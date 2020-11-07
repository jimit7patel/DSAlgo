package lc.topliked.medium;

import java.util.Stack;

/*
 *
 * 9:10
 *
 * brute force, O(n^2). start iterating in reverse order and maintain stack that
 * has values in increasing order. So for i, keep peeking value for greater
 * value than T[i]. Also, keep popping values less than T[i].
 *
 * You do not need stack to maintain index of increasing value. you have result
 * array that also gives the same information. for example, if res[5] = 2, means
 * if you go to 5+2 = 7 index, you will find greater value. if res[j] = 0 means,
 * there is no more warmer
 *
 */
class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {

        //return optimizedTemperatures(T);
        return moreOptimized(T);
        /*brute force
        int[] result = new int[T.length];

        for(int i=0; i< T.length; i++){
            for(int j=i+1; j< T.length; j++){
                if(T[j]> T[i]){
                    result[i] = j-i;
                    break;
                }
            }
        }

        return result;
         */
    }

    public int[] optimizedTemperatures(int[] T){

        Stack<Integer> stack = new Stack<>();
        int[] res = new int[T.length];

        for(int i=T.length-1; i>=0; i--){
            while(!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            res[i] = stack.isEmpty()?0:stack.peek()- i;
            stack.push(i);
        }
        return res;
    }

    public int[] moreOptimized(int[] T){
        int[] res = new int[T.length];
        res[T.length-1] = 0;


        for(int i=T.length-2; i>=0; i--){
            if(T[i]<T[i+1]){
                res[i]=1;
            }else{
                int j = i+1;

                while(res[j]!=0){
                    j+=res[j];
                    if(T[i]<T[j]){
                        res[i] = j-i;
                        break;
                    }

                }

            }


        }
        return res;

    }
}
