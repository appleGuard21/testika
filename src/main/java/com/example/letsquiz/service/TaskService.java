package com.example.letsquiz.service;

import com.example.letsquiz.entity.test.Test;
import com.example.letsquiz.entity.user.AppUser;
import com.example.letsquiz.repository.TestRepo;
import com.example.letsquiz.requests.task.DeleteTaskRequest;
import com.example.letsquiz.requests.task.GiveTaskRequest;
import com.example.letsquiz.responses.GetTestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TestRepo testRepo;
    private final AppUserService appUserService;

    public List<GetTestResponse> getUserTasks(String username){
        List<Test> tests = testRepo.findAllBySolversUsername(username);
        return tests.stream().map(test -> {
            if(Objects.nonNull(test.getImage())){
                return GetTestResponse.builder()
                        .id(test.getId())
                        .title(test.getTitle())
                        .questions(test.getQuestions())
                        .author(test.getAuthor().getUsername())
                        .imageName(test.getImage().getName())
                        .build();
            }else {
                return GetTestResponse.builder()
                        .id(test.getId())
                        .title(test.getTitle())
                        .questions(test.getQuestions())
                        .author(test.getAuthor().getUsername())
                        .build();
            }
        }).toList();
    }
    public void giveTask(GiveTaskRequest request){
        Test test = testRepo.findById(request.getTestId()).orElseThrow(()->
                new IllegalStateException(String.format("Test with id %s not found", request.getTestId())));
        AppUser solver = appUserService.findUserByUsername(request.getSolver());
        test.getSolvers().add(solver);
        testRepo.save(test);
    }
    public void deleteTask(DeleteTaskRequest request){
        Test test = testRepo.findById(request.getTestId()).orElseThrow(()->
                new IllegalStateException(String.format("Test with id %s not found", request.getTestId())));
        AppUser solver = appUserService.findUserByUsername(request.getSolver());
        test.setSolvers(
                test.getSolvers().stream().filter(s->solver.getId() != s.getId()).collect(Collectors.toList())
        );
        testRepo.save(test);
    }

}
