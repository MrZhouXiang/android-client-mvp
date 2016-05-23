package puyuntech.com.androidclient.app.ActivityBuilder;


import puyuntech.com.androidclient.app.NetBroadcastReceiver;

/**
 * 作者 Administrator
 * 创建时间 2016/4/21 0021
 * 描述 activity初始化基础接口，用于规范activity的编码规范
 * 修改时间 2016/4/21 0021
 * 修改描述
 * 修改者 Administrator
 **/
public interface BuildHelper extends InitHelper, ViewShowHelper, ListenerHelper, NetBroadcastReceiver.netEventHandler {


}
