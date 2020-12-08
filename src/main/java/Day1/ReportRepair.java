package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReportRepair {

    public static void main(String[] args) throws FileNotFoundException {

        int[] input = returnIntArray();

        System.out.println("Task 1 solution: " +Task1Solution(input));
        System.out.println("Task 2 solution: " +Task2Solution(input));

    }

    public static int[] returnIntArray() throws FileNotFoundException {
        int[] ints = new int[200];
        int i = 0;
        try (Scanner scanner = new Scanner(new File("src/main/resources/Day1/input.txt"))) {
            while (scanner.hasNext()) {
                ints[i++] = scanner.nextInt();
            }
        }
        return ints;
    }

    public static int Task1Solution(int[] input){
        int result = 0;
        for (int i = 1; i < input.length; i++){
            for (int j = 0; j < input.length-1; j++){
                if(input[i]+input[j]==2020){
                    result = input[i]*input[j];
                }
            }
        }
        return result;
    }

    public static int Task2Solution(int[] input){
        int result = 0;
        for (int k = 2; k < input.length; k++){
            for (int j = 1; j < input.length-1; j++){
                for (int i = 0; i < input.length-2; i++){
                    if(input[i]+input[j]+input[k]==2020){
                        result = input[i]*input[j]*input[k];
                    }
                }
            }
        }

        return result;
    }
}
