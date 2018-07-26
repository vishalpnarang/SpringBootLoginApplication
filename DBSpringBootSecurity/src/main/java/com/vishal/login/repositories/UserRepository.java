package com.vishal.login.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.login.entites.Users;

public interface UserRepository extends JpaRepository<Users,Integer>{

	Optional<Users> findByName(String userName);

}
