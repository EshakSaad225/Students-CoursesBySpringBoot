package com.example.Students.Controllers;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Students.DTO.StudentDTO;
import com.example.Students.Entitys.Student;
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
    public void registerNewStudent(@RequestBody Student student) {
        studentService.AddNewStudent(student);
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<String> enrollStudentInCourse(
            @PathVariable UUID studentId,
            @PathVariable UUID courseId) {
        studentService.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok("Student enrolled successfully");
    }
    

}
