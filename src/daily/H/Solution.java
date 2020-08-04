package daily.H;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: 卑微小冯
 * time: 2020/8/4
 */

public class Solution {
    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{{1,0},{2,1}};
        System.out.println(canFinish(3,prerequisites));
    }
    static class Nodes{
        int situation; // 0,1,2
        int var;
        List<Nodes> list;

        public Nodes(int var) {
            this.var = var;
            this.situation = 0;
            this.list = new LinkedList<>();
        }
    }
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Nodes> list = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            list.add(new Nodes(i));
        }
        for (int[] prerequisite : prerequisites) {
            list.get(prerequisite[0]).list.add(list.get(prerequisite[1]));
        }
        while (!list.isEmpty()){
            if(!canPull(list.get(0), list))
                return false;
        }
        return true;
    }

    private static boolean canPull(Nodes node, List<Nodes> list) {
        boolean flag = true;
        if(!node.list.isEmpty()){
            node.situation = 1;
            for (Nodes nodes : node.list) {
                if(nodes.situation == 1)
                    return false;
                flag = canPull(nodes, list);
                if(!flag)
                    break;
            }
        }
        if(!flag)
            return false;
        list.remove(node);
        node.situation = 2;
        return true;

    }
}
