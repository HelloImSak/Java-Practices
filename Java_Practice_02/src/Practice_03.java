import java.util.Scanner;

public class Practice_03 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int row, col, option;
        int floorNum, roomNum;

        System.out.println("Enter the number of floors: ");
        row = input.nextInt();
        System.out.println("Enter the number of rooms per floor: ");
        col = input.nextInt();

        String[][] condo = new String[row][col];

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Buy condo ");
            System.out.println("2. Sell condo ");
            System.out.println("3. Show condo information ");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    System.out.println("--------- Buy Condo ---------");
                    System.out.print("Enter the floor number (1-" + row + "): ");
                    floorNum = input.nextInt();
                    System.out.print("Enter the room number (1-" + col + "): ");
                    roomNum = input.nextInt();

                    if (floorNum < 1 || floorNum > row || roomNum < 1 || roomNum > col) {
                        System.out.println("Invalid floor or room number. Please try again.");
                        break;
                    }

                    if (condo[floorNum - 1][roomNum - 1] != null) {
                        System.out.println("Condo is already sold! Please choose another.");
                    } else {
                        System.out.print("Enter your name to buy the condo: ");
                        input.nextLine(); // clear buffer
                        String name = input.nextLine();
                        condo[floorNum - 1][roomNum - 1] = name;
                        System.out.println("Condo purchased successfully!");
                    }
                    break;

                case 2:
                    System.out.println("--------- Sell Condo ---------");
                    System.out.print("Enter the floor number (1-" + row + "): ");
                    floorNum = input.nextInt();
                    System.out.print("Enter the room number (1-" + col + "): ");
                    roomNum = input.nextInt();

                    if (floorNum < 1 || floorNum > row || roomNum < 1 || roomNum > col) {
                        System.out.println("Invalid floor or room number. Please try again.");
                        break;
                    }

                    if (condo[floorNum - 1][roomNum - 1] == null) {
                        System.out.println("Condo is not sold yet. Nothing to sell.");
                    } else {
                        System.out.println("Condo sold by: " + condo[floorNum - 1][roomNum - 1]);
                        condo[floorNum - 1][roomNum - 1] = null;
                        System.out.println("Condo is now available for purchase.");
                    }
                    break;

                case 3:
                    System.out.println("------- Condo Information -------");
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            if (condo[i][j] == null) {
                                System.out.print("Available ");
                            } else {
                                System.out.print(condo[i][j] + " ");
                            }
                        }
                        System.out.println();
                    }
                    break;

                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
        } while (option != 4);

        input.close();
    }
}
