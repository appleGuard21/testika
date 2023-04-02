package com.example.letsquiz.requests.test;

import com.example.letsquiz.entity.test.Question;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CreateTestRequest {
    private String title;
    private List<Question> questions;
    private String author;
    private String imageName;
}
