package puyuntech.com.beihai.http.httpContor.base;


/**
 * 作者 zx
 * 创建时间 2016/4/19 0019
 * 描述 请求工厂
 * 修改时间 2016/4/19 0019
 * 修改描述 请求工厂
 * 修改者 zx
 **/
public abstract class HttpFactory {
    /**
     * 不是接口异常
     */
    public class NotInterFaceException extends Exception {
        public NotInterFaceException(String msg) {
            super(msg);
        }
    }

    /**
     * 获取http请求
     *
     * @param c   请求类型
     * @param <T> 必须为请求的接口类型
     * @return c类型的实现类
     * @throws NullPointerException 当c的类型不为接口时候会抛出空指针异常
     */
    public abstract <T> T getHttpByMethod(Class<T> c) throws NotInterFaceException;

    /**
     * 获取http请求
     *
     * @param c         请求类型
     * @param needProxy 是否需要代理
     * @param <T>       必须为请求的接口类型
     * @return c类型的实现类
     * @throws NullPointerException 当c的类型不为接口时候会抛出空指针异常
     */
    public abstract <T> T getHttpByMethod(Class<T> c, boolean needProxy) throws NotInterFaceException;


}
