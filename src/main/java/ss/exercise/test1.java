package ss.exercise;

import org.springframework.format.annotation.DateTimeFormat;

import java.awt.geom.QuadCurve2D;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class test1 {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

    static class MyQueue<E> extends PriorityQueue<E> {
        private final int max;

        public MyQueue(Comparator<? super E> comparator, int max) {
            super(comparator);
            this.max = max;
        }

        @Override
        public boolean offer(E e) {
            boolean r = super.offer(e);
            if (this.size() > max) {
                this.poll();
            }
            return r;
        }
    }

    public static void main(String[] args) throws Exception {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        //public static 不能用
        test11();
    }


    //每月的销售量
    public static void test1() throws IOException {
        String fileName = "./data.txt";
//        Path path= Paths.get(fileName);
//        byte[] bytes = Files.readAllBytes(path);
//        Stream<String> lines = Files.readAllLines(path, Charset.defaultCharset()).stream();
        Stream<String> lines = Files.lines(Path.of(fileName));
        Map<YearMonth, Long> collect = lines.skip(1).
                map(line -> line.split(",")).
                collect(groupingBy(array -> YearMonth.from(formatter.parse(array[1])),
                        TreeMap::new, counting()));
        for (Map.Entry<YearMonth, Long> yearMonthLongEntry : collect.entrySet()) {
            System.out.println(yearMonthLongEntry);
        }
    }

    //    销量最高的月份
    public static void test2() throws IOException {
        Stream<String> lines = Files.lines(Path.of("./data.txt"));
        lines.skip(1)
                .map(line -> line.split(","))
                .collect(groupingBy(array -> YearMonth.from(formatter.parse(array[1])), counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(x -> System.out.println(x));

    }

    //    求销量最高的商品
    public static void test3() throws IOException {
        Stream<String> lines = Files.lines(Path.of("./data.txt"));
        lines.skip(1).map(list -> list.split(","))
                .collect(groupingBy(list -> list[3], counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
//                .max(Comparator.comparingLong(Map.Entry::getValue))
                .ifPresent(System.out::println);
    }

    //    下单最多的前10用户
    public static void test4() throws IOException {

        Stream<String> lines = Files.lines(Path.of("./data.txt"));
        MyQueue<Map.Entry<String, Long>> collect = lines.skip(1).map(s -> s.split(","))
                .collect(groupingBy(array -> array[8], counting()))
                .entrySet()
                .stream().collect(
                        () -> new MyQueue<>(Map.Entry.comparingByValue(), 10),
                        MyQueue::offer,
                        AbstractQueue::addAll
                );
        for (Map.Entry<String, Long> stringLongEntry : collect) {
            System.out.println(stringLongEntry);
        }
    }

    public static void test5() throws IOException {

        Stream<String> lines = Files.lines(Path.of("./data.txt"));
        Map<String, Long> collect = lines.skip(1).map(s -> s.split(","))
                .collect(groupingBy(array -> array[8], TreeMap::new, counting()));
        collect.entrySet().stream().
                sorted(Map.Entry.<String, Long>comparingByValue().reversed()).
                limit(10).
                forEach(System.out::println);
    }

    //    每个地区下单最多的用户
    public static void test6() throws IOException {
        Stream<String> lines = Files.lines(Path.of("./data.txt"));
        Map<String, Map<String, Long>> collect = lines.skip(1).map(s -> s.split(",")).
                collect(groupingBy(list -> list[11], groupingBy(list -> list[8], counting())));
        for (Map.Entry<String, Map<String, Long>> stringLongEntry : collect.entrySet()) {
            System.out.println(stringLongEntry.getKey());
            System.out.println(stringLongEntry.getValue());
        }
//        collect.entrySet().stream()
//                .map(e->Map.Entry(
//                e.getKey(), e.getValue().entrySet().stream().max(Map.Entry.comparingByValue())
//        )).forEach(System.out::println);
        collect.entrySet().stream()
                .map(e -> Map.entry(
                        e.getKey(),
                        e.getValue().entrySet().stream()
                                .max(Map.Entry.comparingByValue())
                ))
                .forEach(System.out::println);
    }

    //    每个地区下单最多的前3用户
    public static void test7() throws IOException {
        Stream<String> lines = Files.lines(Path.of("./data.txt"));
        Map<String, Map<String, Long>> collect = lines.skip(1).map(s -> s.split(",")).
                collect(groupingBy(list -> list[11], groupingBy(list -> list[8], counting())));

        collect.entrySet().stream().map(e -> Map.entry(
                e.getKey(), e.getValue().entrySet().stream().
                        sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(3)
        .toList())).forEach(x->{System.out.println(x.getKey());     //tolist  stream---->entity
            System.out.println(x.getValue());});

    }
//    按类别统计销量
    public static void test8() throws IOException {
        Stream<String> lines = Files.lines(Path.of("./data.txt"));
       lines.skip(1).map(s -> s.split(",")).
               filter(array->!array[5].isEmpty()).
               collect(
                groupingBy((e->e[5]),counting())).entrySet().stream().forEach(System.out::println);

    }
//    按一级类别统计销量

    public static void test9() throws IOException {
        Stream<String> lines = Files.lines(Path.of("./data.txt"));
        lines.skip(1).map(s -> s.split(",")).
                filter(array->!array[5].isEmpty()).
//                map(e->e[5].split(".")).
                collect(
                        groupingBy(test1::firstCategory,counting())).entrySet().stream().forEach(System.out::println);

    }
    static String firstCategory(String[] array){
        String c=array[5];
        int i = c.indexOf(".");
        return c.substring(0,i);
    }
//    按价格区间统计销量
//  p <100
//* 100<= p <500
//* 500<=p<1000
//* 1000<=p

    public static void test10() throws IOException {
        Stream<String> lines = Files.lines(Path.of("./data.txt"));
         lines.skip(1).map(s -> s.split(",")).
                collect(groupingBy(test1::priceRange,counting())).entrySet().stream().forEach(System.out::println);

    }
    static String priceRange (String []array){
        String s = array[7];
        double l = Double.parseDouble(s);
        if(l<100){ return "<100";}
        else if (l<500) {
            return " 100<= p <500";
        } else if (l<1000) {
            return "500<=p<1000";
        }
        else {
            return "1000<=p";
        }

    }
//    不同年龄段女性所下不同类别订单
//    a < 18
//* 18 <= a < 30
//* 30 <= a < 50
//* 50 <= a
public static void test11() throws IOException {
    Stream<String> lines = Files.lines(Path.of("./data.txt"));
    lines.skip(1).map(s -> s.split(",")).filter(s->s[10].equals("女")).filter(array->!array[5].isEmpty()).
          collect(groupingBy(test1::ageRange,groupingBy(test1::firstCategory,counting()))).entrySet().stream().forEach(System.out::println);

}
    static String ageRange (String []array){
        String s = array[9];
        double l = Double.parseDouble(s);
        if(l<18){ return "<18";}
        else if (l<30) {
            return " 18<= p <30";
        } else if (l<50) {
            return "30<=p<50";
        }
        else {
            return "50<=p";
        }

    }
}