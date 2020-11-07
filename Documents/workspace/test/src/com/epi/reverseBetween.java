package com.epi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */


class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
 
class Solution3 {
	 public ListNode reverseBetween(ListNode head, int m, int n) {
	       
	        if(head == null)
	            return null;
	        ListNode dummy = new ListNode(0);
	        dummy.next = head;
	        ListNode prev = dummy;	
	        if(head.next==null)
	            return dummy.next;
	        for(int i=0;i<m-1;i++){
	        	prev = prev.next;
	        }
	        ListNode start = prev.next;
	        ListNode end = start;
	        ListNode tmp = null;
	        for(int i=0;i<(n-m);i++){
	        	
	            tmp = end.next;
	            prev.next=end.next;
	            end.next = tmp.next;
	            tmp.next = start;
	            start = tmp;
	        }
	        return m==1?prev.next:head;
	        
	    }
}

public class reverseBetween {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    
    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);
    
        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }
    
    public static String listNodeToString(ListNode node) {
        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return result.substring(0, result.length() - 2);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);
            line = in.readLine();
            int m = Integer.parseInt(line);
            line = in.readLine();
            int n = Integer.parseInt(line);
            
            ListNode ret = new Solution3().reverseBetween(head, m, n);
            
            String out = listNodeToString(ret);
            
            System.out.print(out);
        }
    }
}