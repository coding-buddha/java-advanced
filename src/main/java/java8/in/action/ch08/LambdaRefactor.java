package java8.in.action.ch08;

public class LambdaRefactor {

    public static void main(String[] args) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("run !");
            }
        };

        final Runnable refactorRunnable = () -> System.out.println("refactor Run !");

        runnable.run();
        refactorRunnable.run();

        doSomething((Runnable) () -> System.out.println(">>"));
    }

    public static void doSomething(Task task){
        task.execute();
    }

    public static void doSomething(Runnable runnable){
        runnable.run();;
    }
}

interface Task{
    public void execute();
}
