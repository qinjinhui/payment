package com.qinjinhui.paymentcredit.utils;

/**
 * @Author qinjinhui
 * @Date 2023/4/21
 * @Describe  分片处理
 **/
import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class FileSplitter {

    private final int numThreads;
    private final int chunkSize;
    private final int bufferSize;

    public FileSplitter(int numThreads, int chunkSize, int bufferSize) {
        this.numThreads = numThreads;
        this.chunkSize = chunkSize;
        this.bufferSize = bufferSize;
    }

    public void splitFile(File file) throws IOException, InterruptedException {
        long fileSize = file.length();
        int numChunks = (int) Math.ceil((double) fileSize / chunkSize);

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numChunks; i++) {
            int chunkIndex = i;
            executor.execute(() -> {
                try (RandomAccessFile input = new RandomAccessFile(file, "r");
                     FileChannel channel = input.getChannel();
                     FileChannel outputChannel = new FileOutputStream(file.getName() + "." + chunkIndex + ".gz").getChannel();
                     DeflaterOutputStream output = new DeflaterOutputStream(Channels.newOutputStream(outputChannel), new Deflater(Deflater.BEST_SPEED))
                ) {
                    long offset = chunkIndex * (long) chunkSize;
                    int chunkFileSize = (int) Math.min(chunkSize, fileSize - offset);

                    MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, offset, chunkFileSize);
                    byte[] bytes = new byte[bufferSize];

                    int len;
                    while (buffer.hasRemaining()) {
                        len = Math.min(bufferSize, buffer.remaining());
                        buffer.get(bytes, 0, len);
                        output.write(bytes, 0, len);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}

        System.out.println("All chunks processed.");
    }

}
