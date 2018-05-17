package com.zhey.decompression;


public class DemoMain {
    public static void zip() {
        Compression compression;
        boolean loadSuccess;
        compression = new Zip();
        loadSuccess = compression.load("/home/zhey/Demo/demo.zip");
        CompressFileInfo compressFileInfo = compression.getDirInfo();
        //解压文r
        compression.extractToFolder("demo/jre64/","/home/zhey/tmp/idea");
        compression.extractFileToFolder("demo/bin/demo.png","/home/zhey/tmp/demo/","tmp.png");
        compression.close();
    }

    public static void rar() {
        Compression compression;
        boolean loadSuccess;
        compression = new Rar();
        loadSuccess = compression.load("/home/zhey/Demo/demo.rar");
        CompressFileInfo compressFileInfo = compression.getDirInfo();
        compression.extractToFolder("精品资源_目录导航/","/home/zhey/tmp/rar");
        System.out.println(compressFileInfo);
        compression.close();
    }

    public static void main(String[] args) {
        rar();
        zip();
    }
}
