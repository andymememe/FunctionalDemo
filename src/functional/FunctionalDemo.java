/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Andy Chen
 */
public class FunctionalDemo {

    public static void main(String[] args) {
        String str = "dammitimmad";
        List<String> strs = new ArrayList<>();
        List<Integer> ints = new ArrayList<>();
        Supplier<Integer> intRandoms = () -> {
            return (int)(Math.random() * 100);
        };
        
        // Add Elements to List
        strs.addAll(Stream.of(
                "Test",
                "2Test",
                "3Test",
                "None").collect(Collectors.toList()));                
        ints.addAll(Stream
                .generate(intRandoms)
                .distinct()
                .limit(10)
                .collect(Collectors.toList()));
        
        // Print All
        strs.stream().forEach(s -> System.out.print(s + " "));
        System.out.print("\n");
        ints.stream().forEach(i -> System.out.print(i.toString() + " "));
        System.out.print("\n");
        
        // Filter
        strs.stream()
                .filter(s -> s.contains("Test"))
                .forEach(s -> System.out.println("Is Test: " + s));
        
        // Find Any
        strs.stream()
                .filter(s -> !s.contains("Test"))
                .findAny()
                .stream()
                .forEach((String s) -> {
                    System.out.println("Not test: " + s);
                });
        
        // Find First
        strs.stream()
                .filter(s -> s.contains("Test"))
                .findFirst()
                .stream()
                .forEach((String s) -> {
                    System.out.println("First test: " + s);
                });
        
        // Flat Map
        strs.stream()
                .findFirst()
                .stream()
                .flatMap(s -> s.chars().boxed())
                .forEach(s -> System.out.print(s.toString() + " "));
        System.out.print("\n");
        
        // Map
        ints.stream()
                .map(i -> i + 1)
                .forEach(i -> System.out.print(i.toString() + " "));
        System.out.print("\n");
        
        // Map to Int
        ints.stream()
                .mapToInt(i -> i * 2)
                .forEach((i) -> System.out.print(String.valueOf(i) + " "));
        System.out.print("\n");
        
        // Sorted & Limit
        ints.stream()
                .sorted((a, b) -> (b > a) ? 1 : -1)
                .limit(5)
                .forEach(i -> System.out.print(String.valueOf(i) + " "));
        System.out.print("\n");
        
        // Any Match & All Match
        System.out.println("All > 50: " + ints.stream().allMatch(i -> i > 50));
        System.out.println("Any > 50: " + ints.stream().anyMatch(i -> i > 50));
        
        // Max & Min
        ints.stream()
                .max((a, b) -> (a > b) ? 1 : -1)
                .stream()
                .forEach(i -> System.out.println("Max: " + i.toString()));
        ints.stream()
                .min((a, b) -> (a > b) ? 1 : -1)
                .stream()
                .forEach(i -> System.out.println("Max: " + i.toString()));
        
        // Peek
        ints.stream()
                .peek(i -> System.out.print("Original: " + i.toString() + " "))
                .map(i -> i + 1)
                .forEach(i -> System.out.print(" New: " + i.toString() + "\n"));
        
        // Reduce
        System.out.print("Sum: ");
        ints.stream()
                .reduce((a, b) -> a + b)
                .stream()
                .forEach(i -> System.out.print(i));
        System.out.print("\n");
        
        // Skip
        ints.stream()
                .sorted()
                .skip(2)
                .forEach(i -> System.out.print(i.toString() + " "));
        System.out.print("\n");
                
        // Concat
        Stream.concat(
               Stream.generate(intRandoms)
                       .filter(i -> i <= 4)
                       .distinct()
                       .limit(2),
               Stream.generate(intRandoms)
                       .filter(i -> i > 8)
                       .distinct()
                       .limit(3)
        ).forEach(i -> System.out.print(String.valueOf(i) + " "));
        System.out.print("\n");
        
        str.chars()
                .collect(ArrayList::new,
                        (list, ele) -> list.add(0, (char) ele),
                        (list1, list2) -> list1.add(0, list2))
                .forEach(e -> System.out.print(e));
        System.out.print("\n");
    }
}
