package com.ptm.crudapp.util.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RestResponseDto<E> {
    private E data = null;

    private String message;

    @NotNull
    private int  status;

    private Long rows;

    private List<String> errors = new ArrayList<>();

    private String errorCode;

    public RestResponseDto(HttpStatus status, E data) {
        this.data = data;
        this.status = status.value();
        this.message = "";

    }

    public RestResponseDto(HttpStatus status,String message, E data) {
        this.data = data;
        this.message = message;
        this.status = status.value();

    }

    public RestResponseDto(HttpStatus status,String message) {
        this.message = message;
        this.status = status.value();

    }

    @Override
    public String toString() {
        return "RestResponseDto{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }


}
