package com.product.solution.service;

import com.product.solution.dto.ProductRequest;
import com.product.solution.dto.UpdateProductRequest;
import com.product.solution.exception.NoRecordFoundException;
import com.product.solution.model.Product;
import com.product.solution.repo.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository repository;
    public Product addProduct(ProductRequest product) {
        Product productEntity = new Product(product.getName(), product.getDescription(), product.getVendor(), product.getPrice(),
                product.getStock(), product.getCurrency(),product.getImage_url(), product.getSku());
        repository.save(productEntity);
        logger.info("product added successfully");
        return productEntity;
    }
    public Product updateProduct(UpdateProductRequest updateRequest, Integer productId) throws NoRecordFoundException {
        Optional<Product> productOptional = repository.findById(productId);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setStock(updateRequest.getStock());
            product.setPrice(updateRequest.getPrice());
            repository.save(product);
            logger.info("product updated successfully");
            return product;
        }else{
            throw new NoRecordFoundException("There is no product for given id "+productId);
        }
    }

    public List<Product> getAllProducts() throws NoRecordFoundException {
        List<Product> products = repository.findAll();
        if(products.isEmpty()){
            throw new NoRecordFoundException("no product exists");
        }
        return products;
    }

    public List<Product> getAllProductsByVendor(String vendor) throws NoRecordFoundException {
        List<Product> products = repository.findByVendorIgnoreCase(vendor);
        if(products.isEmpty()){
            throw new NoRecordFoundException("no product exists for given vendor "+vendor);
        }
        return products;
    }

    public void deleteProduct(Integer productId) {
        repository.deleteById(productId);
        logger.info("product deleted successfully for id {}",productId);
    }
}
