package com.example.letsquiz.repository;

import com.example.letsquiz.entity.test.Test;
import com.example.letsquiz.entity.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepo extends JpaRepository<Test, Long> {
    List<Test> findAllByAuthorUsername(String username);
    List<Test> findAllBySolversUsername(String username);

}
