package com.need.revision;

import java.util.Arrays;

//this is a min heap
public class Heap {

    private int cap = 10;
    private int size = 0;
    int[] items = new int[cap];

    private int getLeftChildIndex(int parentIndex) {
        return 2*parentIndex+1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2*parentIndex+2;
    }

    private int getParentIndex(int index) {
        return (index - 1)/2;
    }

    private int getLeftChild(int parentIndex) {
        return items[getLeftChildIndex(parentIndex)];
    }

    private int getRightChild(int parentIndex) {
        return items[getRightChildIndex(parentIndex)];
    }

    private boolean hasLeftChild(int parentIndex) {
        return getLeftChildIndex(parentIndex) < cap;
    }

    private boolean hasRightChild(int parentIndex) {
        return getRightChildIndex(parentIndex) < cap;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0 ;
    }

    private void swap(int indexA, int indexB) {
        int tmp = items[indexA];
        items[indexA] = items[indexB];
        items[indexB] = tmp;
    }

    private void ensureExtraCapacity() {
        if(size == cap) {
            items = Arrays.copyOf(items, cap*2);
            cap = cap*2;
        }
    }

    public int peek() {
        if(size == 0)
            throw new IllegalStateException();
        return items[0];
    }

    public int poll() {
        if(size == 0)
            throw new IllegalStateException();
        int item = items[0];
        items[0] = items[size - 1];
        size--;
        heapifyDown();
        return item;
    }

    public void add(int item) {
        ensureExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        // TODO Auto-generated method stub
        int index = size - 1;
        while (hasParent(index) && items[getParentIndex(index)] > items[index]) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        // TODO Auto-generated method stub
        int parentIndex = 0;
        while (hasLeftChild(parentIndex)) {
            int smallerIndex = getLeftChildIndex(parentIndex);
            if (hasRightChild(parentIndex) && getLeftChild(parentIndex) > getRightChild(parentIndex))
                smallerIndex = getRightChild(parentIndex);
            if (items[parentIndex] < items[smallerIndex])
                break;
            else
                swap(parentIndex, smallerIndex);
            parentIndex = smallerIndex;
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
