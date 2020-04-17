package java8.functional;

@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
