package com.example.Students.DTO;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class StudentDTO {
    private UUID id ;
    private String firstName ;
    private String lastName ;
    private String phoneNumber ;
    private List<CourseDTOWithoutStudent> course ;
}
