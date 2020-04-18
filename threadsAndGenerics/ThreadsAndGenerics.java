package amazingJava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ThreadsAndGenerics {
    public static void main(String ...args) throws Exception {
        System.out.println(new Response().getResponse(Arrays.asList(5, 7)));
    }
}

class Response extends GenericClass<String> {

    public List<String> getResponse(List<Integer> list)  throws Exception {
        return getGenericResponse(() -> getSingleThreadResponse(list));
    }
    
    public List<String> getSingleThreadResponse(List<Integer> list) {
        return list.stream().map(i -> i.toString()).collect(Collectors.toList());
    }
}

class GenericClass<U>  {
  
    public List<U> getGenericResponse(Callable<List<U>> funcInterface) throws Exception {
        ExecutorService ex = Executors.newFixedThreadPool(10);
        Future<List<U>> future = ex.submit(funcInterface);
        List<U> res = future.get();
        if(ex != null) {
          ex.shutdown();
        }
        return res;
    }
} 
