package daily.G;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: 卑微小冯
 * time: 2020/8/3
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(addStrings("99","9"));
    }
    public static String addStrings(String num1, String num2) {
        int len = num1.length() > num2.length() ? num1.length() : num2.length();
        while (num1.length()<len)
            num1 = "0"+num1;
        while (num2.length()<len)
            num2 = "0"+num2;
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            int n1 = num1.charAt(len - 1- i)-'0';
            int n2 = num2.charAt(len - 1- i)-'0';
            if(list.size()<i+1){
                list.add((n1+n2)%10);
                list.add((n1+n2)/10);
            }
            else {
                int tmp = list.get(i);
                list.set(i,(n1+n2+tmp)%10);
                list.add((n1+n2+tmp)/10);
            }
        }

        String s = "";
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            if(i==0 && list.get(i)==0)
                continue;
            s = s+list.get(i).toString();
        }

        return s;
    }
}
