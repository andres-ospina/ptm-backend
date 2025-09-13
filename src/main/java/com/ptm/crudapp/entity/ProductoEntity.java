package com.ptm.crudapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="producto")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sec_producto_id")
    @SequenceGenerator(name="sec_producto_id", sequenceName = "sec_producto", allocationSize = 1)
    private Long Id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="precio")
    private Double precio;

    @Column(name="cantidad_stock")
    private Integer cantidadStock;

}
