package daily.F;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: 卑微小冯
 * time: 2020/8/2
 */

public class Solution {
    public static void main(String[] args) {
        int[] ap = {1,2,5,3,4,0,6};
        flatten2(createTree(ap));
    }

    public void flatten(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        insert(root, list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode prev = list.get(i-1);
            TreeNode curr = list.get(i);
            prev.right = curr;
            prev.left = null;
        }
    }
    public void insert(TreeNode node, List<TreeNode> list){
        if(node == null)
            return;
        list.add(node);
        insert(node.left, list);
        insert(node.right, list);
    }

    public static void flatten2(TreeNode root){
        rebuild(root);
    }
    public static void rebuild(TreeNode node){
        if(node == null)
            return;
        TreeNode left = node.left;
        TreeNode right = node.right;
        rebuild(left);
        rebuild(right);
        if(left == null)
            return;
        if(right == null){
            node.left = null;
            node.right = left;
            return;
        }
        TreeNode tmp = left.right;
        if(tmp != null){
            while (tmp.right != null)
                tmp = tmp.right;
            tmp.right = right;
        }else
            left.right = right;
        node.left = null;
        node.right = left;
    }
    public static TreeNode createTree(int[] a){
        TreeNode root = new TreeNode(a[0]);
        root.left = new TreeNode(a[1],new TreeNode(a[3]), new TreeNode(a[4]));
        root.right = new TreeNode(a[2], null, new TreeNode(a[6]));
        return root;
    }
}
