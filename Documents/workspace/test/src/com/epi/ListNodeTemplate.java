package com.epi;

public class ListNodeTemplate{
public static class ListNode<T> {
     public T val;
     public ListNode<T> next;
      public ListNode(T x) { val = x; }
      public ListNode(T x,ListNode<T> y) { val = x;next=y;}
  }
}