package com.epi;

public class mergesort{
    public static int[] mergeSort(int[] input,int l,int r) {

        // if (r ==-1){
        // return null;
        // }
        if(l==r){
            return new int[]{input[l]};
        }
        int m = l+(r-l)/2;
        return merge(mergeSort(input,l,m),mergeSort(input,m+1,r));


    }
    public static int[] merge(int[] left, int[] right){
        int i=0;
        int j=0;
        int k=0;
        int[] mergedOutput=new int[left.length+right.length];
        while(i<left.length && j<right.length){

            while(i<left.length && left[i]<right[j]){
                mergedOutput[k++]=left[i++];
            }

            while(i<left.length && j<right.length && left[i]==right[j]){
                mergedOutput[k++]=left[i++];
                mergedOutput[k++]=right[j++];
            }
            while(i<left.length && j<right.length && left[i]>right[j]){
                mergedOutput[k++]=right[j++];
            }
        }
        while(i==left.length && j<right.length){
            mergedOutput[k++]=right[j++];
        }
        while(j==right.length && i<left.length){
            mergedOutput[k++]=left[i++];
        }

        return mergedOutput;
    }


    public static void main(String[] args){
        int []input={100,1203,0,1,3,56,2,3,2};

        if(input.length!=0){

            for (int t:mergeSort(input,0,input.length-1)){
                System.out.println(t);
            }
        }

    }
}