package com.zhey.decompression;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class SevenZ extends AbastractCompress implements Compression {
    @Override
    protected boolean loadCompress(File file) {
        return false;
    }

    @Override
    protected boolean extract(String filepath, String destPath) {
        return false;
    }

    @Override
    protected boolean extractFile(String srcPath, String destPath, String newName) {
        return false;
    }

    @Override
    public void close() {

    }

//
//    @Override
//    protected void loadCompress(File file) {
//        try {
//            SevenZFile sevenZFile = new SevenZFile(file);
//            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
//            while (entry != null) {
//                if (entry.isDirectory()) {
//                    addPath(entry.getName());
//                } else {
//                    addFile(entry.getName());
//                }
//                entry = sevenZFile.getNextEntry();
//            }
//            sevenZFile.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected boolean extract(File compressFile, String filepath, String destPath) {
//        try {
//            SevenZFile sevenZFile = new SevenZFile(compressFile);
//            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
//            while (entry != null) {
//                if (StringUtils.equals(entry.getName(), filepath)) {
//                    byte[] buffer = new byte[1024];
//                    OutputStream outputStream = new FileOutputStream(new File(destPath));
//                    int size = 0;
//                    while ((size = sevenZFile.read(buffer)) > 0) {
//                        outputStream.write(buffer, 0, size);
//                    }
//                    outputStream.close();
//                    break;
//                }
//                entry = sevenZFile.getNextEntry();
//            }
//            sevenZFile.close();
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    protected boolean extractDir(File compressFile, String srcPath, String destPath) {
//        return false;
//    }
}
