package com.example.productapi.Repo;


import com.example.productapi.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    Page<Product> searchByCategory(String category, Pageable pageable);

    Page<Product> searchByIsPaid(boolean isPaid, Pageable pageable);

}
