package com.example.productapi.Service;

import com.example.productapi.Dto.ProductDto;
import com.example.productapi.Entities.Product;
import com.example.productapi.Repo.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

   public ProductDto addProduct(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setCreatedAt(LocalDateTime.now());
        product.setCategory(dto.category());
        product.setDescription(dto.description());
        product.setQuantity(dto.quantity());
        product.setPaid(dto.isPaid());
        product.setSeen(dto.isSeen());

        Product saved = productRepo.save(product);


        return new ProductDto(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getCategory(),
                saved.getDescription(),
                saved.getQuantity(),
                saved.isPaid(),
                saved.isSeen(),
                saved.getCreatedAt()
        );
    }


    public Page<Product> find(String key,int pageNo, int pageSize, String sortField, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (key == null || key.isEmpty()) {
            return productRepo.findAll(pageable);
        }

        return productRepo.search(key,pageable);
    }


    public List<ProductDto> getAllProducts(){

        return productRepo.findAll().stream().map(
                product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getCategory(),
                        product.getDescription(),
                        product.getQuantity(),
                        product.isPaid(),
                        product.isSeen(),
                        product.getCreatedAt()
                )
        ).toList();
    }

    public ProductDto searchByName(String name){
        return productRepo.searchByName(name);
    }
    public ProductDto searchByCategory(String category){
        return productRepo.searchByCategory(category);

    }public ProductDto searchByDescription(String description){
        return productRepo.searchByDescription(description);
    }
    public List<ProductDto> searchByIsPaid(boolean isPaid){
        return productRepo.searchByIsPaid(isPaid);
    }



}
