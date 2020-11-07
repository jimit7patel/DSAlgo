package test;

//Hit compile and run to see a sample output.
//Read values from stdin, do NOT hard code input.
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {

    public static void PrintPrimeNumber(int s1, int s2) {
        int count = 0;
        int totalCount = 0;
        for (int i = s1; i <= s2; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    count = 0;
                    break;
                } else {
                    count = 1;
                }
            }
            if (count == 1) {
                totalCount++;
                System.out.println(i);
            }
        }
        if (totalCount == 0) {
            System.out.println("none");
        }
    }
    public static void main(String args[] ) throws Exception {

        String thisLine = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n_of_tests = Integer.parseInt(br.readLine());
        int counter = 0;
        System.out.println(n_of_tests);
        while ((thisLine = br.readLine()) != null) {
            int m = Integer.parseInt(thisLine.split(" ")[0]);
            int n = Integer.parseInt(thisLine.split(" ")[1]);
            PrintPrimeNumber(m,n);
            if (counter != n_of_tests) {
                System.out.println();
            }
            counter++;
        }
    }
}
