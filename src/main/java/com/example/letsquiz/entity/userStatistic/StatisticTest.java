package com.example.letsquiz.entity.userStatistic;

import com.example.letsquiz.entity.user.AppUser;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StatisticTest {
    @Id
    @SequenceGenerator(name = "statistic_test_sequence", sequenceName = "statistic_test_sequence")
    @GeneratedValue(generator = "statistic_test_sequence" )
    private long id;
    private String title;
    private int points;
    private String author;
    private long testId;
    @ManyToOne
    private AppUser user;
}
