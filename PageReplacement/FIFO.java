package PageReplacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FIFO {
    private List<Integer> references;

    public FIFO(List<Integer> references) {
        this.references = references;
    }

    private void stimulation(int totalFrames) {
        List<Integer> frames = new ArrayList<>(totalFrames);
        int cur = 0;
        int fault = 0;
        int swap = 0;

        System.out.println();
        System.out.println("Page\tPF?\t\tPR");
        for (int page : references) {
            if (frames.contains(page)) {
                System.out.println(page + "\t\t-\t\t-");
                continue;
            }
            if (cur < totalFrames) {
                frames.add(page);
                cur++;
                fault++;
                System.out.println(page + "\t\tF\t\t-");
            } else if (!frames.contains(page)) {
                fault++;
                swap++;
                System.out.println(page + "\t\tF\t\t" + frames.get(0));
                for (int i = 0; i < totalFrames - 1; i++) {
                    frames.set(i, frames.get(i + 1));
                }
                frames.set(totalFrames - 1, page);
            }
        }

        System.out.println("Total page fault: " + fault);
        System.out.println("Total page swap: " + swap);
    }

    public void run(int totalFrames) {
        stimulation(totalFrames);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter space-separated pages (empty list to exit): ");
        String input = scanner.nextLine();

        System.out.print("Number of frames (-1 to exit): ");
        int totalFrames = scanner.nextInt();

        List<Integer> references = new ArrayList<>();
        for (String ref : input.split(" ")) {
            references.add(Integer.parseInt(ref));
        }
        FIFO fifo = new FIFO(references);
        fifo.run(totalFrames);
    }
}

