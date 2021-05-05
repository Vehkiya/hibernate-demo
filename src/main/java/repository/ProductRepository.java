package repository;

import models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    Optional<Product> findByIdAndName(Long id, String name);

    @Query("Select p from Product p where p.name like :likeParam")
    List<Product> findLike(@Param("likeParam") String like);
}
