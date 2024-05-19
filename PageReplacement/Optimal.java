package PageReplacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Optimal {
    private List<Integer> references;

    public Optimal(List<Integer> references) {
        this.references = references;
    }

    private void stimulation(int totalFrames) {
        List<Integer> frames = new ArrayList<>(totalFrames);
        int cur = 0;
        int fault = 0;
        int swap = 0;

        System.out.println();
        System.out.println("Page\tPF?\t\tPR");
        for (int i = 0; i < references.size(); i++) {
            int page = references.get(i);
            if (frames.contains(page)) {
                System.out.println(page + "\t\t-\t\t-");
                continue;
            } else if (cur < totalFrames) {
                frames.add(page);
                cur++;
                fault++;
                System.out.println(page + "\t\tF\t\t-");
            } else if (!frames.contains(page)) {
                fault++;
                swap++;
                int victim = -1;
                List<Integer> candidate = new ArrayList<>(frames);
                for (int j = i + 1; j < references.size(); j++) {
                    if (candidate.size() == 1) {
                        victim = candidate.get(0);
                        break;
                    }
                    int future = references.get(j);
                    if (candidate.contains(future)) {
                        candidate.remove(Integer.valueOf(future));
                    }
                }
                if (!candidate.isEmpty()) {
                    victim = candidate.get(0);
                }
                System.out.println(page + "\t\tF\t\t" + victim);
                frames.remove(Integer.valueOf(victim));
                frames.add(page);
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
        System.out.print("Reference list (empty list to exit): ");
        String input = scanner.nextLine();
        System.out.print("Number of frames (-1 to exit): ");
        int totalFrames = Integer.parseInt(scanner.nextLine());

        List<Integer> references = new ArrayList<>();
        for (String ref : input.split(" ")) {
            references.add(Integer.parseInt(ref));
        }
        Optimal optimal = new Optimal(references);

        optimal.run(totalFrames);
    }
}
