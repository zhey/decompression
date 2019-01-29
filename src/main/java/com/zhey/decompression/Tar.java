package com.zhey.decompression;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class Tar extends AbastractCompress implements Compression {
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

//    @Override
//    protected void loadCompress(File file) {
//        try {
//            InputStream is = new FileInputStream(file);
//            TarArchiveInputStream tin = new TarArchiveInputStream(is);
//            TarArchiveEntry entry = tin.getNextTarEntry();
//            while (entry != null) {
//                System.out.println(entry.getName());
//                if (entry.isDirectory()) {
//                    addPath(entry.getName());
//                } else {
//                    addFile(entry.getName());
//                }
//                entry = tin.getNextTarEntry();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected boolean extract(File compressFile, String filepath, String destPath) {
//        try {
//            InputStream is = new FileInputStream(compressFile);
//            TarArchiveInputStream tin = new TarArchiveInputStream(is);
//            TarArchiveEntry entry = tin.getNextTarEntry();
//            while (entry != null) {
//                if (StringUtils.equals(filepath, entry.getName())) {
//                    InputStream inputStream = new FileInputStream(entry.getFile());
//                    OutputStream outputStream = new FileOutputStream(new File(destPath));
//                    byte[] buffer = new byte[1024];
//                    int size;
//                    while ((size = inputStream.read(buffer)) > 0) {
//                        outputStream.write(buffer, 0, size);
//                    }
//                    outputStream.close();
//                    inputStream.close();
//                }
//                entry = tin.getNextTarEntry();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    protecteded boolean extractDir(File compressFile, String srcPath, String destPath) {
//        return false;
//    }
}
