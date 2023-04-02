package com.example.letsquiz.entity.test;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Answer {
    @Id
    @SequenceGenerator(name = "answer_sequence", sequenceName = "answer_sequence")
    @GeneratedValue(generator = "answer_sequence" )
    private Long id;
    private String title;
    private boolean isRight;
}
