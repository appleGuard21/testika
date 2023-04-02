package com.example.letsquiz.controllers;

import com.example.letsquiz.requests.task.DeleteTaskRequest;
import com.example.letsquiz.requests.task.GiveTaskRequest;
import com.example.letsquiz.responses.GetTestResponse;
import com.example.letsquiz.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
@CrossOrigin
public class TaskController {
    private final TaskService service;

    @PutMapping("/giveTask")
    public void giveTask(@RequestBody GiveTaskRequest request){
        service.giveTask(request);
    }
    @GetMapping("/{username}")
    public List<GetTestResponse> getUserTasks(@PathVariable String username){
        return service.getUserTasks(username);
    }
    @PutMapping("/deleteTask")
    public void deleteTask(@RequestBody DeleteTaskRequest request){
        service.deleteTask(request);
    }


}
