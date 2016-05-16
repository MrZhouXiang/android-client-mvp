package puyuntech.com.beihai.http.httpContor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @作者 zx
 * @创建时间 2016-04-19 下午 14:11
 * @描述 动态代理
 * @修改时间 2016-04-19 下午 14:11
 * @修改描述
 * @修改者 zx
 **/
public class DynamicProxy<T> {
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces,
                                         InvocationHandler invocationHandler) {

        return (T) Proxy.newProxyInstance(loader, interfaces,
                invocationHandler);
    }
}
