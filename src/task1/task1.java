package task1;

import java.util.Arrays;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Введите количество интервалов: ");
        int n = s.nextInt();
        System.out.print("Введите длину интервалов: ");
        int m = s.nextInt();

        int[] arr = new int[n];
        Arrays.setAll(arr, i -> ++i);

        int x = 0;
        System.out.print("Path: ");
        do {
            System.out.print(arr[x]);
            x = (x + m - 1) % n;
        } while (x != 0);
    }
}