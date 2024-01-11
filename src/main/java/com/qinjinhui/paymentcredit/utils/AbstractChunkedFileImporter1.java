package com.qinjinhui.paymentcredit.utils;

import com.google.common.collect.Maps;
import com.qinjinhui.paymentcredit.iserver.ChunkedFileImporter;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author qinjinhui
 * @Date 2023/4/22
 * @Describe 分片导入
 **/
@Slf4j
public abstract class AbstractChunkedFileImporter1 implements ChunkedFileImporter {
    private static final int DEFAULT_SLICE_SIZE = 2;
    private static final int DEFAULT_THREAD_POOL_SIZE = 3;

    private AtomicInteger importFileSlice = new AtomicInteger(0);

    private final Lock importFileSliceLock = new ReentrantLock();

    @Autowired
    @Qualifier("fileImportTaskExecutor")
    private Executor fileImportTaskExecutor;

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
                prepareFile();
                List<Pair<Integer, Integer>> indexRanges = splitIntoIndexRanges();

                ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);

                 Map<String, Boolean> processedIndices = Maps.newConcurrentMap();
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

    private void processIndex(Pair<Integer, Integer> pair) {
        log.info("{} processing {}", Thread.currentThread().getName(), pair);
        try {
            processDataInRange(pair.getKey(), pair.getValue());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void prepareFile() throws IOException {
        Path filePath = Paths.get(getSourceFilePath());
        long lineCount = Files.lines(filePath).count();
        setImportFileSlice(Math.toIntExact(lineCount / getImportSliceSize()) + 1);
        // 可以在此处处理其他文件准备逻辑
    }

    private void setImportFileSlice(int value) {
        importFileSliceLock.lock();
        try {
            importFileSlice.set(value);
        } finally {
            importFileSliceLock.unlock();
        }
    }

    private List<Pair<Integer, Integer>> splitIntoIndexRanges() {
        return IntStream.rangeClosed(1, getImportFileSlice())
                .parallel()
                .boxed()
                .collect(Collectors.groupingBy(index -> (index - 1) / getImportSliceSize()))
                .values()
                .stream()
                .map(indices -> new Pair<>(indices.get(0), indices.get(indices.size() - 1)))
                .collect(Collectors.toList());
    }


    private void submitTasks(ExecutorService executorService, List<Pair<Integer, Integer>> indexRanges, Map<String, Boolean> processedIndices) {
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
    }

    public int getImportFileSlice() {
        return importFileSlice.get();
    }


    protected abstract void processData(List<String> data);

    protected abstract String getSourceFilePath();

    protected int getImportSliceSize() {
        return DEFAULT_SLICE_SIZE;
    }

    protected void processDataInRange(int startLine, int endLine) throws IOException {
//        File file = new File(getSourceFilePath());
//        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
//            raf.seek(getLineStartPosition(file, startLine));
//
//            List<String> dataList = new CopyOnWriteArrayList<>();
//            String line;
//            while ((line = raf.readLine()) != null && getLineNumber(line) <= endLine) {
//                dataList.add(new String(line.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
//            }
//
//            processData(dataList);
//        }
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
