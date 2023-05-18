
//https://www.algoexpert.io/questions/stable-internships
import java.util.*;

class Program {
  public int[][] stableInternships(int[][] interns, int[][] teams) {
    HashMap<Integer, Integer> matches = new HashMap<>();
    // have interns choose their teams
    Stack<Integer> freeInterns = new Stack<>();
    for (int i = 0; i < interns.length; i++) {
      freeInterns.push(i);
    }

    int[] currentInternChoices = new int[interns.length];

    /*
     * You can also use an int[][] for this since the quantities are all finite.
     * Kind of easier than a list of HashMaps
     * See Your Solution 1 in algoexpert
     */
    List<HashMap<Integer, Integer>> teamMaps = new ArrayList<>();

    for (int[] teamPreference : teams) {
      HashMap<Integer, Integer> teamMap = new HashMap<>();
      for (int i = 0; i < teamPreference.length; i++) {
        teamMap.put(teamPreference[i], i);
      }

      teamMaps.add(teamMap);
    }

    while (!freeInterns.isEmpty()) {
      int currentIntern = freeInterns.pop();
      int[] currentInternsPreferences = interns[currentIntern];

      // i.e. Intern #1 is on their first choice (choice 0), etc
      int currentInternsChoiceNumber = currentInternChoices[currentIntern];
      currentInternChoices[currentIntern]++; // mark that we're trying choice n

      int currentTeamPreference = currentInternsPreferences[currentInternsChoiceNumber];

      // This team doesn't have an intern assigned yet
      if (!matches.containsKey(currentTeamPreference)) {
        matches.put(currentTeamPreference, currentIntern);
        continue;
      }

      /*
       * This team has an intern assigned.
       * Would they prefer currentIntern instead?
       */
      int occupyingIntern = matches.get(currentTeamPreference);
      int occupyingInternsRank = teamMaps.get(currentTeamPreference).get(occupyingIntern);
      int currentInternsRank = teamMaps.get(currentTeamPreference).get(currentIntern);

      // They would prefer currentIntern
      if (currentInternsRank < occupyingInternsRank) {
        matches.put(currentTeamPreference, currentIntern);
        // Now this intern is free and needs a team
        freeInterns.push(occupyingIntern);
      } else {
        // currentIntern needs to try again
        freeInterns.push(currentIntern);
      }
    }

    int[][] result = new int[interns.length][2];
    int resultIndex = 0;

    for (Map.Entry<Integer, Integer> match : matches.entrySet()) {
      result[resultIndex] = new int[] { match.getValue(), match.getKey() };
      resultIndex++;
    }

    return result;
  }
}
