package java8.functional.ex01;

@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
