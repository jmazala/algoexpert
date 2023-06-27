// https://www.algoexpert.io/questions/calendar-matching

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CalendarMatching {

  /*
   * TODO: Could also find the gaps in both calendars and then merge the gaps....
   */
  public static List<StringMeeting> calendarMatching(
      List<StringMeeting> calendar1,
      StringMeeting dailyBounds1,
      List<StringMeeting> calendar2,
      StringMeeting dailyBounds2,
      int meetingDuration) {

    calendar1.add(0, new StringMeeting("00:00", dailyBounds1.start));
    calendar1.add(new StringMeeting(dailyBounds1.end, "24:00"));
    calendar2.add(0, new StringMeeting("00:00", dailyBounds2.start));
    calendar2.add(new StringMeeting(dailyBounds2.end, "24:00"));

    // Merge calendar 2 into calendar1
    List<StringMeeting> mergedCalendar = mergeCalendars(calendar1, calendar2);
    List<StringMeeting> flattenedCalendar = flattenCalendar(mergedCalendar);
    return findGaps(flattenedCalendar, meetingDuration);
  }

  private static List<StringMeeting> mergeCalendars(List<StringMeeting> calendar1, List<StringMeeting> calendar2) {
    List<StringMeeting> mergedCalendar = new ArrayList<StringMeeting>();
    int p1 = 0;
    int p2 = 0;

    while (p1 < calendar1.size() || p2 < calendar2.size()) {
      if (p2 == calendar2.size()) {
        mergedCalendar.add(calendar1.get(p1));
        p1++;
        continue;
      }

      if (p1 == calendar1.size()) {
        mergedCalendar.add(calendar2.get(p2));
        p2++;
        continue;
      }

      int meeting1Start = stringToInt(calendar1.get(p1).start);
      int meeting2Start = stringToInt(calendar2.get(p2).start);

      if (meeting1Start == meeting2Start) {
        int meeting1End = stringToInt(calendar1.get(p1).end);
        int meeting2End = stringToInt(calendar2.get(p2).end);

        if (meeting1End < meeting2End) {
          mergedCalendar.add(calendar1.get(p1));
          p1++;
        } else {
          mergedCalendar.add(calendar2.get(p2));
          p2++;
        }
      } else if (meeting1Start < meeting2Start) {
        mergedCalendar.add(calendar1.get(p1));
        p1++;
      } else {
        mergedCalendar.add(calendar2.get(p2));
        p2++;
      }
    }

    return mergedCalendar;
  }

  private static List<StringMeeting> flattenCalendar(List<StringMeeting> calendar) {
    int i = 0;

    while (i < calendar.size() - 1) {
      StringMeeting meeting1 = calendar.get(i);
      StringMeeting meeting2 = calendar.get(i + 1);

      // meeting1.start will always be <= meeting2.start
      int meeting2Start = stringToInt(meeting2.start);
      int meeting1End = stringToInt(meeting1.end);
      int meeting2End = stringToInt(meeting2.end);

      if (meeting2Start > meeting1End) {
        i++;
        continue;
      }

      if (meeting2End > meeting1End) {
        meeting1.end = meeting2.end;
      }

      calendar.remove(i + 1);
    }

    return calendar;
  }

  private static List<StringMeeting> findGaps(List<StringMeeting> calendar, int meetingDuration) {
    List<StringMeeting> output = new ArrayList<>();
    for (int i = 0; i < calendar.size() - 1; i++) {
      StringMeeting meeting1 = calendar.get(i);
      StringMeeting meeting2 = calendar.get(i + 1);

      int start = stringToInt(meeting1.end);
      int end = stringToInt(meeting2.start);

      if (subtractTime(end, start) >= meetingDuration) {
        output.add(new StringMeeting(meeting1.end, meeting2.start));
      }
    }

    return output;
  }

  /*
   * Times are stored in integers where 09:00 = 900, 12:30 = 1230 etc
   * This returns the number of minutes difference.
   */
  private static int subtractTime(int time1, int time2) {
    int hours1 = time1 / 100; // Extracting hours from time1
    int minutes1 = time1 % 100; // Extracting minutes from time1

    int hours2 = time2 / 100; // Extracting hours from time2
    int minutes2 = time2 % 100; // Extracting minutes from time2

    int totalMinutes1 = hours1 * 60 + minutes1; // Converting time1 to total minutes
    int totalMinutes2 = hours2 * 60 + minutes2; // Converting time2 to total minutes

    int difference = totalMinutes1 - totalMinutes2; // Calculating the difference in minutes

    return difference;
  }

  private static int stringToInt(String time) {
    while (time.length() < 5) {
      time = "0" + time;
    }
    return Integer.parseInt(time.substring(0, 2) + time.substring(3, 5));
  }

  static class StringMeeting {
    public String start;
    public String end;

    public StringMeeting(String start, String end) {
      this.start = start;
      this.end = end;
    }

    public String toString() {
      return Arrays.toString(new String[] { this.start, this.end });
    }
  }

  public static void main(String[] args) {
    List<StringMeeting> calendar1 = new ArrayList<>();
    List<StringMeeting> calendar2 = new ArrayList<>();
    StringMeeting dailyBounds1 = new StringMeeting("9:00", "20:00");
    StringMeeting dailyBounds2 = new StringMeeting("10:00", "18:30");

    for (String[] times : new String[][] { { "9:00", "10:30" }, { "12:00", "13:00" }, { "16:00", "18:00" } }) {
      calendar1.add(new StringMeeting(times[0], times[1]));
    }

    for (String[] times : new String[][] { { "10:00", "11:30" }, { "12:30", "14:30" }, { "14:30", "15:00" },
        { "16:00", "17:00" } }) {
      calendar2.add(new StringMeeting(times[0], times[1]));
    }

    List<StringMeeting> output = calendarMatching(calendar1, dailyBounds1,
        calendar2, dailyBounds2, 30);

    for (StringMeeting meeting : output) {
      System.out.println(meeting);
    }

    List<StringMeeting> calendar3 = new ArrayList<>();
    List<StringMeeting> calendar4 = new ArrayList<>();
    StringMeeting dailyBounds3 = new StringMeeting("9:00", "20:00");
    StringMeeting dailyBounds4 = new StringMeeting("10:00", "18:30");

    for (String[] times : new String[][] { { "9:00", "10:30" }, { "12:00", "13:00" }, { "16:00", "18:00" } }) {
      calendar3.add(new StringMeeting(times[0], times[1]));
    }

    for (String[] times : new String[][] { { "10:00", "11:30" }, { "12:30", "14:30" }, { "14:30", "15:00" },
        { "16:00", "17:00" } }) {
      calendar4.add(new StringMeeting(times[0], times[1]));
    }

    System.out.println("\n");

    List<StringMeeting> output2 = calendarMatching(calendar3, dailyBounds3, calendar4, dailyBounds4, 45);

    for (StringMeeting meeting : output2) {
      System.out.println(meeting);
    }
  }
}
