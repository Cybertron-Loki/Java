package ss.stream;

import java.util.List;
import java.util.stream.Stream;

public class quote {
    public static void main(String[] args) {
        Stream.of(
                new Student("张无忌", 18, "男"),
                new Student("杨不悔", 16, "女"),
                new Student("周芷若", 19, "女"),
                new Student("宋青书", 20, "男")
        ).filter(quote::isMale).forEach(System.out::print);

        test(People::hello);

        Stream.of(
                new Student("张无忌", 18, "男"),
                new Student("杨不悔", 16, "女"),
                new Student("周芷若", 19, "女"),
                new Student("宋青书", 20, "男")
        ).map(Student::getName).forEach(System.out::print);

        Util util = new Util();
        Stream.of(
                new Student("张无忌", 18, "男"),
                new Student("杨不悔", 16, "女"),
                new Student("周芷若", 19, "女"),
                new Student("宋青书", 20, "男")
        ).filter(util::isMale).map(util::getName).forEach(System.out::print);

         createHigher((Create1) Student::new);
        createHigher((Create2) Student::new);
        createHigher((Create3) Student::new);
    }
    static boolean isMale(Student student){
        if(student.getSex().equals("男")){
            return true;
        }
        return false;
    }

    interface lambda{
        String apply(People people,String message);
    }
    static void test(lambda lambda0){    //must be static
        System.out.println( lambda0.apply(new People("章三"),"on 9"));
    }

    static class Util {
        boolean isMale(Student student) {
            return student.getSex().equals("男");
        }
        String getName(Student student) {
            return student.getName();
        }
    }



    interface Create1{
        Student create(String name,String sex);
    }
    interface Create2{
        Student create(String name);
    }
    interface Create3{
        Student create(String name,String sex,Integer age);
    }

    static void createHigher(Create1 create){
        System.out.println(create.create("章三","女"));
    }
    static void createHigher(Create2 create){
        System.out.println(create.create("章三"));
    }
    static void createHigher(Create3 create){
        System.out.println(create.create("章三","女",8));
    }


}
