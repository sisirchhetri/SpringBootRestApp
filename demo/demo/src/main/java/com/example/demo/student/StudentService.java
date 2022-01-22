package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//Service Layer
@Service
public class StudentService {

    private final StudentRepository  studentRepository;

    @Autowired //Dependency Injection
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
//        return List.of(
//               new Student(
//                        1L,
//                        "Ramesh",
//                        "ram12@gmail.com",
//                        LocalDate.of(2000, Month.JANUARY,5),
//                        21
//                       ));

//                ,new Student(
//                        "Shyam",
//                        "shyam26@gmail.com",
//                        LocalDate.of(2001, Month.JANUARY,5),
//                        20
//                )


////                Student s2 = new Student(
////                        1L,
////                        "Shyam",
////                        "shyam12@gmail.com",
////                        LocalDate.of(2000, Month.JANUARY,5),
////                        21
////                )
//        );

    }
    
                    
    public void addNewStudent(Student student) {
       Optional<Student> optionalStudent =
                              studentRepository.findStudentByEmail(student.getEmail()) ;

       if (optionalStudent.isPresent()){
           throw new IllegalStateException("email already taken");
       }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {

    boolean exists = studentRepository.existsById(studentId);

        if (!exists){
            throw new IllegalStateException("student with id "+ studentId+" does not exists");
        }
       studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                    .orElseThrow( () -> new IllegalStateException(
                            "student with id "+ studentId +" does not exist"
                            ));


       if (name!= null && name.length() > 0 && !Objects.equals(student.getName(), name)){
           student.setName(name);
       }

       if (email!= null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){

           Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

           if(studentOptional.isPresent()){
               throw new IllegalStateException("email already exists");
           }
           student.setEmail(email);
       }

    }
}

