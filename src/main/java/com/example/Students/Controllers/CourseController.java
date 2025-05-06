package com.example.Students.Controllers;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Students.DTO.CourseDTO;
import com.example.Students.DTO.CourseDTOWithoutStudent;
import com.example.Students.Service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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
    public void registerNewCourse(@RequestBody CourseDTOWithoutStudent newCourse) {
        courseService.AddNewCourse(newCourse);
    }    

    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") UUID courseId){
        courseService.deleteCourse(courseId);
    }

    @PutMapping
    public void updateCourse(
        @RequestBody CourseDTOWithoutStudent updatedCourse
    ) {
        courseService.updateCourse(updatedCourse);
    }

}
