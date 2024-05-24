package DiskScheduling;

import java.util.Scanner;

public class Sort {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Nhập vào các phần tử trong mảng (cách nhau bởi dấu cách):");
        String inputStr = input.nextLine();
        String[] inputArr = inputStr.split(" ");

        int num = inputArr.length;
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = Integer.parseInt(inputArr[i]);
        }

        // Sắp xếp mảng theo thứ tự tăng dần
        for (int i = 0; i < (num - 1); i++) {
            for (int j = 0; j < num - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        System.out.println("Kết quả sau khi sắp xếp theo thứ tự tăng dần là:");
        for (int i = 0; i < num; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

