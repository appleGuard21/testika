package com.example.letsquiz.requests.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStatisticTestRequest {
    private String title;
    private int points;
    private String author;
    private String username;
    private long testId;
}
