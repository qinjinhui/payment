package com.qinjinhui.paymentcredit.utils;

/**
 * @Author qinjinhui
 * @Date 2023/12/28
 * @Describe
 **/
import java.util.function.Supplier;

public class RetryUtil {

    private static final int DEFAULT_MAX_ATTEMPTS = 3;

    public static <T> T retryOperation(Supplier<T> operation, boolean enableLogging, Object... failureMessages) {
        return retryOperation(operation, DEFAULT_MAX_ATTEMPTS, enableLogging, failureMessages);
    }

    public static <T> T retryOperation(Supplier<T> operation, int maxAttempts, boolean enableLogging, Object... failureMessages) {
        int attempt = 0;
        T result = null;

        while (result == null && attempt < maxAttempts) {
            try {
                result = operation.get();
                if (attempt == 2)
                {
                    // 使用 lambda 表达式定义需要重试的操作
                    Supplier<MyObject> operation1 = () -> {
                        System.out.println("Trying to perform some operation...");
                        return someOperation();
                    };
                }
                if (result != null) {
                    return result; // 如果操作成功，则返回结果
                }
            } catch (RuntimeException e) {
                if (enableLogging) {
                    System.out.print("Operation failed. Retrying...");

                    // 打印额外的失败消息
                    for (Object msg : failureMessages) {
                        System.out.print(" " + msg);
                    }

                    System.out.println("------------------重试次数"+attempt);
                }
            }
            attempt++;
        }

        if (enableLogging) {
            System.out.print("Reached maximum retry attempts. Giving up...");

            // 打印额外的失败消息
            for (Object msg : failureMessages) {
                System.out.print(" " + msg);
            }

            System.out.println();
        }
        return null; // 如果达到最大重试次数仍然失败，则返回 null
    }
    public static void main(String[] args) {
        int maxRetryAttempts = 3;
        Object failureMessage1 = "Failure Message 1";
        Object failureMessage2 = "Failure Message 2";

        // 使用 lambda 表达式定义需要重试的操作
        Supplier<MyObject> operation = () -> {
            System.out.println("Trying to perform some operation...");
            return someOperation();
        };

        MyObject result = RetryUtil.retryOperation(operation, maxRetryAttempts, true, failureMessage1, failureMessage2);

        if (result != null) {
            System.out.println("Operation successful! Result: " + result);
        } else {
            System.out.println("Reached maximum retry attempts. Giving up. Failure Messages: " + failureMessage1 + ", " + failureMessage2);
        }
    }

    static class MyObject {
        // 实际的对象类型
        @Override
        public String toString() {
            return "MyObject instance";
        }
    }

    private static MyObject someOperation() {
        // 模拟操作失败的情况
        throw new RuntimeException("Operation failed");
    }

    private static MyObject someOperation1() {
        // 模拟操作失败的情况
        return new MyObject();
    }

}
