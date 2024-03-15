package com.product.solution.controller;

import com.product.solution.dto.ProductRequest;
import com.product.solution.dto.UpdateProductRequest;
import com.product.solution.exception.NoRecordFoundException;
import com.product.solution.model.Product;
import com.product.solution.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequest product){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addProduct(product));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody UpdateProductRequest updateRequest, @PathVariable Integer productId) throws NoRecordFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateProduct(updateRequest,productId));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAll() throws NoRecordFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllProducts());
    }

    @GetMapping("/{vendor}")
    public ResponseEntity<List<Product>> getByVendor(@PathVariable String vendor) throws NoRecordFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllProductsByVendor(vendor));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId) throws NoRecordFoundException {
        service.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
