package daily.A;

/**
 * @auther: 卑微小冯
 * @date: 2020/7/27 16:15
 */

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 *
 * 返回 true.
 *
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 *
 * 返回 false.
 *
 * 后续挑战 :
 *
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢:
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class One {
    public static void main(String[] args) {
        System.out.println(isSubsequence3("abc","ahbgdc"));
    }
    public static boolean isSubsequence(String s, String t){
        char[] sArray = s.toCharArray();
        int index = -1;
        for (int i = 0; i < sArray.length; i++) {
            index = t.indexOf(sArray[i], index+1);
            if(index == -1)
                return false;
        }
        return true;
    }
    public static boolean isSubsequence2(String s, String t){
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        int i=0,j=0;
        for(;i<sArray.length&&j<tArray.length;){
            if(sArray[i]==tArray[j]){
                i++;j++;
            }else
                j++;
        }
        if(i==sArray.length)
            return true;
        return false;
    }
    public static boolean isSubsequence3(String s, String t){
        if(s.length()==0)
            return true;
        if(t.length()==0)
            return false;
        return  s.charAt(0) == t.charAt(0)?isSubsequence3(s.substring(1),t.substring(1)):isSubsequence3(s,t.substring(1));
    }
}
