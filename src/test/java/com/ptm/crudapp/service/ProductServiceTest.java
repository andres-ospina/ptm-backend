package com.ptm.crudapp.service;

import com.ptm.crudapp.dto.TuplaDto;
import com.ptm.crudapp.entity.ProductEntity;
import com.ptm.crudapp.repository.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    public ProductService productService;

    @Mock
    public ProductRepository productRepository;

    @Before
    public void setUp() { }

    @Test
    void getTotalInventory() {
        Long resultExpect = 5L;
        when(productRepository.getTotalInventory()).thenReturn(resultExpect);
        Long resultAssert = productService.getTotalInventory();
        assertEquals(resultAssert,resultExpect);
    }

    @Test
    void getListCombinations() {
        List<ProductEntity> resultExpect = new ArrayList<>();
        resultExpect.add(new ProductEntity(1L,"A", "Descripcion A", 2D,2));
        resultExpect.add(new ProductEntity(2L,"B", "Descripcion B", 4D,4));
        resultExpect.add(new ProductEntity(3L,"C", "Descripcion C", 6D,6));
        resultExpect.add(new ProductEntity(4L,"D", "Descripcion D", 8D,8));
        when(productRepository.findAll()).thenReturn(resultExpect);
        List<TuplaDto> resultAssert = productService.getListCombinations(10D);
        assertEquals(resultAssert.size(),4);

    }
}