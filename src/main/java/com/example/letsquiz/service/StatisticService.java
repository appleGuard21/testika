package com.example.letsquiz.service;

import com.example.letsquiz.entity.test.Test;
import com.example.letsquiz.entity.user.AppUser;
import com.example.letsquiz.entity.userStatistic.StatisticTest;
import com.example.letsquiz.repository.StatisticRepository;
import com.example.letsquiz.repository.TestRepo;
import com.example.letsquiz.requests.statistic.CreateStatisticTestRequest;
import com.example.letsquiz.responses.GetStatisticTestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository statisticRepository;
    private final AppUserService userService;
    private final TestRepo testRepo;

    public void create(CreateStatisticTestRequest request){
        AppUser user = userService.findUserByUsername(request.getUsername());
        StatisticTest statisticTest = StatisticTest.builder()
                .title(request.getTitle())
                .points(request.getPoints())
                .author(request.getAuthor())
                .user(user)
                .testId(request.getTestId())
                .build();
        statisticRepository.save(statisticTest);
    }

    public List<GetStatisticTestResponse> getUsersStatisticTests(String username){
        AppUser user = userService.findUserByUsername(username);
        List<StatisticTest> tests = statisticRepository.findAllByUser(user);
        return tests.stream().map(test -> {
            return GetStatisticTestResponse.builder()
                    .id(test.getId())
                    .title(test.getTitle())
                    .points(test.getPoints())
                    .author(test.getAuthor())
                    .build();
        }).toList();
    }

    public List<GetStatisticTestResponse> getUsersTasksStatisticTests(String username){
        List<StatisticTest> resultListOfUsersTestsStatistic = new ArrayList<>();
        List<Test> userTests = testRepo.findAllByAuthorUsername(username);
        List<List<StatisticTest>> statisticListsOfUsersTests = userTests.stream().map(test -> {
            return statisticRepository.findAllByTestId(test.getId());
        }).toList();
        statisticListsOfUsersTests.forEach(resultListOfUsersTestsStatistic::addAll);
        return resultListOfUsersTestsStatistic.stream().map(test -> {
            return GetStatisticTestResponse.builder()
                    .id(test.getId())
                    .title(test.getTitle())
                    .author(test.getAuthor())
                    .points(test.getPoints())
                    .solver(test.getUser().getUsername())
                    .testId(test.getTestId())
                    .build();
        }).filter(r->!r.getSolver().equals(username)).toList();
    }
}
