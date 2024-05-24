package DiskScheduling;

import java.util.Arrays;
import java.util.Scanner;

public class CLOOK {

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
        System.out.print("Enter the direction (left to right(r) or right to left(l)): ");
        scanner.nextLine(); // consume newline
        String direction = scanner.nextLine().trim();

        // Sort the request sequence for easier processing
        Arrays.sort(requestSequence);

        // Apply C-LOOK Disk Scheduling algorithm
        int totalHeadMovement = 0;
        int currentPos = currentPosition;

        System.out.print("Servicing order: \n" + currentPos + " ");

        if (direction.equalsIgnoreCase("r")) {
            // Move from current position to the end of the requests
            for (int i = 0; i < requestSequence.length; i++) {
                if (requestSequence[i] >= currentPos) {
                    System.out.print(requestSequence[i] + " ");
                    totalHeadMovement += Math.abs(requestSequence[i] - currentPos);
                    currentPos = requestSequence[i];
                }
            }

            // Jump to the start of the remaining requests
            if (requestSequence[0] < currentPosition) {
                totalHeadMovement += Math.abs(currentPos - requestSequence[0]);
                currentPos = requestSequence[0];
            }

            // Continue servicing the remaining requests
            for (int i = 0; i < requestSequence.length; i++) {
                if (requestSequence[i] < currentPosition) {
                    System.out.print(requestSequence[i] + " ");
                    totalHeadMovement += Math.abs(requestSequence[i] - currentPos);
                    currentPos = requestSequence[i];
                }
            }
        } else if (direction.equalsIgnoreCase("l")) {
            // Move from current position to the beginning of the requests
            for (int i = requestSequence.length - 1; i >= 0; i--) {
                if (requestSequence[i] <= currentPos) {
                    System.out.print(requestSequence[i] + " ");
                    totalHeadMovement += Math.abs(requestSequence[i] - currentPos);
                    currentPos = requestSequence[i];
                }
            }

            // Jump to the end of the remaining requests
            if (requestSequence[requestSequence.length - 1] > currentPosition) {
                totalHeadMovement += Math.abs(currentPos - requestSequence[requestSequence.length - 1]);
                currentPos = requestSequence[requestSequence.length - 1];
            }

            // Continue servicing the remaining requests
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

