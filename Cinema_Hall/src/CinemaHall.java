import java.time.LocalDate;
import java.util.Scanner;

public class CinemaHall {
    private static String[][] seatingChart;
    private static int rows;
    private static int columns;
    private static final String[] history = new String[100];
    private static int historyCounts = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===<<Theater Seating Console Application>>===");
            System.out.println("1. Setup Theater Room");
            System.out.println("2. Book Seat");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Display History of Bookings");
            System.out.println("5. Exit");
            System.out.print(">>>>Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println(">>>>>> Setup Theater Room <<<<<<");
                    setupTheaterRoom(scanner);
                    System.out.println("===================================");
                }
                case 2 -> {
                    System.out.println(">>>>>> Book Seat <<<<<<");
                    bookSeat(scanner);
                    System.out.println("===================================");
                }
                case 3 -> {
                    System.out.println(">>>>>> Cancel Booking <<<<<<");
                    cancelBooking(scanner);
                    System.out.println("===================================");
                }
                case 4 -> {
                    System.out.println(">>>>>> Display History of Bookings <<<<<<");
                    displayHistory();
                    System.out.println("===================================");
                }
                case 5 -> {
                    System.out.println("Exiting the application...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            //allow to wait for the pressing enter in order to continue
            System.out.println("Press enter to continue...");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    // Method to set up the theater room
    private static void setupTheaterRoom(Scanner scanner) {
        // row must be greater than 0 and column must be greater than 0
        while (true) {
            System.out.print("[+] Insert rows: ");
            rows = scanner.nextInt();
            if (rows > 0) {
                break;
            } else {
                System.out.println("Number of rows must be greater than 0.");
            }
        }
        while (true) {
            System.out.print("[+] Insert columns: ");
            columns = scanner.nextInt();
            if (columns > 0) {
                break;
            } else {
                System.out.println("Number of columns must be greater than 0.");
            }
        }

        seatingChart = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seatingChart[i][j] = "AV";
            }
        }
        System.out.println("Theater room setup completed.");
    }

    private static void bookSeat(Scanner scanner) {
        if (seatingChart == null) {
            System.out.println("Please setup the theater room first.");
            return;
        }
        displaySeatingChart();
        String input;
        String[] seatToBook;
        int row, seat;

        System.out.print("Enter seat to book (e.g., A-1, B-2): ");
        input = scanner.next();
        seatToBook = input.toUpperCase().split(",");
        for (String seatInput : seatToBook) {
            seatInput = seatInput.trim();
            if (seatInput.matches("^[A-Z]-\\d+$")) {
                char rowLetter = seatInput.charAt(0); //
                int seatNumber = Integer.parseInt(seatInput.substring(2));

                row = rowLetter - 'A'; //find the row
                seat = seatNumber - 1; //find the seat column (since the seat starts from 1)

                if (isSeatValid(row, seat)) {
                    if (seatingChart[row][seat].equals("AV")) {
                        seatingChart[row][seat] = "BO"; // Mark seat as booked
                        LocalDate now = LocalDate.now();
                        System.out.println("Seat " + seatInput + " booked successfully at " + now + ".");
                        history[historyCounts++] = "Seat " + seatInput + " booked at " + now;
                    } else {
                        System.out.println("Seat " + seatInput + " is already booked.");
                    }
                } else {
                    System.out.println("Invalid seat selection " + seatInput + ".");
                }
            } else {
                System.out.println("Invalid input format " + seatInput + ". Use format like A-1.");
            }
        }
    }

    private static void cancelBooking(Scanner scanner) {
        if (seatingChart == null) {
            System.out.println("Please setup the theater room first.");
            return;
        }
        displaySeatingChart();

        String input;
        String[] seatToCancel;
        int row, seat;

        System.out.print("Enter seat to cancel booking (e.g., A-1): ");
        input = scanner.next().toUpperCase();
        seatToCancel = input.toUpperCase().split(",");
        for (String seatInput : seatToCancel) {
            seatInput = seatInput.trim();
            if (seatInput.matches("^[A-Z]-\\d+$")) {
                char rowLetter = seatInput.charAt(0); //
                int seatNumber = Integer.parseInt(seatInput.substring(2));

                row = rowLetter - 'A'; //find the row
                seat = seatNumber - 1; //find the seat column (since the seat starts from 1)

                if (isSeatValid(row, seat)) {
                    if (seatingChart[row][seat].equals("BO")) {
                        seatingChart[row][seat] = "AV"; // Mark seat as available
                        LocalDate now = LocalDate.now();
                        System.out.println("Seat " + seatInput + " booking canceled successfully at " + now + ".");
                    } else {
                        System.out.println("Seat is not booked.");
                    }
                } else {
                    System.out.println("Invalid seat selection " + seatInput + ".");
                }
            } else {
                System.out.println("Invalid input format " + seatInput + ". Use format like A-1.");
            }

        }

    }

    private static void displaySeatingChart() {
        if (seatingChart == null) {
            System.out.println("Please setup the theater room first.");
            return;
        }
        System.out.println("\n--- Seating Chart ---");
        char seatNumber = 'A';
        for (int i = 0; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j < columns; j++) {
                System.out.print(seatNumber + "-" + (j + 1) + ": " + seatingChart[i][j]);
                if (j < columns - 1) {
                    System.out.print(", ");
                }

            }
            seatNumber++;
            System.out.print("]");
            System.out.println();

        }
    }

    private static void displayHistory() {
        // Display the history of bookings
        int count = 1;
        System.out.println("\n--- Booking History ---");
        for (int i = 0; i < historyCounts; i++) {
            System.out.println(count + ". " + history[i]);
            count++;
        }
        // if there are no bookings
        if (historyCounts == 0) {
            System.out.println("No bookings found.");
        }
        System.out.println("Total bookings: " + historyCounts);

    }

    private static boolean isSeatValid(int row, int seat) {
        return row >= 0 && row < rows && seat >= 0 && seat < columns;
    }
}
