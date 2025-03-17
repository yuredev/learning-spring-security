package learning.security.secure_post.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learning.security.secure_post.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
