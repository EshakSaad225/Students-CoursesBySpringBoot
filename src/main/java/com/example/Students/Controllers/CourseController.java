package com.example.Students.Controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Students.DTO.CourseDTO;
import com.example.Students.Entitys.Course;
import com.example.Students.Service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping(path =  "/Course")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService ;

    @GetMapping 
    public List<CourseDTO> getCourses(){
        return courseService.getCourseDto();
    }

    @PostMapping()
    public void registerNewCourse(@RequestBody Course course) {
        courseService.AddNewCourse(course);
    }    

}
