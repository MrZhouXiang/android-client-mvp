package bean;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;

import android.util.Log;

/**
 * http://blog.csdn.net/lmj623565791/article/details/40212367
 *
 * @author zhy
 */
public class Node {

    private String id;
    /**
     * 根节点pId为0
     */
    private String pId = "0";

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    int type;

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    int idCode;

    private String name;


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    private String tel;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    /**
     * 当前的级别
     */
    private int level;

    /**
     * 是否展开
     */
    public boolean isExpand = false;

    private int icon;

    /**
     * 下一级的子Node
     */
    private List<Node> children = new ArrayList<Node>();

    /**
     * 父Node
     */
    private Node parent;

    public Node() {
    }

    public Node(String id, String pId, String name, String phone, String url, int type, boolean isExpand) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.tel = phone;
        this.url = url;
        this.type = type;
        this.isExpand = isExpand;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * 是否为跟节点
     *
     * @return
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * 判断父节点是否展开
     *
     * @return
     */
    public boolean isParentExpand() {
        if (parent == null)
            return false;
        return parent.isExpand();
    }

    /**
     * 是否是叶子界点
     *
     * @return
     */
    public boolean isLeaf() {
        return children.size() == 0;
    }

    /**
     * 获取level
     */
    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }

    /**
     * 设置展开
     *
     * @param isExpand
     */
    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
        if (!isExpand) {

            for (Node node : children) {
                node.setExpand(isExpand);
            }
        }
    }

}
