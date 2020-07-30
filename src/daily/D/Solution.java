package daily.D;

/**
 * @auther: 卑微小冯
 * @date: 2020/7/30 14:39
 */

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Solution {
    public static void main(String[] args) {
        for (int i = 0; i < 58; i++) {
            System.out.println(i+": "+integerBreak(i)+" "+integerBreak2(i));
        }
        long curt = System.currentTimeMillis();
        for (int i = 0; i < 58; i++) {
            integerBreak(i);
        }
        System.out.println("time of first: "+(System.currentTimeMillis() - curt));
        curt = System.currentTimeMillis();
        for (int i = 0; i < 58; i++) {
            integerBreak2(i);
        }
        System.out.println("time of second: "+(System.currentTimeMillis() - curt));
    }

    public static int integerBreak(int n) {
        int[] list = new int[n + 1];
        for (int i = 2; i < n + 1; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                max = Math.max(max, Math.max(j * (i - j), j * list[i - j]));
            }
            list[i] = max;
        }
        return list[n];
    }
    public static int integerBreak2(int n) {
        int max = 0;
        for (int i = 2; i <= n; i++) {
            int tmp = n%i;
            int sum = 1;
            for (int j = 0; j < i-tmp; j++) {
                sum *= n/i;
            }
            for (int j = 0; j < tmp; j++) {
                sum *= n/i + 1;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
