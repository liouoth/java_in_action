package com.leo.concurrent_tool.aqs.cachedImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VisualDao {
    Object query(String sql,String params){
        log.debug("查询了！");
        return new Object();
    }
    boolean update(String sql,String params){
        log.debug("修改了！");
        return true;
    }
}
