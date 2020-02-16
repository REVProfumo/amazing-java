package amazingJava;

import java.util.concurrent.*;

public class ThreadsAndGenerics {
    public static void main(String ...args) throws Exception {
        System.out.println(new Response().getResponse(5));
    }
}

class Response extends GenericClass<Integer, String> {

    public String getResponse(Integer i)  throws Exception {
        return getGenericResponse(() -> getSingleThreadResponse(i));
    }
    
    public String getSingleThreadResponse(Integer i) {
        return i.toString();
    }
}

class GenericClass <T,U>  {
  
    public U getGenericResponse(GenericInterface<U> funcInterface) throws Exception {
        ExecutorService ex = Executors.newFixedThreadPool(10);
        Future<U> future = ex.submit(funcInterface);
        U res = future.get();
        return res;
    }

    interface GenericInterface<U> extends Callable<U>{
        U call();
    }
} 