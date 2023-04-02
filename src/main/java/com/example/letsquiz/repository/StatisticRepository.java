package com.example.letsquiz.repository;

import com.example.letsquiz.entity.user.AppUser;
import com.example.letsquiz.entity.userStatistic.StatisticTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<StatisticTest, Long> {
    List<StatisticTest> findAllByUser(AppUser user);
    List<StatisticTest> findAllByTestId(Long testId);
}
