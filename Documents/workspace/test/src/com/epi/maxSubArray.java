package com.epi;

public class maxSubArray{
	
public static int maxSubArray(int[] nums) {
    int max = Integer.MIN_VALUE;
    int sum=0;
    for(int i=0;i<nums.length;i++){
        
        sum = sum + nums[i];
        max = Math.max(max, sum);
        if(sum<0)
        	sum=0;
        
    }
    return max;
    
}

public static void main (String[] args) throws java.lang.Exception
{
	System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
}
}