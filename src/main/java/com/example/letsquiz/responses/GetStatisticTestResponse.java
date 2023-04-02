package com.example.letsquiz.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStatisticTestResponse {
    private long id;
    private String title;
    private int points;
    private String author;
    private long testId;
    private String solver;
}
