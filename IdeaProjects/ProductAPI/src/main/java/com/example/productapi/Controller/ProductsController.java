package com.example.productapi.Controller;

import com.example.productapi.Dto.ProductDto;
import com.example.productapi.Entities.Product;
import com.example.productapi.Repo.ProductRepo;
import com.example.productapi.Service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductRepo productRepo;
    private final ProductService productService;

    public ProductsController(ProductService productService, ProductRepo productRepo) {
        this.productService = productService;
        this.productRepo = productRepo;
    }

    @GetMapping("/search")
    public Page<Product> find(@RequestParam(required = false) String key,
                              @RequestParam(defaultValue = "1") int pageNo,
                              @RequestParam(defaultValue = "10") int pageSize,
                              @RequestParam(defaultValue = "id") String sortField,
                              @RequestParam(defaultValue = "ASC") String sortDir) {


        return productService.find(key, pageNo, pageSize, sortField, sortDir);
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto dto) {
        productService.addProduct(dto);
        return dto;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/name/{name}")
    public ProductDto searchByName(@PathVariable String name){
        return productService.searchByName(name);
    }

    @GetMapping("/category/{name}")
    public ProductDto searchByCategory(@PathVariable String name){
        return productService.searchByCategory(name);
    }

    @GetMapping("/des/{name}")
    public ProductDto searchByDescription(@PathVariable String name){
        return productService.searchByDescription(name);
    }
    @GetMapping("/paid/{name}")
    public List<ProductDto> searchByIsPaid(@PathVariable boolean name){
        return productService.searchByIsPaid(name);
    }


}
