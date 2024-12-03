import java.util.Arrays;
import java.util.Scanner;

public class Practice_02 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //Allow user to allocate the size of array
        int n;
        System.out.print("Enter n: ");
        n = sc.nextInt();
        int[] arr = new int[n];

        //Allow user to input every element of array
        for (int i = 0; i < n; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }
        System.out.println("Elements of Array: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("--------------------------");
        //Compute sum of array elements
        int sum = 0;
        for(int num : arr){
            sum += num;
        }
        System.out.println("Sum: " + sum);

        //Compute average of array elements
        float avg = (float) sum / arr.length;
        System.out.println("Average: " + String.format("%.2f", avg));

        //Find the maximum element of array
        int max = arr[0];
        for(int num : arr){
            if(num > max){
                max = num;
            }
        }
        System.out.println("Max number: " + max);
    }
}
