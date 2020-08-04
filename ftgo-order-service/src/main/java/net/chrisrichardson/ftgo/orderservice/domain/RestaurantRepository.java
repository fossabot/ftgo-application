package net.chrisrichardson.ftgo.orderservice.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * 使用了JPA
 */
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
