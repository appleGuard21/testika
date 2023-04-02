package com.example.letsquiz.repository;

import com.example.letsquiz.entity.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long>{
    Optional<AppUser> findAppUserByUsername(String username);
    Optional<AppUser> findAppUserById(long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


}
