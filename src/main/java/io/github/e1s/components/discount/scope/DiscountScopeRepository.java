package io.github.e1s.components.discount.scope;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountScopeRepository extends JpaRepository<DiscountScope, Long> {

}
