package com.ptm.crudapp.service;

import com.ptm.crudapp.entity.ProductoEntity;
import com.ptm.crudapp.repository.ProductoRepository;
import com.ptm.crudapp.util.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductoService extends BaseServiceImpl<ProductoEntity, Long> {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
