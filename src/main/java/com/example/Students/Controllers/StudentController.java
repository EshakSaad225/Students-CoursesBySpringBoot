package com.example.Students.Controllers;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Students.DTO.StudentDTO;
import com.example.Students.DTO.StudentDTOWithoutCourse;
import com.example.Students.Service.StudentService;

@RequestMapping(path =  "/Student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService ;

    @GetMapping
    public List<StudentDTO> getStudents(){
        return studentService.getStudentDTO() ;
    }

    @PostMapping()
    public void registerNewStudent(@RequestBody StudentDTOWithoutCourse newStudent) {
        studentService.AddNewStudent(newStudent);
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public void enrollStudentInCourse(
            @PathVariable UUID studentId,
            @PathVariable UUID courseId) {
        studentService.enrollStudentInCourse(studentId, courseId);
    }

    @PostMapping("/{studentId}/unenroll/{courseId}")
    public void unenrollStudentInCourse(
            @PathVariable UUID studentId,
            @PathVariable UUID courseId) {
        studentService.unenrollStudentInCourse(studentId, courseId);
    }

    @DeleteMapping(path = "{studenId}")
    public void deleteStuden(@PathVariable("studenId") UUID studenId){
        studentService.deleteStudent(studenId);
    }

    @PutMapping
    public void updateCourse(@RequestBody StudentDTOWithoutCourse updatedStudent) {
        studentService.updateStudent(updatedStudent);
    }
    
}
