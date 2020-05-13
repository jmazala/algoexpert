import java.util.*;

class Program {
  public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
    int iSequence = 0;
    int iArray = 0;

    while (iSequence < sequence.size() && iArray < array.size()) {
      if (sequence.get(iSequence) == array.get(iArray)) {
        iSequence++;
      }

      iArray++;
    }

    return iSequence == sequence.size();
  }

  public static void main(String[] args) {
    List<Integer> array = new ArrayList<>(8);
    array.add(5);
    array.add(1);
    array.add(22);
    array.add(25);
    array.add(6);
    array.add(-1);
    array.add(8);
    array.add(10);

    List<Integer> sequence = new ArrayList<>(4);
    sequence.add(1);
    sequence.add(6);
    sequence.add(-1);
    sequence.add(10);

    System.out.println(Program.isValidSubsequence(array, sequence));

    List<Integer> sequence2 = new ArrayList<>(3);
    sequence2.add(22);
    sequence2.add(25);
    sequence2.add(6);
    System.out.println(Program.isValidSubsequence(array, sequence2));
  }
}
