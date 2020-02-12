package io.github.e1s.components.discount;

import io.github.e1s.components.product.TypeMaleFemaleKid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByType(TypeMaleFemaleKid typeMaleFemaleKid);

}
