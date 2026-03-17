package com.example.productapi.Repo;


import com.example.productapi.Dto.ProductDto;
import com.example.productapi.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(""" 
SELECT p FROM Product p
WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :key, '%'))
OR LOWER(p.category) LIKE LOWER(CONCAT('%', :key, '%'))
OR LOWER(p.description) LIKE LOWER(CONCAT('%', :key, '%'))
OR  cast(p.price as string) LIKE CONCAT('%', : key,'%' )
OR  cast(p.isPaid as string) LIKE CONCAT('%', : key,'%' )
OR  cast(p.isSeen as string) LIKE CONCAT('%', : key,'%' )
OR  cast(p.quantity as string) LIKE CONCAT('%', : key,'%' ) 
""")
    Page<Product> search(String key, Pageable pageable);
    ProductDto searchByName(String name);
    ProductDto searchByCategory(String category);
    ProductDto searchByDescription(String description);
    List<ProductDto> searchByIsPaid(boolean isPaid);

}
