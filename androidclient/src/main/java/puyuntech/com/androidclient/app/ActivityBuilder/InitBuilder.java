package puyuntech.com.androidclient.app.ActivityBuilder;

/**
 * @作者 Administrator
 * @创建时间 2016-04-25 上午 10:53
 * @描述 初始化activity、fragment类接口【建造者】
 * @修改时间 2016-04-25 上午 10:53
 * @修改描述
 * @修改者 zx
 **/
public interface InitBuilder {

    /**
     * 初始化一个act
     *
     * @param builder
     */
    void initAct(BuildHelper builder);

    /**
     * 初始化一个fragment
     *
     * @param builder
     */
    void initFragment(BuildHelper builder);
}
