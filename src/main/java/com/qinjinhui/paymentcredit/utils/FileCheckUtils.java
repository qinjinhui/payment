package com.qinjinhui.paymentcredit.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Author qinjinhui
 * @Date 2023/4/23
 * @Describe
 **/
@Slf4j
public   class FileCheckUtils {
    public static void checkFileExists(String filePath) throws FileNotFoundException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Invalid file path");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }
}
