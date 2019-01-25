package com.yonjar.www.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by LuoYJ on 2019/1/25.
 * @Transactional(readOnly = false) readOnly=false 意思是表示 增删改，如果是true就只是读
 */
public abstract class BaseService<T,K extends Serializable> {

    @Autowired
    CrudRepository<T, K> crud;

    /**
     * 单条记录保存
     * @param entity
     */
    @Transactional(readOnly = false)       //默认是false
    public void save(T entity){
        crud.save(entity);
    }

    /**
     * 批量保存
     * @param entitys
     */
    @Transactional(readOnly = false)
    public void save(List<T> entitys){
        crud.save(entitys);
    }

    /**
     * 更新记录
     * @param entity
     */
    @Transactional(readOnly = false)
    public void update(T entity,K id) {
        T  bean= crud.findOne(id);
        Class clazz = bean.getClass();
        Field[] fields = bean.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method method = pd.getReadMethod();
                Object old = method.invoke(bean);
                Object news = method.invoke(entity); //新值
                String s1 = old == null ? "" : old.toString();//避免空指针异常
                String s2 = news == null ? "" : news.toString();//避免空指针异常
                if (!s1.equals(s2)) {
                    System.err.println("不一样的属性：" + field.getName() + " 属性值：[" + s1 + "," + s2 + "]");
                    field.setAccessible(true);
                    field.set(bean,news);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        crud.save(bean);
    }

    /**
     * 单条记录查询
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    public T findOne(K id){
        return crud.findOne(id);
    }

    /**
     * 单条记录删除
     * @param id
     */
    @Transactional(readOnly = false)
    public void delete(K id){
        crud.delete(id);
    }

    /**
     * 批量删除
     * @param entitys
     */
    @Transactional(readOnly = false)
    public void delete(List<T> entitys){
        crud.delete(entitys);
    }
}
