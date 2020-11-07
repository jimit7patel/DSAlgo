package com.epi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int i=0,j=0,k=0;
        int result[] = new int[n+m];
        while(k<n+m){
            
            while(j<m && nums1[i]>nums2[j]){
                result[k]=nums2[j];
                System.out.println(result[k]);
                j++;
                k++;
                
            }
            while(j==m && i<n){
                result[k]=nums1[i];
                System.out.println(result[k]);
                i++;
                k++;
                
            }
            while(i<n && nums1[i]<=nums2[j]){
                result[k]=nums1[i];
                System.out.println(result[k]);
                i++;
                k++;  
            }
            while(i==n && j<m){
                result[k]=nums2[j];
                System.out.println(result[k]);
                j++;
                k++;
            }
            
        }
        return (n+m)%2==0?(result[(n+m)/2]+result[(n+m)/2-1])/2:result[(n+m)/2];
    }
}

public class Median {
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
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums1 = stringToIntegerArray(line);
            line = in.readLine();
            int[] nums2 = stringToIntegerArray(line);
            
            double ret = new Solution1().findMedianSortedArrays(nums1, nums2);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}