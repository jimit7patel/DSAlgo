package com.epi;

public class OddEvenLinkedlist{

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode oddEvenList(ListNode head) {
            if(head == null)
                return null;
            ListNode odd = head;
            ListNode even = head.next;
            ListNode evenFirst = head.next;
            
            while(true){
                if(odd == null || even == null || even.next == null){
                    odd.next = evenFirst;
                    return head;
                }
                
                odd.next = even.next;
                odd = odd.next;
                
                if(odd.next == null){
                    even.next = null;
                    odd.next = evenFirst;
                    return head;
                }
                
                even.next = odd.next;
                even = even.next;
                
                
            }
            
        }
    }


    public static void main(String[] args){
        //new MostCommonSubstring().findWords(s1,s2);

    }
}