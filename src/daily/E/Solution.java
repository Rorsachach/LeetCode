package daily.E;

import java.util.*;

/**
 *
 你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。

 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。

 示例 1:

 输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 输出: [20,24]
 解释:
 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 注意:

 给定的列表可能包含重复元素，所以在这里升序表示 >= 。
 1 <= k <= 3500
 -105 <= 元素的值 <= 105
 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动
 */

public class Solution {
    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        Integer[][] array = new Integer[][] {{-73,-70,15,36,51,52},{22,44,58,60,61},{-61,12,21,22,23,23,25,29},{-27,-12,1,4,18,18,18,19},{-1,57,58,60,69,72,73},{13,16,41,54,88,88,89,92},{-16,35,39,39,43,46},{-16,32,35,49,54,63},{25,49,61,68,71},{67,88,88,90,90,90,91}};
        for (int i = 0; i < array.length; i++) {
            lists.add(Arrays.asList(array[i]));
        }
        int[] res = new int[2];
        res = smallestRange(lists);
        System.out.println(res[0]+" "+res[1]);

//        Integer[] t = new Integer[] {1,2,3};
//        List<Integer> test = Arrays.asList(t);
//        System.out.println(BinarySearch(1, test));
    }
    public static int[] smallestRange(List<List<Integer>> nums) {
        // 先进行排序
        for (List<Integer> num : nums) {
            num.sort(Integer::compareTo);
            System.out.println(num);
        }

        int[] array = new int[2];

        int min = Integer.MAX_VALUE;
        int size = nums.size();
        for (int i = 0; i < size; i++) {
            List<Integer> tmp1 = nums.get(i);
            int upLimit = 0;
            int lowLimit = 0;
            int flag = 0;
            for (int j = 0; j < tmp1.size(); j++) {
                lowLimit = tmp1.get(j);
                upLimit = lowLimit;
                for (int k = 0; k < size; k++) {
                    if(k == i)
                        continue;
                    flag = BinarySearch(lowLimit, nums.get(k));
                    if(flag == Integer.MIN_VALUE)
                        break;
                    upLimit = Math.max(upLimit, flag);
                }
                if(flag == Integer.MIN_VALUE)
                    break;
                if(min > upLimit - lowLimit){
                    min = upLimit - lowLimit;
                    array[0] = lowLimit;
                    array[1] = upLimit;
                }
            }
        }

        return array;
    }

    private static int BinarySearch(int lowLimit, List<Integer> integers) {
        int bgn = 0, end = integers.size() -1;
        if(integers.get(end) < lowLimit)
            return Integer.MIN_VALUE;
        if(integers.get(bgn) > lowLimit)
            return integers.get(bgn);
        if(lowLimit == integers.get(bgn) || lowLimit == integers.get(end)) {
            return lowLimit;
        }
        while (end - bgn > 1){
            int mid = (bgn + end)/2;
            if(lowLimit > integers.get(mid)) {
                bgn = mid;
            }
            else {
                end = mid;
            }
        }
        return integers.get(end);
    }

    public int[] smallestRange2(List<List<Integer>> nums) {
        int rangeLeft = 0, rangeRight = Integer.MAX_VALUE;
        int minRange = rangeRight - rangeLeft;
        int max = Integer.MIN_VALUE;
        int size = nums.size();
        int[] next = new int[size];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer index1, Integer index2) {
                return nums.get(index1).get(next[index1]) - nums.get(index2).get(next[index2]);
            }
        });
        for (int i = 0; i < size; i++) {
            priorityQueue.offer(i);
            max = Math.max(max, nums.get(i).get(0));
        }
        while (true) {
            int minIndex = priorityQueue.poll();
            int curRange = max - nums.get(minIndex).get(next[minIndex]);
            if (curRange < minRange) {
                minRange = curRange;
                rangeLeft = nums.get(minIndex).get(next[minIndex]);
                rangeRight = max;
            }
            next[minIndex]++;
            if (next[minIndex] == nums.get(minIndex).size()) {
                break;
            }
            priorityQueue.offer(minIndex);
            max = Math.max(max, nums.get(minIndex).get(next[minIndex]));
        }
        return new int[]{rangeLeft, rangeRight};
    }
}
