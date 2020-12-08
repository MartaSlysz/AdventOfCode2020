package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PasswordPhilosophy {

    public static void main(String[] args) throws FileNotFoundException {
        Passwords input = convertArrayIntoPasswordSet(returnStringArray());
        System.out.println(Solution1(input));
        System.out.println(Solution2(input));
    }

    public static String[] returnStringArray() throws FileNotFoundException {
        String[] strings = new String[1000];
        int i = 0;
        try (Scanner scanner = new Scanner(new File("src/main/resources/Day2/input.txt"))) {
            while (scanner.hasNextLine()) {
                strings[i++] = scanner.nextLine();
            }
        }
        return strings;
    }

    public static Passwords convertArrayIntoPasswordSet(String[] strings) {
        List<Password> passwordsSet = new ArrayList<>();
        for (String line : strings) {
            String[] splittedLine = line.split(": ");
            String policy = splittedLine[0];
            String password = splittedLine[1];
            String[] splittedPolicy = policy.split(" ");
            String numbers = splittedPolicy[0];
            char letter = splittedPolicy[1].charAt(0);
            String[] splittedNumbers = numbers.split("-");
            int minValue = Integer.parseInt(splittedNumbers[0]);
            int maxValue = Integer.parseInt(splittedNumbers[1]);
            Password pass = new Password(password, letter, minValue, maxValue);
            passwordsSet.add(pass);
        }
        return new Passwords(passwordsSet);
    }

    public static int Solution1(Passwords passwords){
        int passwordsCount = 0;
        for(Password p: passwords.passwordList){
            int letterCount = 0;
            for (char c: p.passwordValue.toCharArray()){
                if (c == p.letter){
                    letterCount++;
                }
            }
            if (letterCount>=p.minValue && letterCount<=p.maxValue){
                passwordsCount++;
            }
        }
        return passwordsCount;
    }

    public static int Solution2(Passwords passwords){
        int passwordsCount = 0;
        for(Password p: passwords.passwordList){
            String passValue = p.passwordValue;
            if (passValue.charAt(p.minValue-1) == p.letter && passValue.charAt(p.maxValue-1) != p.letter){
                passwordsCount++;
            } else if (passValue.charAt(p.minValue-1) != p.letter && passValue.charAt(p.maxValue-1) == p.letter){
                passwordsCount++;
            }
        }
        return passwordsCount;
    }



    public static class Password{
         private final String passwordValue;
         private final char letter;
         private final int minValue;
         private final int maxValue;

        public Password(String passwordValue, char letter, int minValue, int maxValue) {
            this.passwordValue = passwordValue;
            this.letter = letter;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    public static class Passwords{
        List<Password> passwordList;

        public Passwords(List<Password> passwordList) {
            this.passwordList = passwordList;
        }
    }
}
