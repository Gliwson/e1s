package io.github.e1s.components.product.repository;

import io.github.e1s.components.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product SET views =(views + 1) WHERE id =:id")
    void findByIdAndIncreaseViewsByOne(@Param("id") Long id);

}
