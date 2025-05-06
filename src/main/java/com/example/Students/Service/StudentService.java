package com.example.Students.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.example.Students.DTO.CourseDTOWithoutStudent;
import com.example.Students.DTO.StudentDTO;
import com.example.Students.DTO.StudentDTOWithoutCourse;
import com.example.Students.Entitys.Course;
import com.example.Students.Entitys.Student;
import com.example.Students.Repository.CourseRepository;
import com.example.Students.Repository.StudentRepository;
import jakarta.transaction.Transactional;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository ;
    @Autowired
    private CourseRepository courseRepository ;
    @Autowired
    @Lazy
    private CourseService courseService ;

    private StudentDTO StudentToDto(Student student) {
        StudentDTO studentDTO = new StudentDTO() ;
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setPhoneNumber(student.getPhoneNumber());

        if(!student.getCourse().isEmpty()){

            List<CourseDTOWithoutStudent> courseDTOWithoutStudent = student.getCourse()
                .stream()
                .map(course -> courseService.CourseDTOWithoutStudent(course)) 
                .collect(Collectors.toList());
                studentDTO.setCourse(courseDTOWithoutStudent);
        }

        return studentDTO ;
    }

    public StudentDTOWithoutCourse studentDTOWithoutCourse(Student student) {
        StudentDTOWithoutCourse studentDTOWithoutCourse = new StudentDTOWithoutCourse() ;
        studentDTOWithoutCourse.setId(student.getId());
        studentDTOWithoutCourse.setFirstName(student.getFirstName());
        studentDTOWithoutCourse.setLastName(student.getLastName());
        studentDTOWithoutCourse.setPhoneNumber(student.getPhoneNumber());

        return studentDTOWithoutCourse ;
    }

    public List<StudentDTO> getStudentDTO(){
        List<Student> student = studentRepository.findAll();
        if(student.isEmpty()){
            return List.of() ;
        }
        return student.stream()
        .map(this::StudentToDto)
        .toList();
    }

    public void AddNewStudent(StudentDTOWithoutCourse newStudent){
        Student student = new Student();
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setPhoneNumber(newStudent.getPhoneNumber());
        studentRepository.save(student);
    }

    public void enrollStudentInCourse(UUID studentId, UUID courseId , String role) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new IllegalStateException("Student not found"));

        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new IllegalStateException("Course not found"));

        if(role.equals("Enrol") ){
            if (student.getCourse().contains(course)) {
                throw new IllegalStateException("Student is Enrolled in this course");
            }
    
            course.getStudent().add(student);
            student.getCourse().add(course);
    
            studentRepository.save(student);
            courseRepository.save(course);
        }
        else{
            System.out.println("hi");
            if (!student.getCourse().contains(course)) {
                throw new IllegalStateException("Student is Unenrolled in this course");
            }
        
            course.getStudent().remove(student);
            student.getCourse().remove(course);
    
            studentRepository.save(student);
            courseRepository.save(course);
        }
    }

    public void deleteStudent(UUID studentId){
        boolean exists = studentRepository.existsById(studentId) ;
        if(!exists){
            throw new IllegalStateException( studentId + " does not exists") ;
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent( StudentDTOWithoutCourse updatedStudent ){
        Student student = studentRepository.findById(updatedStudent.getId()).orElseThrow(
            () -> new IllegalStateException( updatedStudent.getId() + " does not exists")
        );  
        if(!updatedStudent.getFirstName().isEmpty()){
            student.setFirstName(updatedStudent.getFirstName());
        } 
        if(!updatedStudent.getLastName().isEmpty()){
            student.setLastName(updatedStudent.getLastName());
        } 
        if(!updatedStudent.getPhoneNumber().isEmpty()){
            student.setPhoneNumber(updatedStudent.getPhoneNumber());
        } 
    }

}
