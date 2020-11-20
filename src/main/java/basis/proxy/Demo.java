package basis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Demo {

    @SuppressWarnings("unchecked")
    public static <T> T withLogging(T target, Class<T> clazz) throws Exception {
        Class proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(), clazz);
        return (T) proxyClass.getConstructor(new Class[]{InvocationHandler.class}).newInstance(new Object[] {new LoggingHandler(target)});
    }

    @SuppressWarnings("unchecked")
    public static <T> T withLoggingBySimply(T target, Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] {clazz}, new LoggingHandler(target));
    }

    public static void main(String[]args) throws Exception {
        Person person = new Person();
        Human logged = withLoggingBySimply(person, Human.class);
        logged.talk();
        logged.walk();
        logged.walk();
        logged.walk();
        System.out.println(logged);
    }
}
