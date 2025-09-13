package com.ptm.crudapp.service;

import com.ptm.crudapp.dto.TuplaDto;
import com.ptm.crudapp.entity.ProductEntity;
import com.ptm.crudapp.repository.ProductRepository;
import com.ptm.crudapp.util.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService extends BaseServiceImpl<ProductEntity, Long> {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Long getTotalInventory() {
        return  this.repository.getTotalInventory();
    }

    public List<TuplaDto> getListCombinations(Double value) {
        List<ProductEntity> result = this.repository.findAll();
        List<TuplaDto> listofThree = generateCombinationsOfThree(result,value);
        List<TuplaDto> listofTTwo = generateCombinationsOfTwo(result, listofThree,value);
        listofTTwo.sort(Comparator.comparingDouble(TuplaDto::price).reversed());

        if(listofTTwo.size()<=5){
            return listofTTwo;
        }

        return new ArrayList<>();
    }

    private List<TuplaDto> generateCombinationsOfTwo(List<ProductEntity> result, List<TuplaDto> listofThree, Double value) {

        for (int i = 0; i < result.size(); i++) {
            ProductEntity p1 = result.get(i);
            for (int j = i + 1; j < result.size(); j++) {
                ProductEntity p2 = result.get(j);
                double total = p1.getPrice()+p2.getPrice();
                if( total <= value) {
                    String string = p1.getName()+','+p2.getName()+','+total;
                    TuplaDto tuplaDto = new TuplaDto(string, total);
                    listofThree.add(tuplaDto);
                }
            }
        }


        return listofThree;
    }

    private List<TuplaDto> generateCombinationsOfThree(List<ProductEntity> result, Double value) {
        List<TuplaDto> listofThree = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            ProductEntity p1 = result.get(i);
            for (int j = i + 1; j < result.size(); j++) {
                ProductEntity p2 = result.get(j);
                for (int k = j + 1; k < result.size(); k++) {
                    ProductEntity p3 = result.get(k);
                    double total = p1.getPrice()+p2.getPrice()+p3.getPrice();
                    if( total <= value) {
                        String string = p1.getName()+','+p2.getName()+','+p3.getName()+','+total;
                        TuplaDto tuplaDto = new TuplaDto(string, total);
                        listofThree.add(tuplaDto);
                    }
                }
            }
        }

        return listofThree;

    }


}
