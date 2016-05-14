package com.backend.opiniothon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
	List<UserEntity> findByUserName(String username);
}
