package io.github.e1s.components.discount.type;

import io.github.e1s.components.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountTypeRepository extends JpaRepository<DiscountType, Long> {

    Optional<DiscountType> findByType(ProductType productType);

}
