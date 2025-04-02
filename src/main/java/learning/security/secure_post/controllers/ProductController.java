package learning.security.secure_post.controllers;

import org.springframework.web.bind.annotation.RestController;
import learning.security.secure_post.models.Product;
import learning.security.secure_post.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        try {
            var products = productRepository.findAll();
            return ResponseEntity.ok().body(products);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro inesperado ao listar produtos");
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            var productSaved = productRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro inesperado ao cadastrar produto");
        }
    }
    
}
