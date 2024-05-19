package WorkingSet;

import java.util.*;

public class WorkingSet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter reference list: ");
        String input = scanner.nextLine();
        System.out.print("Enter working-window size: ");
        int size = scanner.nextInt();
        List<Integer> references = new ArrayList<>();
        references.add(-1);
        for (String ref : input.split(" ")) {
            references.add(Integer.parseInt(ref));
        }
        System.out.print("Enter position(e.g. 5th, 6th, etc.): ");
        int position = scanner.nextInt();

        int startPos = (position - size >= 0)? position - size + 1 : 1;
        List<Integer> workingSet = new ArrayList<>();

        for (int i = startPos; i <= position; i++) {
            if (!workingSet.contains(references.get(i))) {
                workingSet.add(references.get(i));
            }
            System.out.print(references.get(i) + " ");
            System.out.println();
        }
        System.out.print("Working set: ");

        for (int i = 0; i < workingSet.size(); i++) {
            System.out.print(workingSet.get(i) + " ");
        }
    }
}
