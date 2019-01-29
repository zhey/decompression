package com.zhey.decompression;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public abstract class AbastractCompress implements Compression {
    private String filePath;
    private File compressFile;
    //存放路径和对应的实体
    protected CompressFileInfo compressFileInfo = new CompressFileInfo();

    @Override
    public boolean load(String filePath) {
        compressFile = new File(filePath);
        if (compressFile.exists()) {
            return loadCompress(compressFile);
        } else {
            return false;
        }
    }

    @Override
    public boolean extractToFolder(String fileFullName, String folderPath) {
        return extract(fileFullName, folderPath);
    }

    @Override
    public boolean extractFileToFolder(String fileFullName, String folderPath, String newFileName) {
        return extractFile(fileFullName,folderPath,newFileName);
    }


    @Override
    public CompressFileInfo getDirInfo() {
        return compressFileInfo;
    }


    protected boolean addFile(String file) {
        String[] paths;
        //分割路径,使用junrar时文件路径始终时windows路径需要特殊处理
        if (this instanceof Rar) {
            paths = StringUtils.split(file, "\\");
        } else {
            paths = StringUtils.split(file, File.separatorChar);
        }
        CompressEntity head = null;
        String absPath = "";
        for (int index = 0; index < paths.length; index++) {
            //文件名后不加分隔符
            if (index < paths.length - 1) {
                absPath = absPath + paths[index] + File.separatorChar;
            } else {
                absPath = absPath + paths[index];
            }
            if (compressFileInfo.getEntityMap().containsKey(absPath)) {
                //路径已经存在就使用保存的路径
                head = compressFileInfo.getEntityMap().get(absPath);
            } else {
                CompressEntity child = new CompressEntity();
                child.setName(paths[index]);
                child.setLevel(index);
                child.setFilePath(absPath);
                if (index == paths.length - 1) {
                    child.setType("-");
                } else {
                    child.setType("d");
                }
                if (head != null) {
                    head.getChildren().add(child);
                }
                head = child;
                compressFileInfo.getEntityMap().put(absPath, child);
                if (index == 0) {
                    compressFileInfo.getTopEntity().add(child);
                }
            }
        }
        return false;
    }

    protected boolean addPath(String path) {
        String[] paths;
        //分割路径
        if (this instanceof Rar) {
            paths = StringUtils.split(path, File.separatorChar);
        } else {
            paths = StringUtils.split(path, File.separatorChar);
        }
        CompressEntity head = null;
        String absPath = "";
        for (int index = 0; index < paths.length; index++) {
            absPath = absPath + paths[index] + File.separatorChar;
            if (compressFileInfo.getEntityMap().containsKey(absPath)) {
                //路径已经存在就使用保存的路径
                head = compressFileInfo.getEntityMap().get(absPath);
            } else {
                CompressEntity child = new CompressEntity();
                child.setName(paths[index]);
                child.setLevel(index);
                child.setType("d");
                child.setFilePath(absPath);
                if (head != null) {
                    head.getChildren().add(child);
                }
                head = child;
                compressFileInfo.getEntityMap().put(absPath, child);
                if (index == 0) {
                    compressFileInfo.getTopEntity().add(child);
                }
            }
        }
        return false;
    }

    protected abstract boolean loadCompress(File file);

    protected abstract boolean extract(String filepath, String destPath);

    protected abstract boolean extractFile(String srcPath, String destPath,String newName);
}
