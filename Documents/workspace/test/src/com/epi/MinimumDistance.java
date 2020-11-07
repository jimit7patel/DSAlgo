package com.epi;

import java.util.HashMap;

class MinimumDistance 
{
    int minDistAlternate(int arr[], int n, int x, int y) 
    {
        int i, j;
        int min_dist = Integer.MAX_VALUE;
        for (i = 0; i < n; i++) 
        {
            for (j = i + 1; j < n; j++) 
            {
                if ((x == arr[i] && y == arr[j]
                    || y == arr[i] && x == arr[j])
                    && min_dist > Math.abs(i - j)) 
                    min_dist = Math.abs(i - j);
            }
        }
        return min_dist;
    }
	
	public int minDist(int[] arr, int n, int x, int y) {
		
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		hm.put(x,0);
		hm.put(y,0);
		int d = Integer.MAX_VALUE;
		
		for(int i=0; i<n; i++) {
			
			if(arr[i]==x || arr[i]==y) {
				hm.put(arr[i], i+1);
				if(hm.get(x)!=0 && hm.get(y)!= 0) {
					d = Math.min(d, Math.abs(hm.get(x)-hm.get(y)));
				}
			}
		}
		
		
		return d;
		
	}
 
    public static void main(String[] args) 
    {
        MinimumDistance min = new MinimumDistance();
        //int arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
        int arr[] =  {2, 5, 3, 5, 4, 4, 2, 3};
        int n = arr.length;
        int x = 3;
        int y = 2;
 
        System.out.println("Minimum distance between " + x + " and " + y 
                + " is " + min.minDist(arr, n, x, y));
    }
}