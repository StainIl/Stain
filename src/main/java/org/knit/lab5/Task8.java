package org.knit.lab5;

import java.util.*;

public class Task8 {
    public static void main(String[] args) {
        String[] productNames = {"книга", "ручка", "линейка", "пенал"};
        Random rand = new Random();
        Product[] products = new Product[1000];

        // Заполнение массива объектов Product
        for (int i = 0; i < products.length; i++) {
            String prodName = productNames[rand.nextInt(productNames.length)];
            int cost = 10 + rand.nextInt(90); // цена
            int stock = 1 + rand.nextInt(20); // количество
            products[i] = new Product(prodName, cost, stock);
        }

        // Сортировка товаров по стоимости
        Arrays.sort(products, Comparator.comparingInt(Product::getCost));

        // Вывод информации о каждом товаре
        for (Product prod : products) {
            System.out.println(prod);
        }

        // Подсчет количества повторяющихся Product
        Map<Product, Integer> countMap = new HashMap<>();
        for (Product prod : products) {
            countMap.put(prod, countMap.getOrDefault(prod, 0) + 1);
        }

        System.out.println("\nКоличество одинаковых товаров:");
        countMap.forEach((product, count) -> {
            if (count > 1) {
                System.out.println(product + " - " + count + " шт.");
            }
        });
    }
}

class Product {
    private String title;
    private int cost;
    private int stock;

    public Product(String title, int cost, int stock) {
        this.title = title;
        this.cost = cost;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Предмет: " + title + "; Цена: " + cost + "; Кол-во: " + stock;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return cost == product.cost && stock == product.getStock() && title.equals(product.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cost, stock);
    }
}
