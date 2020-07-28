package daily.one;

/**
 * @auther: 卑微小冯
 * @date: 2020/7/27 16:15
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
