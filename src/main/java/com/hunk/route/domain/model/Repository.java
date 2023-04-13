package com.hunk.route.domain.model;

/**
 * @author YCKJ4297
 * @date 2023/4/12
 *       <p>
 *       增删改
 */
public interface Repository<T> {

    T save(T t);

    void deleteById(Long id);

    T update(T t);

}
