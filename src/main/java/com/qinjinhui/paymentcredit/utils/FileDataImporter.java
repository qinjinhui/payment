package com.qinjinhui.paymentcredit.utils;

/**
 * @Author qinjinhui
 * @Date 2023/4/21
 * @Describe
 **/

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileDataImporter {


    private final int chunkSize;
    private final int bufferSize;

    private final int numThreads;

    public FileDataImporter(int chunkSize, int bufferSize, int numThreads) {
        this.chunkSize = chunkSize;
        this.bufferSize = bufferSize;
        this.numThreads = numThreads;
    }

    public void importFileData(File file) throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        try {
            // Split file into chunks
            long fileSize = file.length();
            int numChunks = (int) Math.ceil((double) fileSize / chunkSize);

            // Create thread pool for concurrent processing
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);

            // Process each chunk in a separate thread
            for (int i = 0; i < numChunks; i++) {
                long offset = i * (long) chunkSize;
                int chunkSize = (int) Math.min(this.chunkSize, fileSize - offset);

                executor.execute(() -> {
                    try {
                        processChunk(file, offset, chunkSize);
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                });
            }

            // Wait for all threads to complete
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (Exception e) {

        }
    }

    private void processChunk(File file, long offset, int chunkSize) throws IOException, SQLException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r");
             FileChannel channel = raf.getChannel()) {

            MappedByteBuffer buffer = channel.map(MapMode.READ_ONLY, offset, chunkSize);

            byte[] bytes = new byte[bufferSize];
            List<String[]> dataList = new ArrayList<>();

            while (buffer.hasRemaining()) {
                int len = Math.min(bufferSize, buffer.remaining());
                buffer.get(bytes, 0, len);
                String[] data = new String(bytes, 0, len, "UTF-8").split(",");
                dataList.add(data);
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+dataList.size());
                dataList.forEach(str ->System.out.println("每行数据为:{}"+str));
                if (dataList.size() >= 10000) {
                    System.out.println("====="+dataList);
                }

            }
        }
    }

    ;

    private void insertDataIntoDatabase(List<String[]> dataList, PreparedStatement statement) throws SQLException {
        for (String[] data : dataList) {
            statement.setInt(1, Integer.parseInt(data[0]));
            statement.setString(2, data[1]);
            statement.setDouble(3, Double.parseDouble(data[2]));
            statement.addBatch();
        }
        statement.executeBatch();
    }
}







