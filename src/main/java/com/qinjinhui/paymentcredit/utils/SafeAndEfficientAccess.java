package com.qinjinhui.paymentcredit.utils;

/**
 * @Author qinjinhui
 * @Date 2023/4/22
 * @Describe
 **/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SafeAndEfficientAccess {
    private final List<String> list = Collections.synchronizedList(new ArrayList<>());
    private final AtomicInteger index = new AtomicInteger(0);

    public void addData(String data) {
        list.add(data);
    }

    public String getNextData() {
        int currentIndex = index.getAndIncrement();
        if (currentIndex >= list.size()) {
            return null;
        }
        return list.get(currentIndex);
    }

    public static void main(String[] args) {
        SafeAndEfficientAccess access = new SafeAndEfficientAccess();
        access.addData("data1");
        access.addData("data2");
        access.addData("data3");
        access.addData("data4");
        access.addData("data5");

        int threadCount = 3;
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                String data = access.getNextData();
                while (data != null) {
                    // do something with data
                    System.out.println(Thread.currentThread().getName() + " processed " + data);
                    data = access.getNextData();
                }
            }).start();
        }
    }
}

