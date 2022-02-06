package com.elzacontiero.supermarket.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elzacontiero.supermarket.domain.Product;
import com.elzacontiero.supermarket.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductService service;
	
	private ProductController(ProductService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody Product productFromUser) {

        Product createdProduct = service.create(productFromUser);

		return new ResponseEntity<Product>(createdProduct, HttpStatus.CREATED);
	}
	
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable long id, @RequestBody Product product) { 
    
        return new ResponseEntity<Product>(service.update(id, product), HttpStatus.ACCEPTED);

    }
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
	
		if (service.delete(id)) {
			return new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
	}

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> allProducts = service.listAllProducts();
        return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/deals")
    public ResponseEntity<List<Product>> getDeals() {
        List<Product> deals = service.listCurrentDeals();
        return new ResponseEntity<List<Product>>(deals, HttpStatus.OK);
    }
}

