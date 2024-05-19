package PageReplacement;

import java.util.*;
import java.util.HashSet;

public class MFU {

    public static void main(String[] args) {
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

        process(pages, capacity);
    }

    private static void process(int[] pages, int capacity) {
        int [] frequency = new int[capacity];
        int [] cache = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            cache[i] = -1;
        }
        int len = pages.length;

        System.out.println();
        String s = "";
        for (int i = 0; i < capacity; i++) {
            s += "F#" + i + "\t\t";
        }
        int countPF = 0;
        int countPR = 0;
        System.out.println("Page\t"+ s +"PF?\t\tPR");
        for(int i=0; i< len; i++){
            boolean PF = false;

            System.out.print(pages[i] + "\t\t");
            int flagpos = 1;
            int done = 0;
            int maxF  = -100;
            int maxFPos = -1;
            for(int j=0; j<capacity; j++){
                //page found in cache
                if (cache[j] == pages[i]){
                    flagpos = 0;
                    frequency[j]++;
                    done = 1;
                    break;
                }
            }
            //if page not found in cache and cache is not full
            if(done == 0){
                for(int j=0; j<capacity; j++){
                    if(cache[j] == -1){
                        cache[j] = pages[i];
                        PF = true;
                        countPF++;
                        frequency[j]++;
                        done = 1;
                        break;
                    }
                }
            }
            //if page not found in cache and cache is full
            int pageToBeReplaced = -1;
            if(done == 0){
                for(int k = 0; k< capacity; k++){
                    if(frequency[k] > maxF){
                        maxF = frequency[k];
                        maxFPos = k;
                    }
                }
                pageToBeReplaced = cache[maxFPos];
                cache[maxFPos] = pages[i];
                frequency[maxFPos] = 1;
                PF = true;
                countPF++;
                countPR++;
            }
            for(int m = 0; m< capacity; m++){
                if(cache[m] != -1)
                    System.out.print(cache[m] + "(" + frequency[m] + ")" + "\t");
                else {
                    System.out.print("-\t\t");
                }
            }
            System.out.print(PF + "\t");
            if (pageToBeReplaced != -1) {
                System.out.println(pageToBeReplaced);
            } else {
                System.out.println("-");
            }
        }
        System.out.println("Total Page Fault: " + countPF);
        System.out.println("Total Page Replacement: " + countPR);
    }

}

