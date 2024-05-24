package DiskScheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Java program to demonstrate
// FCFS Disk Scheduling algorithm
public class FCFS {

    static void FCFS(int arr[], int head) {
        int seek_count = 0;
        int distance, cur_track;

        for (int i = 0; i < arr.length; i++) {
            cur_track = arr[i];

            // calculate absolute distance
            distance = Math.abs(cur_track - head);

            // increase the total count
            seek_count += distance;

            // accessed track is now new head
            head = cur_track;
        }

        System.out.println("Total number of " +
                "seek operations = " +
                seek_count);

        // Seek sequence would be the same
        // as request array sequence
        System.out.println("Seek Sequence is");

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    // Driver code
    public static void main(String[] args) {
        // request array
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter space-separated request sequences (empty list to exit): ");
        String input = scanner.nextLine();

        System.out.print("Current position: ");
        int curPos = scanner.nextInt();

        List<Integer> requests = new ArrayList<>();
        for (String ref : input.split(" ")) {
            requests.add(Integer.parseInt(ref));
        }
        // request array
        int arr[] = new int[requests.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = requests.get(i);
        }
        FCFS(arr, curPos);
    }
}

