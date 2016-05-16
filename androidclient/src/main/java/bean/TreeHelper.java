package bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import puyuntech.com.androidclient.R;

/**
 * http://blog.csdn.net/lmj623565791/article/details/40212367
 *
 * @author zhy
 */
public class TreeHelper {
    /**
     * 传入我们的普通bean，转化为我们排序后的Node
     *
     * @param datas
     * @param defaultExpandLevel
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T> List<bean.Node> getSortedNodes(List<T> datas,
                                                     int defaultExpandLevel) throws IllegalArgumentException,
            IllegalAccessException

    {
        List<bean.Node> result = new ArrayList<bean.Node>();
        // 将用户数据转化为List<Node>
        List<bean.Node> nodes = convetData2Node(datas);
        // 拿到根节点
        List<Node> rootNodes = getRootNodes(nodes);
        // 排序以及设置Node间关系
        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        return result;
    }

    /**
     * 过滤出所有可见的Node
     *
     * @param nodes
     * @return
     */
    public static List<bean.Node> filterVisibleNode(List<bean.Node> nodes) {
        List<bean.Node> result = new ArrayList<bean.Node>();

        for (bean.Node node : nodes) {
            // 如果为跟节点，或者上层目录为展开状态
            if (node.isRoot() || node.isParentExpand()) {
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 将我们的数据转化为树的节点
     *
     * @param datas
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private static <T> List<bean.Node> convetData2Node(List<T> datas)
            throws IllegalArgumentException, IllegalAccessException

    {
        List<bean.Node> nodes = new ArrayList<bean.Node>();
        bean.Node node = null;

        for (T t : datas) {
            String id = null;
            //int pId = -1;
            String pId = null;
            String label = null;
            String phone = null;
            String url = null;
            boolean isRxpand = false;
            int type = -1;
            Class<? extends Object> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field f : declaredFields) {
                if (f.getAnnotation(TreeNodeId.class) != null) {
                    f.setAccessible(true);
                    id = (String) f.get(t);
                }
                if (f.getAnnotation(TreeNodePid.class) != null) {
                    f.setAccessible(true);
                    pId = (String) f.get(t);
                }
                if (f.getAnnotation(TreeNodeLabel.class) != null) {
                    f.setAccessible(true);
                    label = (String) f.get(t);
                }
                if (f.getAnnotation(TreeNodeTel.class) != null) {
                    f.setAccessible(true);
                    phone = (String) f.get(t);
                }
                if (f.getAnnotation(TreeNodeUrl.class) != null) {
                    f.setAccessible(true);
                    url = (String) f.get(t);
                }
                if (f.getAnnotation(TreeNodeType.class) != null) {
                    f.setAccessible(true);
                    type = f.getInt(t);
                }
                if (f.getAnnotation(TreeNodeIsExpand.class) != null) {
                    f.setAccessible(true);
                    isRxpand = f.getBoolean(t);
                }
                if (id != null && pId != null && label != null && label != null && url != null && type != -1) {
                    break;
                }
            }
            node = new bean.Node(id, pId, label, phone, url, type, isRxpand);
            nodes.add(node);
        }

        /**
         * 设置Node间，父子关系;让每两个节点都比较一次，即可设置其中的关系
         */
        for (int i = 0; i < nodes.size(); i++) {
            bean.Node n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                bean.Node m = nodes.get(j);
                if (m.getpId().equals(n.getId() + "")) {
                    n.getChildren().add(m);
                    m.setParent(n);
                } else if ((m.getId() + "").equals(n.getpId())) {
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }

        // 设置图片
        for (bean.Node n : nodes) {
            setNodeIcon(n);
        }
        return nodes;
    }

    private static List<bean.Node> getRootNodes(List<bean.Node> nodes) {
        List<bean.Node> root = new ArrayList<bean.Node>();
        for (bean.Node node : nodes) {
            if (node.isRoot())
                root.add(node);
        }
        return root;
    }

    /**
     * 把一个节点上的所有的内容都挂上去
     */
    private static void addNode(List<bean.Node> nodes, bean.Node node,
                                int defaultExpandLeval, int currentLevel) {

        nodes.add(node);
        if (defaultExpandLeval >= currentLevel) {
            node.setExpand(node.isExpand());
        }else {
            node.setExpand(false);

        }

        if (node.isLeaf())
            return;
        for (int i = 0; i < node.getChildren().size(); i++) {
            addNode(nodes, node.getChildren().get(i), defaultExpandLeval,
                    currentLevel + 1);
        }
    }

    /**
     * 设置节点的图标
     *
     * @param node
     */
    private static void setNodeIcon(bean.Node node) {
        if (node.getChildren().size() > 0 && node.isExpand()) {
            node.setIcon(R.mipmap.ic_addressbook_arrow_open);
        } else if (node.getChildren().size() > 0 && !node.isExpand()) {
            node.setIcon(R.mipmap.ic_addressbook_arrow_close);
        } else
            node.setIcon(-1);

    }

}
