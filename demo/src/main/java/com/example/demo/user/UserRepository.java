package com.example.demo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	//사용자 ID로 SiteUser 엔티티를 조회하는 findByUsername메서드를 추가
	Optional<SiteUser> findByUsername(String username);

}
