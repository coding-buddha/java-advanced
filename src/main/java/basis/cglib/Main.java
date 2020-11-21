package basis.cglib;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Cat.class);
//        enhancer.setInterfaces(new Class<?>[] {Animal.class});

        // 콜백 다양하게 적용하기
//        enhancer.setCallback(new CustomMethodInterceptor());
//        enhancer.setCallback(new CustomLazyLoader(Cat.class));
        enhancer.setCallback(new CustomInvocationHandler(Cat.class));
//        enhancer.setCallback(new CustomFixedValue());

        Object proxy = enhancer.create();
        Animal cat = (Animal) proxy;
        cat.cry();
        cat.cry();
    }
}

// LazyLoader
class CustomLazyLoader implements LazyLoader {
    private final Class<?> target;
    public CustomLazyLoader(Class<?> target) {
        this.target = target;
    }

    @Override
    public Object loadObject() throws Exception {
        return target.newInstance();
    }
}

// InvocationHandler
class CustomInvocationHandler implements InvocationHandler {

    private final Class<?> target;

    public CustomInvocationHandler(Class<?> target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[callback-InvocationHandler] Meow! Meow!");
        return method.invoke(target.newInstance(), args);
    }
}

// MethodInterceptor
class CustomMethodInterceptor implements MethodInterceptor {
    /**
     * 
     * @param obj       프록시 객체
     * @param method    호출 메소드
     * @param args      호출 시 전달받은 인자
     * @param proxy     대상 객체 메소드를 호출할 때 사용
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        System.out.println("before method calling");
        Object result = proxy.invokeSuper(obj, args);   // setSuperclass() 로 지정한 클래스의 메소드 호출
        System.out.println("after method called");
        return result;

        // Object result = proxy.invoke(obj, args);        // obj 를 사용해서 값을 넣으면 재귀가 발생함
    }
}

// FixedValue
class CustomFixedValue implements FixedValue {
    @Override
    public Object loadObject() throws Exception {
        System.out.println("[callback-FixedValue] Meow! Meow!");
        return null;
    }
}
