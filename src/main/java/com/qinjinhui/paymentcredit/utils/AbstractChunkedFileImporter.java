package com.qinjinhui.paymentcredit.utils;

import com.google.common.collect.Maps;
import com.qinjinhui.paymentcredit.iserver.ChunkedFileImporter;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author qinjinhui
 * @Date 2023/4/22
 * @Describe 分片导入
 **/
@Slf4j
public abstract class AbstractChunkedFileImporter implements ChunkedFileImporter {
    private static final int DEFAULT_SLICE_SIZE = 2;
    private static final int DEFAULT_THREAD_POOL_SIZE = 3;

    /**
     * 此接口暂时不用，对子类不暴露调用入口，统一暴露给底层，后续扩展
     */
    @Deprecated
    protected   void execute(){
        executeFileImport();
    }

    public void executeFileImport() {
            try {
                FileCheckUtils.checkFileExists(getSourceFilePath());
                Path filePath = Paths.get(getSourceFilePath());
                long lineCount = Files.lines(filePath).count();
                int importFileSlice = Math.toIntExact(lineCount / getImportSliceSize()) + 1;

                List<Pair<Integer, Integer>> indexRanges = IntStream.rangeClosed(1, importFileSlice)
                        .parallel()
                        .boxed()
                        .collect(Collectors.groupingBy(index -> (index - 1) / getImportSliceSize()))
                        .values()
                        .stream()
                        .map(indices -> new Pair<>(indices.get(0), indices.get(indices.size() - 1)))
                        .collect(Collectors.toList());

                Map<String, Boolean> processedIndices = Maps.newConcurrentMap();
                ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);

                for (int i = 0; i < DEFAULT_THREAD_POOL_SIZE; i++) {
                    executorService.submit(() -> {
                        int size = indexRanges.size();
                        while (processedIndices.size() < size) {
                            int index = ThreadLocalRandom.current().nextInt(size);
                            Pair<Integer, Integer> pair = indexRanges.get(index);
                            processedIndices.computeIfAbsent(pair.getKey().toString(), key -> {
                                log.info(" {} processing {} ", Thread.currentThread().getName(), pair);
                                try {
                                    processDataInRange(pair.getKey(), pair.getValue());
                                } catch (IOException e) {
                                    log.error(e.getMessage());
                                }
                                return true;
                            });
                        }
                    });
                }

                executorService.shutdown();
                executorService.awaitTermination(1, TimeUnit.MINUTES);
            } catch (Exception e) {
               log.error(e.getMessage());
            }
    }

    protected abstract void processData(List<String> data);

    protected abstract String getSourceFilePath();

    protected int getImportSliceSize() {
        return DEFAULT_SLICE_SIZE;
    }

    protected void processDataInRange(int startLine, int endLine) throws IOException {
        File file = new File(getSourceFilePath());
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            raf.seek(getLineStartPosition(file, startLine));

            List<String> dataList = new CopyOnWriteArrayList<>();
            String line;
            while ((line = raf.readLine()) != null && getLineNumber(line) <= endLine) {
                dataList.add(new String(line.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            }

            processData(dataList);
        }
    }

    protected long getLineStartPosition(File file, int lineNumber) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            if (lineNumber < 1) {
                return 0;
            }
            long start = 0;
            int currentLineNumber = 1;
            String line;
            while ((line = raf.readLine()) != null && currentLineNumber < lineNumber) {
                start += line.getBytes().length + System.lineSeparator().getBytes().length;
                currentLineNumber++;
            }
            return start;
        }
    }

    protected int getLineNumber(String line) {
        try {
            return Integer.parseInt(line.substring(0, line.indexOf(";")));
        } catch (NumberFormatException e) {
            return -1;
        }
    }


}
