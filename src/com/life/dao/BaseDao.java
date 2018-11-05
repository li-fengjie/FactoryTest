package com.life.dao;

import org.apache.commons.dbutils.QueryRunner;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();
    private Class<T> beanClass;

    public BaseDao(){
        beanClass = (Class<T>) ((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }

    public void add(T bean) throws SQLException, IllegalAccessException {
        Field[] fields = beanClass.getDeclaredFields();
        String sql = "insert into " + beanClass.getSimpleName() + " value(";
        Object[] params = {};
        for(int i = 0;i < fields.length; i++){
            sql += "?";
            if(i < fields.length - 1){
                sql += ",";
            }
            //得到属性
            Field field = fields[i];
            //打开私有访问
            field.setAccessible(true);
            //获取属性
            String name = field.getName();
            //获取属性值
            Object value = field.get(beanClass);
            //一个个赋值
            params[i] = value;
        }
        sql += ")";
        queryRunner.update(sql, params);
    }


    public void update(T bean){

    }

    public void delete(T bean){

    }

    public T load(String uuid){
        return null;
    }

    public List<T> findAll(){
        return null;
    }
}
