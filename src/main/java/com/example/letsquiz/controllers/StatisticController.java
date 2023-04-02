package com.example.letsquiz.controllers;

import com.example.letsquiz.requests.statistic.CreateStatisticTestRequest;
import com.example.letsquiz.responses.GetStatisticTestResponse;
import com.example.letsquiz.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistic")
@CrossOrigin
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/my/{username}")
    public List<GetStatisticTestResponse> getStatistic(@PathVariable String username){
        return statisticService.getUsersStatisticTests(username);
    }

    @PostMapping("/create")
    public void create(@RequestBody CreateStatisticTestRequest request){
        statisticService.create(request);
    }

    @GetMapping("/tasks/{username}")
    public List<GetStatisticTestResponse> getTasksStatistic(@PathVariable String username){
        return statisticService.getUsersTasksStatisticTests(username);
    }
}
