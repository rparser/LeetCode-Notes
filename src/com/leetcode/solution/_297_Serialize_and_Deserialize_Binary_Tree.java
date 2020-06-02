/**
 * 思路： use 2 string marker: "X" for null - empty node, "," for spliter - spliting nodes
 * To Serialize, do pre-order traversal. Append node.val(sb可直接append数字) + spliter
 * to StringBuilder recursively.
 * To Deserialize, create a queue to add nodes from string(need to 1st split 2nd convert to arraylist) .
 * Then recursively build the tree by pointing left & right children.
 * Complexity: O(N) time travel all nodes
 */

public class Codec {
    //BFS
    private final String nullN = "null";
    private final String spliter = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(nullN).append(spliter);
            return;
        }
        sb.append(node.val).append(spliter);
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    // Decodes your encoded data to tree.
    //将此时数据二叉树的先序遍历结果依次压入队列
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<String>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Queue<String> nodes) {
        String val = nodes.remove();//return null
        if (val.equals(nullN))
            return null;
        //若节点值不为空，将其由String转换回int
        //将其作为当前节点值新建当前节点
        TreeNode node = new TreeNode(Integer.valueOf(val));//Integer.parseInt()
        //递归调用连接当前节点的左右节点
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        return node;
    }
}