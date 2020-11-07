package test;

public class rotationBinarySearch {

    public static void main(String[] args) {

        Integer input[] = { 4, 5, 6, 7, 8, 9, 1, 2, 3 };

        System.out.println(findElement(input, 3));
        System.out.println(findElement(input, 8));

    }

    public static int findElement(Integer[] input, int n) {

        int m = findRotatingIndex(input);
        int start = 0;
        int end = input.length - 1;

        if (m != -1) {
            if (input[m] == n)
                return m;
            if (input[0] > n)
                start = m + 1;
            else
                end = m - 1;
        }
        m = start + (end - start) / 2;

        while (end >= start) {

            if (input[m] == n)
                return m;
            if (input[m] < n)
                start = m + 1;
            else
                end = m - 1;

            m = start + (end - start) / 2;
        }
        return -1;

    }

    public static int findRotatingIndex(Integer[] input) {

        int s = 0;
        int end = input.length;
        int m = s + (end - s) / 2;

        while (s < end) {
            if (input[m] < input[m - 1])
                return m - 1;
            if (input[m] < input[m + 1])
                return m;
            if (input[m] > input[0])
                s = m + 1;
            if (input[m] <= input[0])
                end = m - 1;
            m = s + (end - s) / 2;

        }
        return -1;
    }


}
