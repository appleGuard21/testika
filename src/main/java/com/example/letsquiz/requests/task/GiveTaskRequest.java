package com.example.letsquiz.requests.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiveTaskRequest {
    private String solver;
    private long testId;
}
