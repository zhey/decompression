package com.zhey.decompression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompressFileInfo {
    //存放路径和对应的实体
    private Map<String, CompressEntity> entityMap = new HashMap<String, CompressEntity>();
    private List<CompressEntity> topEntity = new ArrayList<CompressEntity>();

    public Map<String, CompressEntity> getEntityMap() {
        return entityMap;
    }

    public void setEntityMap(Map<String, CompressEntity> entityMap) {
        this.entityMap = entityMap;
    }

    public List<CompressEntity> getTopEntity() {
        return topEntity;
    }

    public void setTopEntity(List<CompressEntity> topEntity) {
        this.topEntity = topEntity;
    }
}
