package com.zhey.decompression;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.BaseBlock;
import com.github.junrar.rarfile.FileHeader;
import com.github.junrar.rarfile.UnrarHeadertype;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class Rar extends AbastractCompress implements Compression {
    Archive rarFile = null;

    @Override
    protected boolean loadCompress(File file) {
        try {
            rarFile = new Archive(file);
            FileHeader fh = rarFile.nextFileHeader();
            while (fh != null) {
                if (!fh.isDirectory()) {
                    addFile(StringUtils.isEmpty(fh.getFileNameW()) ? fh.getFileNameString() : fh.getFileNameW());
                } else {
                    addPath(StringUtils.isEmpty(fh.getFileNameW()) ? fh.getFileNameString() : fh.getFileNameW());
                }
                fh = rarFile.nextFileHeader();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RarException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean extract(String filepath, String destPath) {
        if (!compressFileInfo.getEntityMap().keySet().contains(filepath)) {
            return false;
        }
        filepath = filepath.replace("/", "\\");
        try {
            List<BaseBlock> fileHeaderList = rarFile.getHeaders();
            Iterator<BaseBlock> iterator = fileHeaderList.iterator();
            FileHeader fh;
            while (iterator.hasNext()) {
                BaseBlock block = iterator.next();
                if (block.getHeaderType() == UnrarHeadertype.FileHeader) {
                    fh = (FileHeader) block;
                } else {
                    continue;
                }
                String fhName = StringUtils.isEmpty(fh.getFileNameW()) ? fh.getFileNameString() : fh.getFileNameW();
                if (fhName.startsWith(filepath)) {
                    //解压文件
                    String srcFileName = fhName.substring(filepath.lastIndexOf("\\") + 1, fhName.length());
                    writeToFile(fh, srcFileName, destPath);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean extractFile(String srcPath, String destPath, String newName) {
        srcPath = srcPath.replace("/", "\\");
        try {
            FileHeader fh = rarFile.nextFileHeader();
            while (fh != null) {
                if (StringUtils.equals(srcPath, StringUtils.isEmpty(fh.getFileNameW()) ? fh.getFileNameString() : fh.getFileNameW())) {
                    //解压文件
                    String srcFileName = srcPath.substring(srcPath.lastIndexOf(File.separator), srcPath.length());
                    writeToFile(fh, newName, destPath);
                    break;
                }
                fh = rarFile.nextFileHeader();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void writeToFile(FileHeader entry, String srcFileName, String destPath) throws IOException, RarException {
        OutputStream outputStream;
        File destDir = new File(destPath);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        if (destPath.endsWith(File.separator)) {
            destPath += srcFileName;
        } else {
            destPath = destPath + File.separator + srcFileName.replace("\\", File.separator);
        }
        File file = new File(destPath);
        File dir = file.getParentFile();
        if (!dir.exists())
            dir.mkdirs();
        outputStream = new FileOutputStream(destPath);
        rarFile.extractFile(entry, outputStream);
    }

    @Override
    public void close() {
        try {
            if (rarFile != null)
                rarFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
