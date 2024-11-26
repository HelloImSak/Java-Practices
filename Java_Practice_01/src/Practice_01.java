import java.util.Scanner;

public class Practice_01 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;
        enum days {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
            SATURDAY, SUNDAY
        }
        do {
            System.out.println("1. Wages Calculator");
            System.out.println("2. Money Exchange Program");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    int wage;
                    int hour;
                    String day;
                    days enumDay;
                    System.out.println("=====<<Wages Calculator>>=====");
                    System.out.println("Enter the number of hours worked: ");
                    hour = scanner.nextInt();
                    System.out.println("Enter the wage per hour: ");
                    wage = scanner.nextInt();
                    System.out.println("Enter the day of the week: ");
                    day = scanner.next().toUpperCase();
                    enumDay = days.valueOf(day);

                    float totalMoney = switch (enumDay) {
                        case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> hour * wage;
                        case SATURDAY, SUNDAY -> (float) (hour * wage * 2);
                    };

                    System.out.println("=====<<Result>>=====");
                    System.out.println("Hour is : " + hour + ((hour > 1) ? "hrs" : "hr"));
                    System.out.println("Wage is : " + wage + "$/1hr(s)");
                    System.out.println("Day of work : " + day);
                    System.out.println("Total Of Money is : " + totalMoney + "$");
                    System.out.println("=====<<Program End>>=====");

                }
                break;
                case 2: {
                    int choice;
                    float amount;
                    float result;
                    float rate = 4100;
                    System.out.println("1. USD to KHR");
                    System.out.println("2. KHR to USD");
                    do {
                        System.out.print("Enter your choice (1-2): ");
                        choice = scanner.nextInt();

                        if (choice != 1 && choice != 2) {
                            System.out.println("Invalid choice");
                            continue;
                        }
                        System.out.println("=====<<Money Exchange Program>>=====");
                        System.out.print("Enter the amount: " + ((choice == 1) ? "$ " : "KHR "));
                        amount = scanner.nextFloat();
                        result = switch (choice) {
                            case 1 -> amount * rate;
                            case 2 -> amount / rate;
                            default -> 0;
                        };
                        System.out.println("=====<<Result>>=====");
                        System.out.println("Money after Exchange: " + result + ((choice == 1) ? " KHR" : " $"));
                        System.out.println("=====<<Program End>>=====");
                    } while (choice != 1 && choice != 2);

                }
                break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option!! Try again!");
                    break;
            }

        } while (option != 3);
    }
}
