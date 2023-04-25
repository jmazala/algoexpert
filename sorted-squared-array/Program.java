import java.util.*;

class Program {

    /*
     * O(n) for array iterate
     * O(n log n) for sort
     * O(n) for iterate
     * = O(n log n) solution
     * O(1) for space (unless Arrays.sort isn't)
     */
    public static int[] sortedSquaredArray(int[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] < 0) {
                input[i] *= -1;
            }
        }

        Arrays.sort(input);

        for (int i = 0; i < input.length; i++) {
            input[i] *= input[i];
        }

        return input;
    }

    public static void main(String[] args) {
        System.out.println(Program.sortedSquaredArray(new int[] { 1, 2, 3, 5, 6, 8, 9 }));
    }
}