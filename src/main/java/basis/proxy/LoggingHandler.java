package basis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoggingHandler implements InvocationHandler {

    private final Object target;
    private final Map<String, Integer> calls = new HashMap<>();

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final String name = method.getName();

        /** 필요 기능 구현 가능 **/
        if(name.contains("toString")){
            return calls.toString();
        }

        calls.merge(name, 1, Integer::sum);

        /** 타겟에 대한 메소드가 호출된다. **/
        return method.invoke(target, args);
    }
}
