package com.example.letsquiz.controllers;

import com.example.letsquiz.requests.test.CreateTestRequest;
import com.example.letsquiz.responses.GetTestResponse;
import com.example.letsquiz.requests.test.UpdateTestRequest;
import com.example.letsquiz.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
@CrossOrigin
public class TestController {
    private final TestService service;

    @PostMapping("/createTest")
    public void createTest(@RequestBody CreateTestRequest createTestRequest){
        service.createTest(createTestRequest);
    }
    @PutMapping("/updateTest")
    public void updateTest(@RequestBody UpdateTestRequest updateTestRequest){service.updateTest(updateTestRequest);}
    @DeleteMapping("/deleteTest/{id}")
    public void deleteTest(@PathVariable Long id){
        service.deleteTest(id);
    }
    @GetMapping("/getTests/{username}")
    public List<GetTestResponse> getUserTests(@PathVariable String username){
        return service.getUserTests(username);
    }
    @GetMapping("/getTest/{id}")
    public GetTestResponse getTest(@PathVariable Long id){
        return service.getTest(id);
    }
}
