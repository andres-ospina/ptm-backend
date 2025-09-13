package com.ptm.crudapp.util.controller;

import com.google.gson.Gson;
import com.ptm.crudapp.util.dto.response.RestResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class BaseRestController {

    @Autowired
    protected Gson gson;

    public <E> RestResponseDto<E> createResponse(E data){
        return new RestResponseDto(HttpStatus.OK,data);
    }

    public <E> RestResponseDto<E> createResponseException(Exception e){
        return new RestResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    public <E> RestResponseDto<E> procesarJsonToBeanException(HttpServletRequest request, String json, String  className) {
        String mensaje = String.format("No se puede convertir %s al objeto %s", json,className);
        return new RestResponseDto(HttpStatus.CONFLICT, mensaje);
    }

    public <E> RestResponseDto<E> createResponse( String mensaje){
        return new RestResponseDto(HttpStatus.OK,mensaje);
    }

}
