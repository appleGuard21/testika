package com.example.letsquiz.responses;

import com.example.letsquiz.entity.test.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTestResponse {
    private Long id;
    private String title;
    private String author;
    private List<Question> questions;
    private String imageName;
}
