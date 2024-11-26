package com.imaks;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0),
                new Product("Phone", "Electronics", 1050.0),
                new Product("Air purifier ", "Appliances", 150.0),
                new Product("Microphone", "Electronics", 80.0),
                new Product("Coat", "Clothing", 250.0),
                new Product("Trousers", "Clothing", 100.0),
                new Product("Shirt", "Clothing", 80.0),
                new Product("Blazer", "Clothing", 180.0),
                new Product("Air fryer", "Appliances", 50.0)
        );

        //групуємо та виводимо продукти по категоріям
        Map<String, List<Product>> categorized = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("Products by category:");
        categorized.forEach((category, proctList) -> {
            System.out.println(category + ": \n" + proctList);
        });

        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory
                        ,Collectors.averagingDouble(Product::getPrice)));
        //знаходимо та виводимо середню ціну по категоріям
        System.out.println("\nAverage price by category:");
        averagePriceByCategory.forEach((category, price) -> {
            System.out.println(category + " - " + price + "$");
        });

        //знаходимо та виводимо категорію з найвищю ціною
        Optional<Map.Entry<String, Double>> maxAveragePriceCategory = averagePriceByCategory.entrySet()
                .stream().max(Map.Entry.comparingByValue());
        maxAveragePriceCategory.ifPresent(entry -> {
            System.out.println(String.format(
                    "\nThe maximum average price category is %s with average price %s$"
            , entry.getKey(), entry.getValue()));
        });
    }
}