package com.luoqi.LeetCode;

import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    //初始化main方法
    public static void main(String[] args) {
        Solution solution=new Solution();
        solution.myAtoi("2000000000000000000000");
        System.out.println("end");
    }


    //链表节点定义
    static class ListNode {
    int val;
      ListNode next;
     ListNode(int x) { val = x; }
  }

    //第2题两数相加

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp=0;
        int odd=0;
        ListNode head=new ListNode(-1);
        ListNode result=head;
            while (l1!=null&&l2!=null){
                temp=l1.val+l2.val+odd;
                if (temp>=10){
                    odd=1;
                    temp-=10;
                }else {
                    odd=0;
                }
                head.next=new ListNode(temp);
                l1=l1.next;
                l2=l2.next;
                head=head.next;
            }
            while (l1==null&&l2!=null){
                temp=l2.val+odd;
                if (temp>=10){
                    odd=1;
                    temp-=10;
                }else {
                    odd=0;
                }
                head.next=new ListNode(temp);


                l2=l2.next;
                head=head.next;
            }
            while (l1!=null&&l2==null){
                temp=l1.val+odd;
                if (temp>=10){
                    odd=1;
                    temp-=10;
                }else {
                    odd=0;
                }
                head.next=new ListNode(temp);
                odd=0;
                l1=l1.next;

                head=head.next;
            }
            if (l1==null&&l2==null&&odd!=0){
                head.next=new ListNode(odd);
                odd=0;
            }

            return result.next;
    }
    //第3题无重复最长子串
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        int maxLength=0;
        ArrayList<Character> temp=new ArrayList<>();

        for (int i=0;i<s.length()-1;i++){
            temp.add(s.charAt(i));
            for (int j=i+1;j<s.length();j++) {
                char c = s.charAt(j);
                if (!temp.contains(c)) {
                    temp.add(c);
                }else
                {
                    if (maxLength<temp.size()) maxLength=temp.size();
                    temp.clear();
                    break;
                }
            }
        }
        return maxLength;
    }
    //第5题 最大回文子串
    public String longestPalindrome(String s) {
        int sIndex=0;
        int maxLength=0;
        for (int i=0;i<s.length();i++){
            for (int j=i+1;j<s.length();j++){

                    if (isPalindrome(s.substring(i,j+1))){
                        if (j-i>maxLength) {
                            maxLength=j-i;
                            sIndex=i;
                        }
                    }

            }
        }
        return s.substring(sIndex,sIndex+maxLength+1);
    }
    public static Boolean isPalindrome(String s){
        int n=s.length();
        if (n==1) return true;
        else if(n==2){
            if (s.charAt(0)==s.charAt(n-1))
                return true;
            else
                return false;
        }
        else if(n>=3) {
            if(s.charAt(0)==s.charAt(n-1))
                return isPalindrome(s.substring(1,n-1));
            else return false;
        }
        return false;
    }

    //第6题 Z字形变化
    public String convert(String s, int numRows) {
        if (numRows<2) return s;
        List<StringBuilder> list=new ArrayList<>();
        for (int i=0;i<numRows;i++){
            list.add(new StringBuilder());
        }
        int temp=0;
        int flag=-1;
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            list.get(temp).append(c);
            if (temp==0||temp==numRows-1) flag=-flag;
            temp+=flag;
        }
        StringBuilder res=new StringBuilder();
        for (StringBuilder temps:list) res.append(temps);
        return res.toString();
    }
    //第7题 整数反转
    public int reverse(int x) {
        if (x>=0&x<10) return x;
        StringBuilder stringBuilder=new StringBuilder(String.valueOf(x));
        stringBuilder.reverse();
        int flag=1;
        if (stringBuilder.charAt(stringBuilder.length()-1)=='-') {
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            flag=-1;
        }
        while (stringBuilder.charAt(0)=='0') {
            stringBuilder.deleteCharAt(0);
        }
        if (flag == -1) {
            stringBuilder.insert(0,'-');
        }

//        System.out.println(stringBuilder.toString());
        BigInteger a=new BigInteger(stringBuilder.toString());
        int res=0;
        if (a.compareTo(BigInteger.valueOf(0))>0) res=a.compareTo(BigInteger.valueOf(Integer.MAX_VALUE))>0? 0:Integer.parseInt(stringBuilder.toString());

        else res=a.compareTo(BigInteger.valueOf(Integer.MIN_VALUE))<0? 0:Integer.parseInt(stringBuilder.toString());
       return res;
    }
    public int myAtoi(String str) {
        if (str.length()==0) return 0;
        int res=0;

        int k=0;
        while (k<str.length()&&str.charAt(k)==' '){
            k++;
        }
        if (k<str.length()) str=str.substring(k,str.length());
        else return 0;
        char first=str.charAt(0);
        if (first=='+'||first=='-'){
            res=myAtoiSymbolHelper(first,str);
        }else if (first>='0'&&first<='9'){
            res=myAtoiNumberHelper(str);
        }else  return 0;
//        System.out.println("run");
        return res;
    }
    public static int myAtoiSymbolHelper(char first,String str){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(first);
        char c=' ';
        for (int i=1;i<str.length();i++){
            c=str.charAt(i);
            if (c>='0'&&c<='9') {
                stringBuilder.append(c);
            }else break;
        }
        if (stringBuilder.length()>1) {
            long res = Long.parseLong(stringBuilder.toString());
            if (res > (long) Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (res < (long) Integer.MIN_VALUE) return Integer.MIN_VALUE;
            return (int)res;
        }else return 0;


    }
    public static int myAtoiNumberHelper(String str){
        StringBuilder stringBuilder=new StringBuilder();
        char c=' ';
        for (int i=0;i<str.length();i++){
            c=str.charAt(i);
            if (c>='0'&&c<='9') {
                stringBuilder.append(c);
            }else break;
        }
       
        long res=Long.parseLong(stringBuilder.toString());
        if (res>(long)Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (res<(long)Integer.MIN_VALUE) return Integer.MIN_VALUE;


        return (int)res;

    }

}
