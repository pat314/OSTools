package DiskScheduling;

import java.util.Arrays;
import java.util.Scanner;

public class CSCAN {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the request sequence
        System.out.print("Enter the request sequence (space-separated): ");
        String[] requestString = scanner.nextLine().split(" ");
        int[] requestSequence = Arrays.stream(requestString).mapToInt(Integer::parseInt).toArray();

        // Input the current head position
        System.out.print("Enter the current head position: ");
        int currentPosition = scanner.nextInt();

        // Input the range (min, max)
        System.out.print("Enter the minimum cylinder: ");
        int min = scanner.nextInt();

        System.out.print("Enter the maximum cylinder: ");
        int max = scanner.nextInt();

        // Input the direction
        System.out.print("Enter the direction (left to right (r) or right to left(l)): ");
        scanner.nextLine(); // consume newline
        String direction = scanner.nextLine().trim();

        // Sort the request sequence for easier processing
        Arrays.sort(requestSequence);

        // Apply C-SCAN Disk Scheduling algorithm
        int totalHeadMovement = 0;
        int currentPos = currentPosition;

        System.out.print("Servicing order: \n" + currentPos + " ");

        if (direction.equalsIgnoreCase("r")) {
            // Move from current position to the end
            for (int i = 0; i < requestSequence.length; i++) {
                if (requestSequence[i] >= currentPos) {
                    System.out.print(requestSequence[i] + " ");
                    totalHeadMovement += Math.abs(requestSequence[i] - currentPos);
                    currentPos = requestSequence[i];
                }
            }

            // Jump to the beginning of the disk (from max to min)
            if (currentPos != max) {
                totalHeadMovement += Math.abs(max - currentPos);
                currentPos = max;
                System.out.print(currentPos + " ");
            }
            totalHeadMovement += Math.abs(max - min);
            currentPos = min;
            System.out.print(currentPos + " ");

            // Continue servicing the requests from the start to the current position
            for (int i = 0; i < requestSequence.length; i++) {
                if (requestSequence[i] < currentPosition) {
                    System.out.print(requestSequence[i] + " ");
                    totalHeadMovement += Math.abs(requestSequence[i] - currentPos);
                    currentPos = requestSequence[i];
                }
            }
        } else if (direction.equalsIgnoreCase("l")) {
            // Move from current position to the beginning
            for (int i = requestSequence.length - 1; i >= 0; i--) {
                if (requestSequence[i] <= currentPos) {
                    System.out.print(requestSequence[i] + " ");
                    totalHeadMovement += Math.abs(requestSequence[i] - currentPos);
                    currentPos = requestSequence[i];
                }
            }

            // Jump to the end of the disk (from min to max)
            if (currentPos != min) {
                totalHeadMovement += Math.abs(currentPos - min);
                currentPos = min;
                System.out.print(currentPos + " ");
            }
            totalHeadMovement += Math.abs(max - min);
            currentPos = max;
            System.out.print(currentPos + " ");

            // Continue servicing the requests from the end to the current position
            for (int i = requestSequence.length - 1; i >= 0; i--) {
                if (requestSequence[i] > currentPosition) {
                    System.out.print(requestSequence[i] + " ");
                    totalHeadMovement += Math.abs(requestSequence[i] - currentPos);
                    currentPos = requestSequence[i];
                }
            }
        } else {
            System.out.println("Invalid direction input. Please enter either 'left to right' or 'right to left'.");
            return;
        }

        System.out.println();
        System.out.println("Total head movement: " + totalHeadMovement);
    }
}

