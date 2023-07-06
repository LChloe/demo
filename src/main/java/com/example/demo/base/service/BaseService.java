package com.example.demo.base.service;

import com.example.demo.base.repository.IBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Transactional
public abstract class BaseService<R extends IBaseRepository<M, I>, M, I extends Serializable>{


//    public abstract IBaseRepository<M, I> getBaseRepository();
    @Autowired
    public R baseRepository;

    /**
     * findById.
     *
     * @param id id
     * @return M
     */
    public M find(I id) {
        return baseRepository.findById(id).orElse(null);
    }

    /**
     * @return List
     */
    public List<M> findAll() {
        return baseRepository.findAll();
    }

    /**
     * @param ids ids
     * @return List
     */
    public List<M> findList(I[] ids) {
        List<I> idList = Arrays.asList(ids);
        return baseRepository.findAllById(idList);
    }

    /**
     * find list.
     *
     * @param spec spec
     * @return list
     */
    public List<M> findList(Specification<M> spec) {
        return baseRepository.findAll(spec);
    }

    /**
     * find list.
     *
     * @param spec spec
     * @param sort sort
     * @return List
     */
    public List<M> findList(Specification<M> spec, Sort sort) {
        return baseRepository.findAll(spec, sort);
    }

    /**
     * find one.
     *
     * @param spec spec
     * @return M
     */
    public M findOne(Specification<M> spec) {
        return baseRepository.findOne(spec).orElse(null);
    }

    /**
     * @param pageable pageable
     * @return Page
     */
    public Page<M> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    /**
     * count.
     *
     * @return long
     */
    public long count() {
        return baseRepository.count();
    }

    /**
     * count.
     *
     * @param spec spec
     * @return long
     */
    public long count(Specification<M> spec) {
        return baseRepository.count(spec);
    }

    /**
     * exists.
     *
     * @param id id
     * @return boolean
     */
    public boolean exists(I id) {
        return baseRepository.findById(id).isPresent();
    }

    /**
     * flush.
     */
    public void flush() {
        baseRepository.flush();
    }

    /**
     * save.
     *
     * @param entity entity
     */
    public M save(M entity) {
        return baseRepository.save(entity);
    }

    public M saveAndFlush(M entity) {
        entity = save(entity);
        baseRepository.flush();
        return entity;
    }

    /**
     * save.
     *
     * @param entities entities
     */
    public void save(List<M> entities) {
        baseRepository.saveAll(entities);
    }

    /**
     * update.
     *
     * @param entity entity
     * @return M
     */
    public M update(M entity) {
        return baseRepository.saveAndFlush(entity);
    }

    /**
     * update List
     * @param entityList entityList
     * @return List
     */
    public List<M> updateAll(List<M> entityList) {
        return baseRepository.saveAllAndFlush(entityList);
    }

    /**
     * delete.
     *
     * @param id id
     */
    public void delete(I id) {
        baseRepository.deleteById(id);
    }

    /**
     * delete by ids.
     *
     * @param ids ids
     */
    public void deleteByIds(List<I> ids) {
        baseRepository.deleteAllById(ids);
    }

    /**
     * delete all.
     */
    public void deleteAll() {
        baseRepository.deleteAllInBatch();
    }

    /**
     * delete.
     *
     * @param entities entities
     */
    public void delete(M[] entities) {
        List<M> tList = Arrays.asList(entities);
        baseRepository.deleteAll(tList);
    }

    /**
     * delete.
     *
     * @param entities entities
     */
    public void delete(Iterable<M> entities) {
        baseRepository.deleteAll(entities);
    }

    /**
     * delete.
     *
     * @param entity entity
     */
    public void delete(M entity) {
        baseRepository.delete(entity);
    }

    /**
     * @param ids ids
     * @return List
     */
    public List<M> findList(Iterable<I> ids) {
        return baseRepository.findAllById(ids);
    }

    /**
     * @param spec     spec
     * @param pageable pageable
     * @return Page
     */
    public Page<M> findAll(Specification<M> spec, Pageable pageable) {
        return baseRepository.findAll(spec, pageable);
    }
    
    public R getBaseRepository(){
        return baseRepository;
    }

}
