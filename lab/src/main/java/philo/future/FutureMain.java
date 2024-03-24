package philo.future;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureMain {

    public static void main(String[] args) throws Exception {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        out.println(availableProcessors);
        ExecutorService threadPool = Executors.newFixedThreadPool(availableProcessors);

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            out.println(i + ": future submit");
            Future<String> future = CompletableFuture.supplyAsync(() -> {
                String currentThreadName = Thread.currentThread().getName();
                out.println(currentThreadName + ":: working...");
                try {
                    Thread.sleep(1_500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                out.println(currentThreadName + ":: done");

                return "Hello World";
            }, threadPool);
        }

        CompletableFuture.allOf((CompletableFuture<?>) futures).join();

        threadPool.shutdown();

//        while (!threadPool.isTerminated()) {
//            Thread.sleep(500);
//            out.println("Waiting for all tasks to be completed...");
//        }
        out.println("All tasks are completed");

        for (Future<String> future : futures) {
            String result = future.get();
            out.println(result);
        }

    }

    private static class FutureDto {

    }

}
