package com.example.Students.DTO;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class CourseDTOWithoutStudent {
    private UUID id ;
    private String name ;
    private int totalHours ;
}
