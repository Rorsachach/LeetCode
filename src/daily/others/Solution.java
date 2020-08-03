package daily.others;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: 卑微小冯
 * time: 2020/8/3
 */

public class Solution {
    public static int[] arr = new int[117];
    public static void main(String[] args) throws IOException {
        int len = creatArr(arr);
        int end = 799179;
        int cnt = 0;
        for (int i = 233; i < end; i++) {
            boolean flag = true;
            int num = i;
            while (flag){
                flag = false;
                int j = 0;
                for (; j < len; j++) {
                    if(num%arr[j]==0){
                        if(num/arr[j]!=1){
                            flag = true;
                            num /= arr[j];
                        }
                        break;
                    }
                }
                if(!flag && j == len){
                    System.out.println(i);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
    public static int creatArr (int[] arr){
        int cnt = 0;
        for (int i = 2; i < 233; i++) {
            if(isSu(i))
                arr[cnt++] = i;
        }
        return cnt;
    }
    public static boolean isSu(int num){
        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if(num % i == 0)
                return false;
        }
        return true;
    }
}
