package com.leo.concurrent_tool.aqs.cachedImpl;

import lombok.val;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedDaoTest2 {
}

class CachedDaoImpl2 extends VisualDao{
    //使用装饰器模式
    private VisualDao visualDao = new VisualDao();
    //使用重入读写锁
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();

    HashMap<SqlPair,Object> map = new HashMap<>();

    //增加缓存的功能
    @Override
    Object query(String sql, String params) {
        SqlPair pair = new SqlPair(sql,params);
        //从缓存中找，如果找不到则从数据库找
        Object o = null;
        r.lock();
        try{
            if (map.get(pair)!=null){
                return map.get(pair);
            }
        }finally {
            r.unlock();
        }
        w.lock();
        try {
            if ( map.get(pair)==null){
                //当第一次缓存不存在，多个线程进入，那么线程都会进入到后续的代码，加写锁
                //double check
                map.put(pair,visualDao.query(sql,params));
            }
            o = visualDao.query(sql, params);
            map.put(pair,o);//针对缓存的写操作
            return o;
        }finally {
            w.unlock();
        }
    }

    @Override
    boolean update(String sql, String params) {
        w.lock();
        try{
            boolean result = visualDao.update(sql, params);
            //更新
            map.remove(new SqlPair2(sql,params));
            return  result;
        }finally {
            w.unlock();
        }
    }
}

class SqlPair2{
    String sql;
    String params;

    public SqlPair2(String sql, String params) {
        this.sql = sql;
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SqlPair sqlPair = (SqlPair) o;
        return sql.equals(sqlPair.sql) &&
                params.equals(sqlPair.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sql, params);
    }
}
