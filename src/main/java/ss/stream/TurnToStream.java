package ss.stream;

import java.sql.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class TurnToStream {
    public static void main(String[] args) {
        Integer[] list1 = new Integer[5];
        list1[0] = 1;
        list1[1] = 2;
        list1[2] = 3;
        Stream<Integer> stream = Arrays.stream(list1);
        System.out.println(stream);
        System.out.println("=====================================");

        Stream.of("x", "y", "", "z").dropWhile(s -> !s.isEmpty()).forEach(System.out::println);
        System.out.println("=====================================");
        Stream.of("x", "y", "", "z").takeWhile(s -> !s.isEmpty()).forEach(System.out::println);


    }


}
