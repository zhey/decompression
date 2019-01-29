package com.zhey.decompression;

import java.util.ArrayList;
import java.util.List;

public class CompressEntity {
    //文件或文件夹的名称
    private String name;
    //子节点
    private List<CompressEntity> children = new ArrayList<CompressEntity>();
    //类型“d”文件夹，“-”文件
    private String type;
    //相对压缩包的绝对路径
    private String filePath;
    //相对压缩包的层次
    private int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CompressEntity> getChildren() {
        return children;
    }

    public void setChildren(List<CompressEntity> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}
