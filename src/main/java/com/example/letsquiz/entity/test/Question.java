package com.example.letsquiz.entity.test;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Question {
    @Id
    @SequenceGenerator(name = "question_sequence", sequenceName = "question_sequence")
    @GeneratedValue(generator = "question_sequence" )
    private Long id;
    private String title;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;
}
