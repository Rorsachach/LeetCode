package daily.B;

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

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
