package com.ptm.crudapp.controller;

import com.ptm.crudapp.entity.ProductoEntity;
import com.ptm.crudapp.service.ProductoService;
import com.ptm.crudapp.util.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/producto")
public class ProductoController extends BaseController<ProductoEntity, Long> {

    public ProductoController(ProductoService productoService) {
        super(productoService);

    }

    @Override
    public Class<ProductoEntity> getTClass() {
        return ProductoEntity.class;
    }



}
