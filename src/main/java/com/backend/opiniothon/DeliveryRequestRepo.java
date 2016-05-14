package com.backend.opiniothon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRequestRepo extends JpaRepository<DeliveryRequestEntity, Long> {
	List<DeliveryRequestEntity> findByCustomerDetails(long id);
}
