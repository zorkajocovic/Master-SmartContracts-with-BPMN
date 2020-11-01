package com.Service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Appuser;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AppuserRepository extends JpaRepository<Appuser, Long> {
	
	@Query("SELECT u FROM Appuser u WHERE u.email  = :email")
	Appuser findByEmail(@Param("email") String email);
	
}
