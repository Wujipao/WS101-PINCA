package com.pinca.lab_7.model;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private Map<Long, Product> products = new ConcurrentHashMap<>();
    private AtomicLong idGenerator =  new AtomicLong(0);

    public ProductService(){
        products.put(idGenerator.incrementAndGet(), new Product(idGenerator.get(), "ID 1: Nikon", 999.9));
        products.put(idGenerator.incrementAndGet(), new Product(idGenerator.get(), "ID 2: Sony", 2000.99));
        products.put(idGenerator.incrementAndGet(), new Product(idGenerator.get(), "ID 3: Canon", 4000.00));
    }

    public List<Product> readAll(){
        return new ArrayList<>(products.values());
    }
    public Optional<Product> readOne(long id){
        return Optional.ofNullable(products.get(id));
    }
    public Product create(Product newProduct){
        long newId = idGenerator.incrementAndGet();
        newProduct.setID(newId);
        products.put(newId, newProduct);
        return newProduct;
    }
    public Optional<Product> update(long id, Product updatedProduct){
        if (products.containsKey(id)){
            Product oldProduct = products.get(id);
            oldProduct.setName(updatedProduct.getName());
            oldProduct.setPrice(updatedProduct.getPrice());
            return Optional.of(oldProduct);
        }
        return Optional.empty();
    }

    public boolean delete(Long id){
        return products.remove(id) != null;
    }
}
