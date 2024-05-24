package DiskScheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Java program for implementation of
// SSTF disk scheduling
class node {

    // represent difference between
    // head position and track number
    int distance = 0;

    // true if track has been accessed
    boolean accessed = false;
}

public class SSTF {

    // Calculates difference of each
    // track number with the head position
    public static void calculateDifference(int queue[],
                                           int head, node diff[])

    {
        for (int i = 0; i < diff.length; i++)
            diff[i].distance = Math.abs(queue[i] - head);
    }

    // find unaccessed track
    // which is at minimum distance from head
    public static int findMin(node diff[])
    {
        int index = -1, minimum = Integer.MAX_VALUE;

        for (int i = 0; i < diff.length; i++) {
            if (!diff[i].accessed && minimum > diff[i].distance) {

                minimum = diff[i].distance;
                index = i;
            }
        }
        return index;
    }

    public static void shortestSeekTimeFirst(int request[],
                                             int head)

    {
        if (request.length == 0)
            return;

        // create array of objects of class node
        node diff[] = new node[request.length];

        // initialize array
        for (int i = 0; i < diff.length; i++)

            diff[i] = new node();

        // count total number of seek operation
        int seek_count = 0;

        // stores sequence in which disk access is done
        int[] seek_sequence = new int[request.length + 1];

        for (int i = 0; i < request.length; i++) {

            seek_sequence[i] = head;
            calculateDifference(request, head, diff);

            int index = findMin(diff);

            diff[index].accessed = true;

            // increase the total count
            seek_count += diff[index].distance;

            // accessed track is now new head
            head = request[index];
        }

        // for last accessed track
        seek_sequence[seek_sequence.length - 1] = head;

        System.out.println("Total number of seek operations = "
                + seek_count);

        System.out.println("Seek Sequence is");

        // print the sequence
        for (int i = 0; i < seek_sequence.length; i++)
            System.out.print(seek_sequence[i] + " ");
    }

    public static void main(String[] args)
    {
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
        shortestSeekTimeFirst(arr, curPos);
    }
}
