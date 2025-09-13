package com.ptm.crudapp.util.controller;

import com.ptm.crudapp.util.common.Constant;
import com.ptm.crudapp.util.dto.response.RestResponseDto;
import com.ptm.crudapp.util.service.BaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
public abstract class BaseController<E, K extends Serializable> extends BaseRestController{



    protected BaseService<E, K> genericService;

    protected BaseController(BaseService<E, K> genericService) {
        this.genericService = genericService;
    }

    public abstract Class<E> getTClass();

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RestResponseDto<E> create(@RequestHeader(value = Constant.CONTENT_TYPE) String contentType,
                                     @RequestBody String object, HttpServletRequest request) {

        return doCreate(contentType,object, request);

    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponseDto<E> read(@PathVariable K id, @RequestHeader(value = Constant.CONTENT_TYPE) String contentType,HttpServletRequest request) {
        return doFindId(id);

    }

    private RestResponseDto<E> doFindId(K id) {
        E entity = genericService.findById(id).orElse(null);
        if (entity == null) {
            return createResponseException(new Exception("No se encontro el registro con el id: " + id));
        } else {
            return createResponse(entity);
        }

    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponseDto<List<E>> readAll(@RequestHeader(value = Constant.CONTENT_TYPE) String contentType, HttpServletRequest request) {

        return doFindAll();


    }

    private RestResponseDto<List<E>> doFindAll() {

        List<E> entities = genericService.findAll();
        if (entities == null || entities.isEmpty()) {
            return createResponseException(new Exception("No se encontro ningun registro"));
        } else {
            return createResponse(entities);
        }

    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponseDto<E> update(@RequestHeader(value = Constant.CONTENT_TYPE) String contentType,
                                     @RequestBody String object,
                                     @PathVariable("id") K id,
                                     HttpServletRequest request) {
        return doUpdate(id,contentType,object, request);
    }



    @CrossOrigin(origins = "*", maxAge = 3600)
    @DeleteMapping(path = "/{id}",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponseDto<E>  delete(@RequestHeader(value = Constant.CONTENT_TYPE) String contentType,
                                      @PathVariable K id,
                                      HttpServletRequest request) {

        return doDelete(id,contentType,request);

    }

    private RestResponseDto<E> doDelete(K id, String contentType, HttpServletRequest request) {

        try{
            genericService.deleteById(id);
            return createResponse("Se elimino el objeto con id: " + id + " correctamente.");
        }catch (Exception e){
            return  createResponseException(e);

        }

    }

    private RestResponseDto<E> doCreate(String contentType, String json, HttpServletRequest request) {

        E entity;
        try {
            //logger.trace("JSON: {}", json);
            entity = gson.fromJson(json, getTClass());
        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
            return procesarJsonToBeanException(request, json, null);
        }

        entity = genericService.save(entity);

        return  createResponse(entity);

    }

    private RestResponseDto<E> doUpdate(K id,String contentType, String json, HttpServletRequest request) {
        E entity;

        try {
            //logger.trace("JSON: {}", json);
            entity = gson.fromJson(json, getTClass());

        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
            return procesarJsonToBeanException(request, json, null);
        }

        entity = genericService.update(id,entity);

        return  createResponse(entity);


    }
}