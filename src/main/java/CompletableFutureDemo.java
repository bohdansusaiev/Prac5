package ua.bohdan;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> computeTask("Task 1", 10));
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> computeTask("Task 2", 20));

        CompletableFuture<Integer> combinedResult = task1.thenCombine(task2, Integer::sum);

        System.out.println("Combined task result: " + combinedResult.get());

        CompletableFuture<SoftwareOption> option1 = CompletableFuture.supplyAsync(() -> evaluateSoftware("Software 1", 100, 8, 7));
        CompletableFuture<SoftwareOption> option2 = CompletableFuture.supplyAsync(() -> evaluateSoftware("Software 2", 120, 9, 8));
        CompletableFuture<SoftwareOption> option3 = CompletableFuture.supplyAsync(() -> evaluateSoftware("Software 3", 90, 7, 9));

        CompletableFuture<SoftwareOption> bestOption = CompletableFuture.allOf(option1, option2, option3).thenApply(v -> {
            SoftwareOption o1 = option1.join();
            SoftwareOption o2 = option2.join();
            SoftwareOption o3 = option3.join();
            return findBestOption(o1, o2, o3);
        });

        System.out.println("Best option: " + bestOption.get());
    }

    private static int computeTask(String taskName, int baseValue) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        int result = baseValue + ThreadLocalRandom.current().nextInt(1, 100);
        System.out.println(taskName + " completed with result: " + result);
        return result;
    }

    private static SoftwareOption evaluateSoftware(String name, int price, int functionality, int support) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new SoftwareOption(name, price, functionality, support);
    }

    private static SoftwareOption findBestOption(SoftwareOption... options) {
        SoftwareOption best = options[0];
        for (SoftwareOption option : options) {
            if (option.getScore() > best.getScore()) {
                best = option;
            }
        }
        return best;
    }

    static class SoftwareOption {
        private final String name;
        private final int price;
        private final int functionality;
        private final int support;

        public SoftwareOption(String name, int price, int functionality, int support) {
            this.name = name;
            this.price = price;
            this.functionality = functionality;
            this.support = support;
        }

        public int getScore() {
            return (functionality + support) - price / 10;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Price: " + price + ", Functionality: " + functionality + ", Support: " + support;
        }
    }
}
