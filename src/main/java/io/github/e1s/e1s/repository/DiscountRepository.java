package io.github.e1s.e1s.repository;

import io.github.e1s.e1s.domain.Discount;
import io.github.e1s.e1s.domain.enums.TypeMaleFemaleKid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    //    @Query("SELECT Discount from Discount discount where discount.type =:type")
    Optional<Discount> findByType(TypeMaleFemaleKid typeMaleFemaleKid);

}
