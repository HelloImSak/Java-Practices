import java.util.Scanner;

public class Application {
    private static String[][] seatingChart;
    private static int rows;
    private static int seatsPerRow;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===<<Theater Seating Console Application>>===");
            System.out.println("1. Setup Theater Room");
            System.out.println("2. Book Seat");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Display Seating Chart");
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
                    System.out.println(">>>>>> Display Seating Chart <<<<<<");
                    displaySeatingChart();
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
        System.out.print("Enter number of rows: ");
        rows = scanner.nextInt();
        System.out.print("Enter number of seats per row: ");
        seatsPerRow = scanner.nextInt();

        seatingChart = new String[rows][seatsPerRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seatingChart[i][j] = "Non-Booked"; //
            }
        }
        System.out.println("Theater room setup completed.");
    }

    private static void bookSeat(Scanner scanner) {
        if (seatingChart == null) {
            System.out.println("Please setup the theater room first.");
            return;
        }
        // validating the row number
        // if not true demand user input again
        int row;
        do {
            System.out.print("Enter row number to book (1-" + rows + "): ");
            // decrementing the row number by 1 to match the array index
            row = scanner.nextInt() - 1;
            if (row < 0 || row >= rows) {
                System.out.println("Invalid row number. Please try again.");
            }
        } while (row < 0 || row >= rows);

        // validating the seat number
        // if not true demand user input again
        int seat;
        do {
            System.out.print("Enter seat number to book (1-" + seatsPerRow + "): ");
            // decrementing the seat number by 1 to match the array index
            seat = scanner.nextInt() - 1;
            if (seat < 0 || seat >= seatsPerRow) {
                System.out.println("Invalid seat number. Please try again.");
            }
        } while (seat < 0 || seat >= seatsPerRow);

        if (isSeatValid(row, seat)) {
            if (seatingChart[row][seat].equals("Non-Booked")) {
                System.out.print("Enter the name of the person booking the seat: ");
                String name = scanner.next();
                seatingChart[row][seat] = name; // Mark seat as booked
                System.out.println("Seat booked successfully for <" + name + "> .");
            } else {
                System.out.println("Seat is already booked by " + seatingChart[row][seat] + ".");
            }
        } else {
            System.out.println("Invalid seat selection.");
        }

    }

    private static void cancelBooking(Scanner scanner) {
        if (seatingChart == null) {
            System.out.println("Please setup the theater room first.");
            return;
        }
        displaySeatingChart();

        System.out.print("Enter name of the person who booked the seat: ");
        String name = scanner.next();

        boolean isBookingFound = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                if (seatingChart[i][j].equals(name)) {
                    seatingChart[i][j] = "Non-Booked";
                    System.out.println("Booking for <" + name + "> has been cancelled.");
                    isBookingFound = true;
                    break;
                }
            }
            if (!isBookingFound) {
                System.out.println("Booking for " + name + " not found.");
                break;
            }
        }
    }

    private static void displaySeatingChart() {
        if (seatingChart == null) {
            System.out.println("Please setup the theater room first.");
            return;
        }

        System.out.println("\n--- Seating Chart ---");
        for (int i = 0; i < rows; i++) {
            System.out.print("Row " + (i + 1) + ": | ");
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print("<" + seatingChart[i][j] + "> ");
            }
            System.out.print("|");
            System.out.println();

        }
    }

    private static boolean isSeatValid(int row, int seat) {
        return row >= 0 && row < rows && seat >= 0 && seat < seatsPerRow;
    }
}
