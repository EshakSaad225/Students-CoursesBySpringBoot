package com.example.Students.DTO;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class CourseDTO {
    private UUID id ;
    private String name ;
    private Integer totalHours ;
    private List<StudentDTOWithoutCourse> student ;
}
