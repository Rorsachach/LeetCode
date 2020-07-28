package daily.two;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @auther: 卑微小冯
 * @date: 2020/7/28 14:53
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

public class Solution {
    public int deep = 0;

    public static void main(String[] args) {

    }
    public int maxDepth(TreeNode root) {
        toDeep(0, root);
        return this.deep;
    }

    public void toDeep(int dp, TreeNode node){
        if(node!=null){
            dp++;
            if(dp>deep)
                deep = dp;
            toDeep(dp, node.left);
            toDeep(dp, node.right);
        }
    }

    public int maxDepth2(TreeNode root){
        if(root==null)
            return 0;
        return 1+Math.max(maxDepth2(root.left), maxDepth2(root.right));
    }

    public int maxDepth3(TreeNode root){
        if(root==null)
            return 0;
        int deep = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                TreeNode node = queue.poll();
                if(node.left!=null)
                    queue.offer(node.left);
                if(node.right!=null)
                    queue.offer(node.right);
            }
            deep++;
        }
        return deep;
    }
}
