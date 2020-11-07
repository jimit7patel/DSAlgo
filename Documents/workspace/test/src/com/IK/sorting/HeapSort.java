package com.IK.sorting;

import java.util.PriorityQueue;

public class HeapSort {

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public int[] heapSort(int[] a) {

        for (int i : a) {
            pq.add(i);
        }
        int i = 0;
        while (pq.isEmpty()) {

            a[i] = pq.remove();
            i++;
        }
        return a;

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }


}
