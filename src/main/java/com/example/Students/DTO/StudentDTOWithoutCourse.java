package com.example.Students.DTO;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class StudentDTOWithoutCourse {
    private UUID id ;
    private String firstName ;
    private String lastName ;
    private String phoneNumber ;
}
