package Java8;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.*;//import everything
import java.util.stream.Collectors;

public class java8 {
// functional interface is an interface with one abstract method it can contain one abstract or multiple default methods or multiple static method

    @FunctionalInterface
interface MyInterface {
    void doSomething();
}

//Lambada expression
    //(parameter)->expression or statement
    Runnable r =()->System.out.println("hello");

    //================================================================
    //predicate
    //represents a condition(boolean returning function)
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }
    //used for filtering and logical testing
    //Predicate<Integer> isEven =x->x%2==0;
    //System.out.println(isEven.test(4));

    //Predicate has and(),or(),negate()
    //Predicate<Integer> graterThan0 = x->x>0;
    //Predicate<Integer> even =x->x%2==0;
    //Predicate<Integer> complex =graterThan0.and(even);

    //================================================================
    //Consumer
    //represent an operation that takes input and return nothing (void)
    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }
//    Consumer<String> print =name->System.out.println(name);
//    print.accept("ali");
    //Default method -> andThen()
//    Consumer<String>c1=x->System.out.println(x);
//    Consumer<String>c2=x->System.out.println(x.length());
//    c1.andThen(c2).accept("java");

    //================================================================
    //Function<T,R> Represents transformation.
    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }
//    Function<String, Integer> length = s -> s.length();
//    System.out.println(length.apply("Java"));//output :4

    //Default method
    //andThen(),compose();

//    Function<Integer, Integer> multiply = x -> x * 2;
//    Function<Integer, Integer> add = x -> x + 3;

//    Function<Integer, Integer> combined =multiply.andThen(add);
//    System.out.println(combined.apply(5));//5 → *2 → 10 → +3 → 13

    //================================================================

    //BiFunction<T,U,R> takes two input return one output

    @FunctionalInterface
    public interface BiFunction<T, U, R> {
        R apply(T t, U u);
    }
//    BiFunction<Integer,Integer,Integer> sum=(a,b)->a+b;
//    System.out.println(sum.apply(5, 3)); // 8

    //================================================================
    //Runnable takes no input return nothing
    @FunctionalInterface
    public interface Runnable {
        void run();
    }
    //before java8
//      new Thread(new Runnable() {
//        @Override
//        public void run() {
//            System.out.println("Thread running");
//        }
//      }).start();

    //java8
    // new Thread(() -> System.out.println("Thread running")).start();

    // ================================================================
    //Callable<V>

//    @FunctionalInterface
//    public interface Callable<V> {
//        V call() throws Exception;
//    }
//            | Runnable             | Callable                    |
//            | -------------------- | --------------------------- |
//            | No return value      | Returns value               |
//            | No checked exception | Can throw checked exception |

    // Callable<Integer> task=()->{return 10;};

    // ================================================================

    //Streams (uses all above interfaces )
    List<String> names = Arrays.asList("Ali", "Omar", "Ahmad");
    List<Integer>lenghts=names.stream()
            .filter(name->name.startsWith("A"))
            .map(name->name.length())
            .collect(Collectors.toList());

    // ================================================================
    //Method References
//    Instead of:
//    x -> System.out.println(x)
//
//    Use:
//    System.out::println

// ================================================================

//    Optional (Null Safety)
//    Purpose: Avoid NullPointerException.
// Optional<String> name = Optional.ofNullable(null);
// System.out.println(name.orElse("Default"));
//    Important methods:
//    map()
//    flatMap()
//    orElse()
//    orElseGet()
//    orElseThrow()






}

