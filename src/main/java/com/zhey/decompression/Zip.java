package com.zhey.decompression;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.archivers.zip.ZipFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 获取zip文件的详细目录结构
 * 解压指定的文件
 *
 * @author zhey
 */
public class Zip extends AbastractCompress implements Compression {
    private ZipFile zipFile;

    @Override
    public boolean loadCompress(File file) {
        List<String> files = new ArrayList<String>();
        try {
            zipFile = new ZipFile(file,"CP936");
            Enumeration<ZipArchiveEntry> zipEntryEnumeration = zipFile.getEntries();
            while (zipEntryEnumeration.hasMoreElements()) {
                ZipArchiveEntry zipEntry = zipEntryEnumeration.nextElement();
                if (zipEntry.isDirectory()) {
                    addPath(zipEntry.getName());
                } else {
                    addFile(zipEntry.getName());
                }
            }
            return true;
        } catch (IOException e) {
        }
        return false;
    }

    @Override
    protected boolean extract(String filepath, String destPath) {
        try {
            ZipArchiveEntry entry = (ZipArchiveEntry) zipFile.getEntry(filepath);
            if (entry == null) {
                return false;
            } else {
                if (entry.isDirectory()) {
                    Enumeration<ZipArchiveEntry> zipArchiveEntries = zipFile.getEntries();
                    while (zipArchiveEntries.hasMoreElements()) {
                        ZipArchiveEntry tmpEntry = zipArchiveEntries.nextElement();
                        if (tmpEntry.getName().startsWith(filepath) && !tmpEntry.isDirectory()) {
                            String fileName = tmpEntry.getName();
                            fileName = fileName.substring(filepath.length(), fileName.length());
                            writeToFile(tmpEntry, fileName, destPath);
                        }
                    }
                } else {
                    //解压文件
                    String srcFileName = filepath.substring(filepath.lastIndexOf(File.separator), filepath.length());
                    writeToFile(entry, srcFileName, destPath);
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean extractFile(String filepath, String destPath, String newName) {
        try {
            ZipArchiveEntry entry = (ZipArchiveEntry) zipFile.getEntry(filepath);
            if (entry == null) {
                return false;
            } else {
                if (entry.isDirectory()) {
                    return false;
                } else {
                    //解压文件
                    writeToFile(entry, newName, destPath);
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void writeToFile(ZipArchiveEntry entry, String srcFileName, String destPath) throws IOException {
        InputStream inputStream = zipFile.getInputStream(entry);
        OutputStream outputStream;
        File destDir = new File(destPath);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        if (destPath.endsWith(File.separator)) {
            destPath += srcFileName;
        } else {
            destPath = destPath + File.separator + srcFileName;
        }
        File file = new File(destPath);
        File dir = file.getParentFile();
        if (!dir.exists())
            dir.mkdirs();
        outputStream = new FileOutputStream(destPath);
        byte[] buffer = new byte[1024];
        int size;
        while ((size = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, size);
        }
        outputStream.close();
        inputStream.close();
    }

    @Override
    public void close() {
        try {
            if (zipFile != null)
                zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
