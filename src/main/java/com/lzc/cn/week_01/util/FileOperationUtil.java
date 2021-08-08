package com.lzc.cn.week_01.util;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author lc
 */
public class FileOperationUtil {
    // todo 读取字节码文件
    public static byte[] getByteFromFile(Path path) {
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(path);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
