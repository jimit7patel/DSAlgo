package com.epi;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

    public static class HeapEntry implements Comparable<HeapEntry> {
        int array;
        int index;
        int data;

        public HeapEntry(int array, int index, int data) {
            this.array = array;
            this.index = index;
            this.data = data;
        }

        @Override
        public int compareTo(HeapEntry o) {
            // TODO Auto-generated method stub
            return Integer.compare(data, o.data);
        }

    }

    public static int[] merge(int array[][]) {

        PriorityQueue<HeapEntry> pq = new PriorityQueue<>();
        int size = 0;
        for (int i = 0; i < array.length; i++) {
            size = size + array[i].length;
            pq.add(new HeapEntry(i, 0, array[i][0]));
        }
        int result[] = new int[size];
        int n = 0;
        while (!pq.isEmpty()) {
            HeapEntry node = pq.poll();
            result[n++] = node.data;
            int newIndex = node.index + 1;
            if (newIndex < array[node.array].length) {
                pq.add(new HeapEntry(node.array, newIndex, array[node.array][newIndex]));
            }
        }
        return result;

    }

    public static void main(String[] args) {
        int input[][] = { { 1, 3, 5, 7 },
                { 2, 4, 6, 8 },
                { 0, 9, 10, 11 } };

        int output[] = merge(input);
        Arrays.stream(output)
        .forEach(a -> System.out.println(a));
    }

}
