package com.leo.concurrent_tool.aqs.cachedImpl;

import java.util.HashMap;
import java.util.Objects;

public class CachedDaoTest {
}
class CachedDaoImpl extends VisualDao{
    //使用装饰器模式
    private VisualDao visualDao = new VisualDao();

    HashMap<SqlPair,Object> map = new HashMap<>();

    //增加缓存的功能
    @Override
    Object query(String sql, String params) {
        SqlPair pair = new SqlPair(sql,params);
        //从缓存中找，如果找不到则从数据库找
        if (map.get(pair)!=null){
            return map.get(pair);
        }
        Object o = visualDao.query(sql, params);
        //放入到缓存中
        map.put(pair,o);
        return o;
    }

    @Override
    boolean update(String sql, String params) {
        //更新
        map.remove(new SqlPair(sql,params));
        //更新之后删除缓存
        return visualDao.update(sql, params);
    }
}
class SqlPair{
    String sql;
    String params;

    public SqlPair(String sql, String params) {
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
