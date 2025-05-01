package io.denchik.cinemakursach.repository;

import io.denchik.cinemakursach.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
