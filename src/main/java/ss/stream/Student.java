package ss.stream;

public class Student {
        private String name;
        private String sex;
        private Integer age;

        public Student(String name,  Integer age,String sex) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public Student(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}

