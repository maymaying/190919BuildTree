import java.util.*;
public class BuildTree {
    private static class Node {
        private char val;
        private Node left = null;
        private Node right = null;
        private Node(char val) {
            this.val = val;
        }
    }
    private static class RV {
        private Node root;
        private int used;
        private RV(Node root, int used) {
            this.root = root;
            this.used = used;
        }
    }

    //数组
    private static RV buildTree(char[] preorder) {
        if (preorder.length == 0) {
            return new RV(null, 0);
        }
        if (preorder[0] == '#') {
            return new RV(null, 1);
        }
        Node root = new Node(preorder[0]);
        RV left = buildTree(Arrays.copyOfRange(preorder, 1, preorder.length));
        RV right = buildTree(Arrays.copyOfRange(preorder, 1 + left.used, preorder.length));
        root.left = left.root;
        root.right = right.root;
        return new RV(root, 1 + left.used + right.used);
    }

    private static void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }

    //List
    //如何只用前序遍历（带空结点的）创建二叉树
    // Build Tree Return Value
    private static class BTRV {
        private Node root;
        private int used;
    }
    BTRV buildTree5(List<Integer> preorder) {
        BTRV returnValue = new BTRV();
        if (preorder.size() == 0) {
            returnValue.root = null;
            returnValue.used = 0;
            return returnValue;
        }
        int rootValue = preorder.get(0);
        if (rootValue == '#') {
            returnValue.root = null;
            returnValue.used = 1;
            return returnValue;
        }
        BTRV leftReturn = buildTree5(preorder.subList(1, preorder.size()));
        BTRV rightReturn = buildTree5(preorder.subList(1 + leftReturn.used,
                preorder.size()));
        Node root = new Node((char) rootValue);
        root.left = leftReturn.root;
        root.right = rightReturn.root;
        returnValue.root = root;
        returnValue.used = 1 + leftReturn.used + rightReturn.used;
        return returnValue;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        char[] charArray = line.toCharArray();
        RV rv = buildTree(charArray);
        inorderTraversal(rv.root);
    }
}
