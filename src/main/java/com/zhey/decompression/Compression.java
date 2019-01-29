package com.zhey.decompression;


public interface Compression {
    /**
     * 加载压缩文件
     *
     * @param filePath
     * @return
     */
    boolean load(String filePath);

    /**
     * 解压文件到文件夹
     *
     * @param fileFullName
     * @param folderPath
     * @return
     */
    boolean extractToFolder(String fileFullName, String folderPath);

    /**
     * 解压文件到文件夹，并重命名
     *
     * @param fileFullName
     * @param folderPath
     * @param newFileName
     * @return
     */
    boolean extractFileToFolder(String fileFullName, String folderPath, String newFileName);

    /**
     * 获取压缩文件的结构信息
     *
     * @return
     */
    CompressFileInfo getDirInfo();

    void close();

}
