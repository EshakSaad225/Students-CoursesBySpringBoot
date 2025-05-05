package com.example.Students.Repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Students.Entitys.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,UUID> {

}
