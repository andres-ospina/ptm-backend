package com.ptm.crudapp.util.service;

import com.ptm.crudapp.util.repository.BaseRepository;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public abstract class BaseServiceImpl<E, K extends Serializable> implements BaseService<E, K> {

    private static final Logger logger   = LoggerFactory.getLogger(BaseServiceImpl.class);
    protected BaseRepository<E, K> repository;

    protected BaseServiceImpl(BaseRepository<E, K> genericDao) {
        this.repository = genericDao;
    }


    @Override
    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Transactional
    public E update(K id, E entity){
        E newEntity = this.findOne(id);

        if (newEntity != null) {

            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Annotation annotation = field.getAnnotation(Id.class);
                    Object value = field.get(entity);

                    if (annotation == null && value != null) {
                        field.set(newEntity, value);

                    }

                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return repository.save(entity);
    }

    public E findOne(K id) {
        try {
            return repository.findById(id).orElse(null);
        } catch (Exception e){
            logger.trace("findOne id:{} message:{}", id, e.getMessage());
        }
        return null;
    }


    @Override
    @Transactional
    public void deleteById(K id) {
        repository.deleteById(id);
    }



}