package philo.future;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class FutureMain {

    public static void main(String[] args) throws Exception {

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        out.println(availableProcessors);
        var threadPool = Executors.newFixedThreadPool(availableProcessors);
        List<CompletableFuture<String>> futures = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            out.println(i + ": future submit");
            var completableFuture = CompletableFuture.supplyAsync(task(), threadPool);
            futures.add(completableFuture);
        }

        futures.forEach(CompletableFuture::join);
        threadPool.shutdown();
        out.println("All tasks are completed");

        for (Future<String> future : futures) {
            String result = future.get();
            out.println(result);
        }
    }

    @NotNull
    private static Supplier<String> task() {
        return () -> {
            String currentThreadName = Thread.currentThread().getName();
            out.println(currentThreadName + ":: working...");
            try {
                Thread.sleep(1_500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            out.println(currentThreadName + ":: done");

            return "Hello World: " + UUID.randomUUID().toString();
        };
    }
}
