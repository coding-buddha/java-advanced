package java8.in.action.ch03;

public class LambdaCapturing {

    private int number = 5;

    public static void main(String[]args) {

        LambdaCapturing lambdaCapturing = new LambdaCapturing();
//        lambdaCapturing.bar();
    }

//    private void bar(){
//        int localNumber = 10;
//        Runnable r = () -> {
//            try {
//                Thread.sleep(5000);
//                localNumber++;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//        r.run();
//    }

    private void foo(){
        // 인스턴스 변수를 increase 시킨다.
        Runnable r = () -> System.out.println(number++);
        r.run();
    }
}
