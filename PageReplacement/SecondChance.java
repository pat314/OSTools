package PageReplacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SecondChance {
    private List<Integer> references;

    public SecondChance(List<Integer> references) {
        this.references = references;
    }

    private void stimulation(int totalFrames, int initPointer) {
        List<Integer> frames = new ArrayList<>(totalFrames);
        int cur = 0;
        int fault = 0;
        int swap = 0;
        int pointer = initPointer;
        int[] cnt = new int[totalFrames];

        System.out.println();
        System.out.println("Page\tPF?\t\tPR");
        for (int i = 0; i < references.size(); i++) {
            int page = references.get(i);
            if (frames.contains(page)) {
                for (int j = 0; j < frames.size(); j++) {
                    if (page == frames.get(j)) {
                        cnt[j] = 1;
                        System.out.println(page + "\t\t-\t\t-");
                    }
                }
            } else if (cur < totalFrames) {
                frames.add(page);
                cur++;
                fault++;
                System.out.println(page + "\t\tF\t\t-");
            } else if (!frames.contains(page)) {
                fault++;
                swap++;
                while (cnt[pointer] != 0) {
                    cnt[pointer] = 0;
                    pointer = (pointer + 1) % totalFrames;
                }
                int victim = frames.get(pointer);
                frames.set(pointer, page);
                System.out.println(page + "\t\tF\t\t" + victim);
            }
        }

        System.out.println("Total page fault: " + fault);
        System.out.println("Total page swap: " + swap);
    }

    public void run(int totalFrames, int initPointer) {
        stimulation(totalFrames, initPointer);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter space-separated pages (empty list to exit): ");
        String input = scanner.nextLine();
        System.out.print("Number of frames: ");
        int totalFrames = Integer.parseInt(scanner.nextLine());
        List<Integer> references = new ArrayList<>();
        for (String ref : input.split(" ")) {
            references.add(Integer.parseInt(ref));
        }
        SecondChance sc = new SecondChance(references);
        sc.run(totalFrames, 0);
    }
}
