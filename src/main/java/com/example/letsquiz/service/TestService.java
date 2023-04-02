package com.example.letsquiz.service;

import com.example.letsquiz.entity.file.FileData;
import com.example.letsquiz.entity.test.Test;
import com.example.letsquiz.entity.user.AppUser;
import com.example.letsquiz.repository.TestRepo;
import com.example.letsquiz.requests.test.CreateTestRequest;
import com.example.letsquiz.responses.GetTestResponse;
import com.example.letsquiz.requests.test.UpdateTestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepo testRepo;
    private final AppUserService appUserService;
    private final FileDataService fileDataService;


    public void createTest(CreateTestRequest createTestRequest){
        AppUser appUser = appUserService.findUserByUsername(createTestRequest.getAuthor());
        Optional<FileData> image = fileDataService.getByName(createTestRequest.getImageName());
        Test test = Test.builder()
                .title(createTestRequest.getTitle())
                .questions(createTestRequest.getQuestions())
                .author(appUser)
                .build();
        image.ifPresent(test::setImage);
        testRepo.save(test);

    }

    public void updateTest(UpdateTestRequest updateTestRequest){
        long testId = updateTestRequest.getId();
        Test test = testRepo.findById(testId).orElseThrow(()->
                new IllegalStateException(String.format("Test with id %s not found", testId)));
        test.setTitle(updateTestRequest.getTitle());
        test.setQuestions(updateTestRequest.getQuestions());
        Optional<FileData> image = fileDataService.getByName(updateTestRequest.getImageName());
        image.ifPresent(test::setImage);
        testRepo.save(test);
    }

    public void deleteTest(Long id){
        testRepo.deleteById(id);
    }
    public List<GetTestResponse> getUserTests(String username){
        List<Test> tests = testRepo.findAllByAuthorUsername(username);
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

    public GetTestResponse getTest(Long id){
        Test test =  testRepo.findById(id).orElseThrow(()->
                new IllegalStateException(String.format("Test with id %s not found", id)));
        if(Objects.nonNull(test.getImage())){
            return GetTestResponse.builder()
                    .id(test.getId())
                    .title(test.getTitle())
                    .questions(test.getQuestions())
                    .author(test.getAuthor().getUsername())
                    .imageName(test.getImage().getName())
                    .build();
        } else
            return GetTestResponse.builder()
                    .id(test.getId())
                    .title(test.getTitle())
                    .questions(test.getQuestions())
                    .author(test.getAuthor().getUsername())
                    .build();

    }
}
