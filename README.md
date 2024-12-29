# Інструкція до програми CompletableFutureDemo

## Опис програми

Ця програма демонструє роботу з класом `CompletableFuture` у Java для виконання асинхронних завдань. Вона реалізує два приклади використання асинхронних завдань:

1. **Комбінування результатів двох завдань:**
    - Два завдання виконуються паралельно, а їх результати об'єднуються для обчислення загальної суми.

2. **Вибір найкращого варіанту програмного забезпечення:**
    - Три варіанти програмного забезпечення оцінюються паралельно за такими критеріями: ціна, функціональність та підтримка.
    - Після завершення всіх оцінок визначається найкращий варіант.

---

## Основні функції

### 1. Виконання асинхронних завдань
Метод `computeTask` симулює виконання завдання із затримкою. Результат обчислюється на основі початкового значення та випадкової додаткової величини.

```java
CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> computeTask("Task 1", 10));
CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> computeTask("Task 2", 20));

CompletableFuture<Integer> combinedResult = task1.thenCombine(task2, Integer::sum);
System.out.println("Combined task result: " + combinedResult.get());
```

### 2. Оцінка програмного забезпечення
Метод `evaluateSoftware` оцінює варіанти програмного забезпечення. Клас `SoftwareOption` використовується для зберігання даних про варіант. Найкращий варіант визначається за допомогою методу `findBestOption`.

```java
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
```

### 3. Логіка вибору найкращого варіанту
Критерії оцінки включають функціональність, підтримку та ціну. Найкращий варіант визначається за найвищим результатом оцінки (score):

```java
public int getScore() {
    return (functionality + support) - price / 10;
}
```

---

## Як запустити

1. Завантажте вихідний код програми.
2. Впевніться, що у вас встановлено JDK 11 або новішої версії.
3. Скомпілюйте програму за допомогою команди:
   ```bash
   javac CompletableFutureDemo.java
   ```
4. Запустіть програму:
   ```bash
   java CompletableFutureDemo
   ```

---

## Очікуваний результат

- Програма виконає два завдання паралельно та виведе їх суму.
- Оцінить три варіанти програмного забезпечення та виведе найкращий варіант за заданими критеріями.

