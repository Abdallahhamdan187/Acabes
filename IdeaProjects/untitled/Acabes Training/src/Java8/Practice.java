package Java8;
import java.awt.*;
import java.util.List;
import java.util.function.*;
import java.lang.*;
public class Practice {
    public static void main(String[] args) {
        Predicate<Integer> positive = x -> x >= 0;
        Predicate<Integer> isEven = x -> x % 2 == 0;
        Predicate<Integer> graterThan10 = x -> x > 10;
        Predicate<Integer> combined = positive.and(isEven).and(graterThan10);
        System.out.println(combined.test(10));

        List<String> name = List.of("abdallah", "mohammad", "salem");
        Predicate<String> aNames = x -> x.startsWith("a");
        Predicate<String> four = x -> x.length() > 4;
        Predicate<String> complex = aNames.and(four);
        name.stream()
                .filter(complex)
                .forEach(System.out::println);


        Predicate<String> onlyLetters = x -> x.matches("[a-zA-Z]+");
        Predicate<String> length = x -> x.length() >= 5 && x.length() <= 10;
        Predicate<String> complex2 = onlyLetters.and(length);
        System.out.println(complex2.test("JavaTest"));


        Predicate<String> blank = x -> x.isBlank();
        Predicate<String> complex3 = blank.negate();
        System.out.println(complex3.test(" "));

        Consumer<String> c1 = x -> System.out.println(x);
        Consumer<String> c2 = x -> System.out.println(x.length());

        c1.andThen(c2).accept("java");

        List<Integer> nums = List.of(1, 2, 3, 4, 5, 36, 25, 100, 22);
       nums.forEach(x->System.out.println(x*x));

       List<Integer>numbers1= List.of(1, 2, 34);

       Consumer<List<Integer>>numbers= List -> numbers1.add(0,1);
        //numbers1.forEach(x->System.out.println(numbers1);

    }


}
