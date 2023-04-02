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
public class UpdateTestRequest {
    private Long id;
    private String title;
    private List<Question> questions;
    private String imageName;
}
