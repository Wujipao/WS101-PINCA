package com.pinca.lab_7.model;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
     //1.READ ALL
    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = productService.readAll();
        return ResponseEntity.ok(products);
    }
    //2. READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable long prodId){
        Optional<Product> product = productService.readOne(prodId);
        return product.map(ResponseEntity:: ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //3. CREATE
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product newProduct = productService.create(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    //4. UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product productDetails){
        Optional<Product> updatedProduct = productService.update(id, productDetails);
        return updatedProduct.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id")long id){
        boolean deleted =  productService.delete(id);

        if (deleted){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
