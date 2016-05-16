package puyuntech.com.androidclient.model;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 15:20
 * @描述 ListItemModel
 * @修改时间 2016-05-16 下午 15:20
 * @修改描述
 * @修改者 Administrator
 **/
public class ListItemModel {
    public Enum type;
    public String value;

    public ListItemModel(Enum type, String value) {
        this.type = type;
        this.value = value;
    }
}
