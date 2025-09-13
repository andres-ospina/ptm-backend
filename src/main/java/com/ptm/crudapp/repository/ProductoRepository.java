package com.ptm.crudapp.repository;

import com.ptm.crudapp.entity.ProductoEntity;
import com.ptm.crudapp.util.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends BaseRepository<ProductoEntity, Long> {
}
