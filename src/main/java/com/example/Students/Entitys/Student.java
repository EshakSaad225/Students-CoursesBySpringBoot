package com.example.Students.Entitys;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor 
@Entity
@Table
public class Student {
    @Id
    @GeneratedValue
    private UUID id ;
    private String firstName ;
    private String lastName ;
    private String phoneNumber ;

    @ManyToMany(mappedBy = "student")
    private List<Course> course ;
    
}
