package com.ptm.crudapp.repository;

import com.ptm.crudapp.entity.ProductEntity;
import com.ptm.crudapp.util.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {
    @Query("SELECT SUM(p.price * p.stockQuantity) FROM ProductEntity p")
    Long getTotalInventory();


}
