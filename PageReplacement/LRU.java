package PageReplacement;

// Java implementation of above algorithm

import java.util.*;

public class LRU {
    // Method to find page faults using indexes
    static int pageFaults(int pages[], int n, int capacity) {
        // To represent set of current pages. We use
        // an unordered_set so that we quickly check
        // if a page is present in set or not
        HashSet<Integer> s = new HashSet<>(capacity);

        // To store least recently used indexes
        // of pages.
        HashMap<Integer, Integer> indexes = new HashMap<>();

        // Start from initial page
        int page_faults = 0;
        System.out.println();
        System.out.println("Page\tPF?\t\tPR");
        int countPR = 0;
        for (int i=0; i < n; i++) {
            // Check if the set can hold more pages
            if (s.contains(pages[i])) {
                System.out.println(pages[i] + "\t\t-\t\t-");
                indexes.put(pages[i], i);
                continue;
            }
            if (s.size() < capacity) {

                // Insert it into set if not present
                // already which represents page fault
                if (!s.contains(pages[i])) {
                    System.out.println(pages[i] + "\t\tF\t\t-");
                    s.add(pages[i]);

                    // increment page fault
                    page_faults++;
                }

                // Store the recently used index of
                // each page
                indexes.put(pages[i], i);
            }

            // If the set is full then need to perform lru
            // i.e. remove the least recently used page
            // and insert the current page
            else {
                // Check if current page is not already
                // present in the set
                if (!s.contains(pages[i])) {
                    // Find the least recently used pages
                    // that is present in the set
                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;

                    Iterator<Integer> itr = s.iterator();

                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (indexes.get(temp) < lru)
                        {
                            lru = indexes.get(temp);
                            val = temp;
                        }
                    }
                    System.out.println(pages[i] + "\t\tF\t\t" + val);
                    countPR++;

                    // Remove the indexes page
                    s.remove(val);
                    //remove lru from hashmap
                    indexes.remove(val);
                    // insert the current page
                    s.add(pages[i]);

                    // Increment page faults
                    page_faults++;
                }

                // Update the current page index
                indexes.put(pages[i], i);
            }
        }
        System.out.println("Number of Page Replacement: " + countPR);

        return page_faults;
    }

    // Driver method
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter space-separated pages (empty list to exit): ");
        String input = sc.nextLine();
        List<Integer> references = new ArrayList<>();
        for (String ref : input.split(" ")) {
            references.add(Integer.parseInt(ref));
        }

        int[] pages = new int[references.size()];
        for (int i = 0; i < references.size(); i++) {
            pages[i] = references.get(i);
        }

        System.out.print("Enter number of frames: ");
        int capacity = sc.nextInt();

        System.out.println("Number of page faults: " + pageFaults(pages, pages.length, capacity));
    }
}

