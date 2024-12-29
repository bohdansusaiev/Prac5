# Варіант 4

Моя програма демонструє роботу з методами класу `CompletableFuture`, а саме: `thenCompose()`, `thenCombine()`, `allOf()` та `anyOf()`. Для цього я використовую знання з лекцій та минулих практичних. 

1. Створіть два асинхронних завдання, які виконуються паралельно. Коли обидва завдання завершаться, їх результати повинні бути об’єднані.
2. Користувач обирає програмне забезпечення для певної задачі. Є кілька варіантів, і потрібно порівняти їх за наступними критеріями: ціна, функціональність і підтримка. Потрібно паралельно отримати ці дані і вибрати найкращий варіант.

## Сума результатів завдань

Два завдання виконуються одночасно, їх результати підсумовуються:

```java
CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> computeTask("Task 1", 10));
CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> computeTask("Task 2", 20));

CompletableFuture<Integer> result = task1.thenCombine(task2, Integer::sum);
System.out.println("Сума результатів: " + result.get());
```

## Вибір найкращого програмного забезпечення

Оцінюються три варіанти за ціною, функціональністю та підтримкою. Після завершення всіх завдань обирається найкращий варіант:

```java
CompletableFuture<SoftwareOption> option1 = CompletableFuture.supplyAsync(() -> evaluateSoftware("Software 1", 100, 8, 7));
CompletableFuture<SoftwareOption> option2 = CompletableFuture.supplyAsync(() -> evaluateSoftware("Software 2", 120, 9, 8));
CompletableFuture<SoftwareOption> option3 = CompletableFuture.supplyAsync(() -> evaluateSoftware("Software 3", 90, 7, 9));

CompletableFuture<SoftwareOption> bestOption = CompletableFuture.allOf(option1, option2, option3).thenApply(v -> {
    return findBestOption(option1.join(), option2.join(), option3.join());
});

System.out.println("Найкращий варіант: " + bestOption.get());
```

## Запуск програми

Компіляція та запуск програму:
   ```bash
   javac CompletableFutureDemo.java
   java CompletableFutureDemo
   ```

## Результат

Програма виведе суму результатів двох завдань і найкращий варіант програмного забезпечення.
