package RAID5;

import java.util.Scanner;

public class XOR {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter binary numbers separated by spaces: ");
        String inputBinaryNumbers = input.nextLine();
        String[] binaryNumbersArray = inputBinaryNumbers.split(" ");

        int xorResult = 0;
        for (String binaryNumber : binaryNumbersArray) {
            xorResult ^= Integer.parseInt(binaryNumber, 2);
        }

        // Convert the result to an 8-bit binary string
        String binaryResult = String.format("%08d", Integer.parseInt(Integer.toBinaryString(xorResult)));

        System.out.println("The 8-bit binary XOR result is: " + binaryResult);
        input.close();
    }
}

