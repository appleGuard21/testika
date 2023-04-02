package com.example.letsquiz.entity.file;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FileData {
    @Id
    @SequenceGenerator(name = "file_sequence", sequenceName = "file_sequence")
    @GeneratedValue(generator = "file_sequence" )
    private long id;
    private String name;
}
