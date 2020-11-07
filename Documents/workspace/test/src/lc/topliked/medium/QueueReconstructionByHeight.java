package lc.topliked.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 */
public class QueueReconstructionByHeight {
    public static int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][];
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                if (a1[0] != a2[0]) {
                    return a2[0] - a1[0];
                } else {
                    return a1[1] - a2[1];
                }
            }
        });

        ArrayList<int[]> list = new ArrayList<>(people.length);

        for(int i=0; i<people.length; i++){
            int[] arr = people[i];
            list.add(arr[1], arr);
        }

        for (int i = 0; i < people.length; i++) {
            result[i] = list.get(i);
        }

        return result;


    }

    public static void main(String[] args) {
        QueueReconstructionByHeight.reconstructQueue(new int[][] { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } });
    }
}
