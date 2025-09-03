package com.wipro.sindhu.authservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sindhu.authservice.entities.User;
import com.wipro.sindhu.authservice.enums.UserRole;

import java.util.Optional;


@Repository
public interface UserRepository  extends JpaRepository<User, Long>
{

	 Optional<User> findFirstByEmail(String username);

	 Optional<User> findByUserRole(UserRole admin);

}
