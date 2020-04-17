package java8.functional;

public class Example01 {

    private static final String SPACE = " ";

    public static void main(String[]args){
        Example01 example01 = new Example01();

        // 제네릭 메소드를 이용, 타입추론.
        String line = example01.concat("Hello", "World", (t, u) -> t.concat(SPACE).concat(u));
        System.out.println(line);

        // 메소드 참조 이용
        Integer addValue = example01.add(10, 20, Integer::sum);
        System.out.println(addValue);
    }

    <T, U> String concat(T t, U u, BiFunction<T, U, String> function){
        return function.apply(t, u);
    }

    <T, U> Integer add(T t, U u, BiFunction<T, U, Integer> function){
        return function.apply(t, u);
    }
}
