import java.util.Arrays;
import java.util.TreeSet;
import java.util.*;

public class Solution {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //前序+中序  数组
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        int rootValue = preorder[0];
        int leftCount;
        for (leftCount = 0; leftCount < inorder.length; leftCount++) {
            if (inorder[leftCount] == rootValue) {
                break;
            }
        }
        TreeNode root = new TreeNode(rootValue);
        int[] leftPreorder = Arrays.copyOfRange(preorder,1, 1 + leftCount);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, leftCount);
        root.left = buildTree(leftPreorder, leftInorder);
        int[] rightPreorder = Arrays.copyOfRange(preorder,
                1 + leftCount, preorder.length);
        int[] rightInorder = Arrays.copyOfRange(inorder,
                leftCount + 1, inorder.length);
        root.right = buildTree(rightPreorder, rightInorder);

        return root;
    }

    //前序+中序  List
    TreeNode buildTree3(List<Integer> preorder, List<Integer> inorder) {
        if (preorder.isEmpty()) {
            return null;
        }

        int rootValue = preorder.get(0);
        TreeNode root = new TreeNode(rootValue);
        int leftCount = inorder.indexOf(rootValue);

        List<Integer> leftPreorder = preorder.subList(1, 1 + leftCount);
        List<Integer> leftInorder = inorder.subList(0, leftCount);
        root.left = buildTree3(leftPreorder, leftInorder);
        List<Integer> rightPreorder = preorder.subList(1 + leftCount, preorder.size());
        List<Integer> rightInorder = inorder.subList(1 + leftCount, preorder.size());
        root.right = buildTree3(rightPreorder, rightInorder);

        return root;
    }

    //中序+后序  数组
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        int rootValue = postorder[postorder.length - 1];
        int leftCount = indexOf(inorder, rootValue);
        TreeNode root = new TreeNode(rootValue);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, leftCount);
        int[] leftPostorder = Arrays.copyOfRange(postorder, 0, leftCount);
        root.left = buildTree2(leftInorder, leftPostorder);
        int[] rightInorder = Arrays.copyOfRange(inorder, leftCount + 1, inorder.length);
        int[] rightPostorder = Arrays.copyOfRange(postorder, leftCount, postorder.length - 1);
        root.right = buildTree2(rightInorder, rightPostorder);

        return root;
    }
    private int indexOf(int[] a, int r) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == r) {
                return i;
            }
        }

        return -1;
    }

    //中序+后序  List
    TreeNode buildTree4(List<Integer> inorder, List<Integer> postorder){
        if (inorder.isEmpty()) {
            return null;
        }
        int rootValue = postorder.get(postorder.size()- 1);
        int leftCount = inorder.indexOf(rootValue);
        TreeNode root = new TreeNode(rootValue);
        List<Integer> leftInorder = inorder.subList(0, leftCount);
        List<Integer> leftPostorder = postorder.subList(0, leftCount);
        root.left = buildTree4(leftInorder, leftPostorder);
        List<Integer> rightInorder = inorder.subList(leftCount + 1, inorder.size());
        List<Integer> rightPostorder = postorder.subList(leftCount, postorder.size() - 1);
        root.right = buildTree4(rightInorder, rightPostorder);

        return root;
    }
}
