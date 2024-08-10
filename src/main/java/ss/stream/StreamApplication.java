package ss.stream;

import jakarta.el.LambdaExpression;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class StreamApplication {

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("张无忌", 18, "男"),
                new Student("杨不悔", 16, "女"),
                new Student("周芷若", 19, "女"),
                new Student("宋青书", 20, "男")
        );
//            students.stream().filter(student -> student.getSex().equals("男")).forEach(System.out::print);
//            System.out.println(filter(students)); // 能得到 张无忌，宋青书
               System.out.println(filter(students,student->student.getAge()<=18));
    }



    //        static List<Student> filter(List<Student> students) {
//            List<Student> result = new ArrayList<>();
//            for (Student student : students) {
//                if (student.getSex().equals("男")) {
//                    result.add(student);
//                }
//            }
//            return result;
//        }
    static List<Student> filter(List<Student> students, Lambda lambda) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (lambda.apply(student)) {
                result.add(student);
            }
        }
        return result;
    }
    interface Lambda{
        boolean apply(Student student);
    }

}


