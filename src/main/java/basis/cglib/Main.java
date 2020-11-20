package basis.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class Main {
    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();

        // 프록시 대상을 지정
        enhancer.setSuperclass(Cat.class);
        
        // 인터페이스 지정
        enhancer.setInterfaces(new Class<?>[] {Animal.class});
        enhancer.setCallback(NoOp.INSTANCE);
    
        // proxy 생성
        Object proxy = enhancer.create();

        Animal cat = (Animal) proxy;
        cat.cry();
    }
}
