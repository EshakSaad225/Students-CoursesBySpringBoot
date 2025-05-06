package com.example.Students.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.example.Students.DTO.CourseDTO;
import com.example.Students.DTO.CourseDTOWithoutStudent;
import com.example.Students.DTO.StudentDTOWithoutCourse;
import com.example.Students.Entitys.Course;
import com.example.Students.Repository.CourseRepository;
import jakarta.transaction.Transactional;


@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository ;

    @Autowired
    @Lazy
    private StudentService studentService ;

     private CourseDTO CourseToDto(Course course) {
        CourseDTO courseDTO = new CourseDTO() ;
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());        
        courseDTO.setTotalHours(course.getTotalHours());

        if(!course.getStudent().isEmpty()){

            List<StudentDTOWithoutCourse> studentDTOWithoutCourse = course.getStudent()
                .stream()
                .map(student -> studentService.studentDTOWithoutCourse(student)) 
                .collect(Collectors.toList());
                courseDTO.setStudent(studentDTOWithoutCourse);
        }
        return courseDTO ;
    }

    public CourseDTOWithoutStudent CourseDTOWithoutStudent(Course course) {
        CourseDTOWithoutStudent courseDTOWithoutStudent = new CourseDTOWithoutStudent() ;
        courseDTOWithoutStudent.setId(course.getId());
        courseDTOWithoutStudent.setName(course.getName());
        courseDTOWithoutStudent.setTotalHours(course.getTotalHours());

        return courseDTOWithoutStudent ;
    }

    public List<CourseDTO> getCourseDto(){
        List<Course> course = courseRepository.findAll();
        if(course.isEmpty()){
            return List.of() ;
        }
        return course.stream()
        .map(this::CourseToDto)
        .toList();
    }

    public void AddNewCourse(CourseDTOWithoutStudent newCourse){
        Course course = new Course() ;
        course.setName(newCourse.getName());
        course.setTotalHours(newCourse.getTotalHours());
        courseRepository.save(course);
    }

    public void deleteCourse(UUID courseId){
        boolean exists = courseRepository.existsById(courseId) ;
        if(!exists){
            throw new IllegalStateException(courseId + " does not exists") ;
        }
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(CourseDTOWithoutStudent updatedCourse){


        Course course = courseRepository.findById(updatedCourse.getId()).orElseThrow(
            () -> new IllegalStateException( updatedCourse.getId() + " does not exists")
        );  
        if(!updatedCourse.getName().isEmpty()){
            course.setName(updatedCourse.getName());
        } 
        if(updatedCourse.getTotalHours() != null ){
            course.setTotalHours(updatedCourse.getTotalHours());
        } 
    }
}
