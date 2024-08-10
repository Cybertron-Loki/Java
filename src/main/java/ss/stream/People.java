package ss.stream;

public class People {
    private String name;

    public People(String name) {
        this.name = name;
    }

    public String hello(String message){
        return this.name+"hello"+message;
    }
}
