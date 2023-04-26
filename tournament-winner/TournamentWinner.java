import java.util.*;

class TournamentWinner {
  static int HOME_WINS = 1;
  static int HOME_INDEX = 0;
  static int AWAY_INDEX = 1;

  /*
   * competitions is ArrayList of size 2 {$home, $away}
   * results[i] is the winner of competitions[i].
   * 1 means home team, 0 means away team
   * 
   * OPTION 1 - keep track of them all in a hash, keep track of max
   * OPTION 2 - use a map thats sorted by values?
   */

  /*
   * OPTION 1
   * time is O(n) where r is number of results / competitions
   * space is O(k) where k is number of teams
   */
  public static String tournamentWinner(
      ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
    Map<String, Integer> namesToScores = new HashMap<String, Integer>();
    int maxScore = Integer.MIN_VALUE;
    String champion = "";

    for (int i = 0; i < results.size(); i++) {
      int result = results.get(i);
      String winner = (result == HOME_WINS) ? competitions.get(i).get(HOME_INDEX) : competitions.get(i).get(AWAY_INDEX);
      if (!namesToScores.containsKey(winner)) {
        namesToScores.put(winner, 0);
      }

      int teamScore = namesToScores.get(winner) + 3;
      namesToScores.put(winner, teamScore);
      if (teamScore > maxScore) {
        maxScore = teamScore;
        champion = winner;
      }
    }

    return champion;
  }

  public static void main(String[] args) {
    ArrayList<ArrayList<String>> competitions = new ArrayList<>();
    competitions.add(new ArrayList<String>(Arrays.asList(new String[] { "HTML", "C#" })));
    competitions.add(new ArrayList<String>(Arrays.asList(new String[] { "C#", "Python" })));
    competitions.add(new ArrayList<String>(Arrays.asList(new String[] { "Python", "HTML" })));

    ArrayList<Integer> results = new ArrayList<>();
    results.addAll(Arrays.asList(new Integer[] { 0, 0, 1 }));

    System.out.println(TournamentWinner.tournamentWinner(competitions, results)); // Python
  }
}
