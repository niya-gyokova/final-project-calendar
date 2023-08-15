import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static ArrayList<String> eventNames = new ArrayList<>();
    static ArrayList<LocalDateTime> eventStartTimes = new ArrayList<>();
    static ArrayList<LocalDateTime> eventEndTimes = new ArrayList<>();
    static ArrayList<String> eventComments = new ArrayList<>();

    public static int getTask() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Calendar Menu:");
        System.out.println("1. Schedule an event");
        System.out.println("2. Cancel an event");
        System.out.println("3. Print daily schedule");
        System.out.println("4. Change event date/time");
        System.out.println("5. Search for an event");
        System.out.println("6. Find available spot");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        int task = sc.nextInt();
        return task;
    }


    public static void createEvent(int task) { //не мога да измисля как да приеме 1 след като примерно е правило 3
        Scanner sc = new Scanner(System.in);
        while (task == 1) {
            System.out.print("Enter start date and time (yyyy-MM-dd HH:mm): ");
            String startDateTimeString = sc.nextLine();
            LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("Enter end date and time (yyyy-MM-dd HH:mm): ");
            String endDateTimeString = sc.nextLine();
            LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("Enter event name: ");
            String eventName = sc.nextLine();  //обърква се при cancel event ако е повече от 1 дума, дори да сменя на sc.next()

            System.out.print("Enter comment: ");
            String eventComment = sc.nextLine();

            eventStartTimes.add(startDateTime);
            eventEndTimes.add(endDateTime);
            eventNames.add(eventName);
            eventComments.add(eventComment);

            System.out.println("Calendar Menu:");
            System.out.println("1. Schedule an event");
            System.out.println("2. Cancel an event");
            System.out.println("3. Print daily schedule");
            System.out.println("4. Change event date/time");
            System.out.println("5. Search for an event");
            System.out.println("6. Find available spot");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            task = Integer.parseInt(sc.nextLine());
        }
    }

    public static void cancelEvent(int task) { //repeats two times
        Scanner sc = new Scanner(System.in);
        while (task == 2) {
            System.out.println("Enter event's name you want to cancel");
            String canceledEvent = sc.next();

            int index = eventNames.indexOf(canceledEvent);

            eventNames.remove(index);
            eventStartTimes.remove(index);
            eventEndTimes.remove(index);
            eventComments.remove(index);

            System.out.println("The event has been canceled successfully!");

            System.out.println("Calendar Menu:");
            System.out.println("1. Schedule an event");
            System.out.println("2. Cancel an event");
            System.out.println("3. Print daily schedule");
            System.out.println("4. Change event date/time");
            System.out.println("5. Search for an event");
            System.out.println("6. Find available spot");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            task = sc.nextInt();
        }
    }

    private static void printEvents(int task) { //repeats 3 times
        Scanner sc = new Scanner(System.in);

        while (task == 3) {
            System.out.println("Enter the date for the schedule (yyyy-MM-dd)");
            String wantedDateString = sc.nextLine();
            LocalDate wantedDate = LocalDate.parse(wantedDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            System.out.println("Events on " + wantedDateString + ":");
            boolean foundEvents = false;
            int number = 1;

            for (int i = 0; i < eventStartTimes.size(); i++) {
                if (eventStartTimes.get(i).toLocalDate().isEqual(wantedDate)) {
                    System.out.println("Event " + number + ":");
                    System.out.println("Name: " + eventNames.get(i));
                    System.out.println("Start time: " + eventStartTimes.get(i));
                    System.out.println("End time: " + eventEndTimes.get(i));
                    System.out.println("Comment: " + eventComments.get(i));
                    System.out.println();
                    foundEvents = true;
                    number++;
                }
            }

            if (!foundEvents) {
                System.out.println("No events found on " + wantedDateString + ".");
            }

            System.out.println("Calendar Menu:");
            System.out.println("1. Schedule an event");
            System.out.println("2. Cancel an event");
            System.out.println("3. Print daily schedule");
            System.out.println("4. Change event date/time");
            System.out.println("5. Search for an event");
            System.out.println("6. Find available spot");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            task = Integer.parseInt(sc.nextLine());
        }
    }

    public static void changeEventInfo(int task) {
        Scanner sc = new Scanner(System.in);

        while (task == 4) {
            System.out.println("Enter event name:");
            String name = sc.next();
            boolean foundEvent = false;

            for (int i = 0; i < eventNames.size(); i++) {
                if (eventNames.get(i).equals(name)) {
                    foundEvent = true;
                    System.out.println("Will you change the time or the comment?");
                    String answer = sc.next();
                    int index = i;

                    if (answer.equals("time")) {
                        sc.nextLine();
                        System.out.print("Enter start date and time (yyyy-MM-dd HH:mm): ");
                        String startDateTimeString = sc.nextLine();
                        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        eventStartTimes.set(index, startDateTime);

                        System.out.print("Enter end date and time (yyyy-MM-dd HH:mm): ");
                        String endDateTimeString = sc.nextLine();
                        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        eventEndTimes.set(index, endDateTime);

                        System.out.println("Time successfully changed!");
                        System.out.println("Event " + eventNames.get(index) + " is now with time to start " + eventStartTimes.get(index) + " and to end " + eventEndTimes.get(index));
                    } else if (answer.equals("comment")) {
                        sc.nextLine();
                        System.out.print("Enter new comment: ");
                        String eventComment = sc.nextLine();
                        eventComments.set(index, eventComment);

                        System.out.println("Comment successfully changed!");
                        System.out.println("Event " + eventNames.get(index) + " is now with comment " + eventComments.get(index));
                    } else {
                        System.out.println("You can change only the time or the comment.");
                    }
                }
            }

            if (!foundEvent) {
                System.out.println("Event not found.");
            }

            System.out.println("Calendar Menu:");
            System.out.println("1. Schedule an event");
            System.out.println("2. Cancel an event");
            System.out.println("3. Print daily schedule");
            System.out.println("4. Change event date/time");
            System.out.println("5. Search for an event");
            System.out.println("6. Find available spot");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            task = Integer.parseInt(sc.nextLine());
        }
    }

    public static void searchEvent(int task) {
        Scanner sc = new Scanner(System.in);

        while (task == 5) {
            System.out.println("Enter event name:");
            String name = sc.next();
            boolean foundEvent = false;

            for (int i = 0; i < eventNames.size(); i++) {
                if (eventNames.get(i).equals(name)) {
                    foundEvent = true;
                    int index = i;
                    System.out.println("Name: " + eventNames.get(index));
                    System.out.println("Start time: " + eventStartTimes.get(index));
                    System.out.println("End time: " + eventEndTimes.get(index));
                    System.out.println("Comment: " + eventComments.get(index));
                }
            }
            if (!foundEvent) {
                System.out.println("Event not found.");
            }

            System.out.println("Calendar Menu:");
            System.out.println("1. Schedule an event");
            System.out.println("2. Cancel an event");
            System.out.println("3. Print daily schedule");
            System.out.println("4. Change event date/time");
            System.out.println("5. Search for an event");
            System.out.println("6. Find available spot");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            task = sc.nextInt();

        }
    }

    public static void findAvailability(int task) { //не повтаря метода
        Scanner sc = new Scanner(System.in);
        while (task == 6) {
            System.out.println("Enter the start time (yyyy-MM-dd HH:mm): ");
            String DateTimeString = sc.nextLine();
            LocalDateTime DateTime = LocalDateTime.parse(DateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.println("Enter the duration (HH:mm): ");
            String duration = sc.nextLine();
            LocalTime durationTime = LocalTime.parse(duration, DateTimeFormatter.ofPattern("HH:mm"));

            LocalTime workStart = LocalTime.of(8, 0);
            LocalTime workEnd = LocalTime.of(17, 0);

            LocalDateTime currentDateTime = DateTime;

            while (true) {
                if (currentDateTime.toLocalTime().isAfter(workStart) &&
                        currentDateTime.toLocalTime().plusMinutes(durationTime.toNanoOfDay()).isBefore(workEnd)) {
                    System.out.println("Available meeting time: " + currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                } else {
                    System.out.println("No availability!");
                }
                break;

            }
            System.out.println("Calendar Menu:");
            System.out.println("1. Schedule an event");
            System.out.println("2. Cancel an event");
            System.out.println("3. Print daily schedule");
            System.out.println("4. Change event date/time");
            System.out.println("5. Search for an event");
            System.out.println("6. Find available spot");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            task = sc.nextInt();
        }

    }


    public static void main(String[] args) {
        createEvent(getTask());
        cancelEvent(getTask());
        printEvents(getTask());
        changeEventInfo(getTask());
        searchEvent(getTask());
        findAvailability(getTask());
        System.exit(0);

    }
}