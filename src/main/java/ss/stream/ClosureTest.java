package ss.stream;

import org.apache.catalina.Executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ClosureTest {
    public static void main(String[] args) throws IOException {
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i < i++; i++) {
            int k=1+i;
            Runnable task=()-> System.out.println(Thread.currentThread()+"completing tesk"+k);
            list.add(task);
        }
        final Executors executor;

//        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
        for (Runnable task:list){
//            service.submit(task);
        }
        System.in.read();
    }
}
