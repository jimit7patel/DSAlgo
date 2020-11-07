package lc.topliked.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * https://leetcode.com/problems/top-k-frequent-elements/submissions/ use heap.
 * while adding to heap use comparator that sorts based on values. Otherwise,
 * you can have a mapping from frequency to number
 */
public class TopKFrequentItems {
    public int[] topKFrequentUsingHeap(int[] nums, int k) {
        HashMap<Integer, Integer> tmp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            tmp.put(nums[i], tmp.getOrDefault(nums[i], 0) + 1);
        }
        new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(tmp.get(a), tmp.get(b));
            }
        };
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(a -> tmp.get(a)));

        // PriorityQueue<Map.Entry<Integer, Integer>> queue
        // = new PriorityQueue<>(Comparator.comparing(e -> e.getValue()));

        for (int a : tmp.keySet()) {
            pq.add(a);
            if (pq.size() > k) {
                pq.remove();
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int a : pq) {
            res.add(a);
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> tmp = new HashMap<>();
        HashMap<Integer, List<Integer>> reversemap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            tmp.put(nums[i], tmp.getOrDefault(nums[i], 0) + 1);
        }
        int[] freq = tmp.values().stream().mapToInt(i -> i).toArray();

        for (Map.Entry<Integer, Integer> en : tmp.entrySet()) {
            List<Integer> r = new ArrayList<>();
            if (reversemap.containsKey(en.getValue())) {
                r = reversemap.get(en.getValue());
            }
            r.add(en.getKey());
            reversemap.put(en.getValue(), r);
        }

        helper(freq, 0, freq.length - 1, freq.length - k);

        int[] result = new int[k];
        int j = 0;
        for (int i = freq.length - k; i < freq.length; i++) {
            List<Integer> list = reversemap.get(freq[i]);
            result[j] = list.get(0);
            list.remove(0);
            reversemap.put(freq[i], list);
            j++;
            if (j == k) {
                return result;
            }
        }
        return result;
    }

    public void helper(int[] nums, int start, int end, int index) {
        if (start == end) {
            return;
        }
        int p = lomutos(nums, start, end);
        if (p == index) {
            return;
        } else if (p > index) {
            helper(nums, start, p - 1, index);
        } else {
            helper(nums, p + 1, end, index);
        }

    }

    public int lomutos(int[] a, int start, int end) {

        int p = a[start];
        int orange = start;
        int green = start + 1;
        while (green <= end) {
            if (a[green] < p) {
                orange++;
                swap(a, orange, green);
            }
            green++;
        }
        swap(a, orange, start);
        return orange;
    }

    public void swap(int a[], int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        new TopKFrequentItems().topKFrequent(new int[] { 5, 3, 1, 1, 1, 3, 5, 73, 1 }, 3);
    }
}
