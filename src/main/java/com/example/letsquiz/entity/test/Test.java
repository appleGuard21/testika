package com.example.letsquiz.entity.test;

import com.example.letsquiz.entity.file.FileData;
import com.example.letsquiz.entity.user.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Test {
    @Id
    @SequenceGenerator(name = "test_sequence", sequenceName = "test_sequence")
    @GeneratedValue(generator = "test_sequence" )
    private Long id;
    private String title;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;
    @ManyToOne
    private AppUser author;
    @ManyToMany
    private List<AppUser> solvers = new ArrayList<>();
    @OneToOne
    private FileData image;

}
