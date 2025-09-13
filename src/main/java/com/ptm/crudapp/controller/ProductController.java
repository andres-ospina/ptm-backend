package com.ptm.crudapp.controller;

import com.ptm.crudapp.dto.TuplaDto;
import com.ptm.crudapp.entity.ProductEntity;
import com.ptm.crudapp.service.ProductService;
import com.ptm.crudapp.util.controller.BaseController;
import com.ptm.crudapp.util.dto.response.RestResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductController extends BaseController<ProductEntity, Long> {

    private final ProductService productoService;

    public ProductController(ProductService productoService) {
        super(productoService);
        this.productoService = productoService;

    }

    @Override
    public Class<ProductEntity> getTClass() {
        return ProductEntity.class;
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping(value = "total-invetory", produces = "application/json")
    @ResponseBody
    public RestResponseDto<Long> getTotalInventory(HttpServletRequest request){

        try{
            Long totalInventory = this.productoService.getTotalInventory();

            return createResponse(totalInventory);

        }catch (Exception e){
            return  createResponseException(e);

        }

    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping(value = "list-combinations/{value}", produces = "application/json")
    @ResponseBody
    public RestResponseDto<List<TuplaDto>> getListCombinations(@PathVariable Double value, HttpServletRequest request){

        try{
            List<TuplaDto> list = this.productoService.getListCombinations(value);
            return createResponse(list);

        }catch (Exception e){
            return  createResponseException(e);

        }

    }


}
