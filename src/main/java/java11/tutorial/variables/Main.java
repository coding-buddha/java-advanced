package java11.tutorial.variables;

public class Main {

    /**
     * project jdk 버전 11 로 업,
     * java compiler  버전도 11 로 업.
     */
    public static void main(String[] args) {
        String text = "Hello World";

        var variable = "Hello World";

        System.out.println("text : " + text);
        System.out.println("variable : " + variable);
        System.out.println("typeChecking() : " + typeChecking(text, variable));

        // 다른 타입의 값은 재할당 불가.
        // variable = 5;

        //
        variable = "Hello Java";
    }

    public static boolean typeChecking(final String text, final String var) {
        return text.equals(var);
    }


}
