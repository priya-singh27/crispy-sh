import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        System.out.print("$ ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        do {
            if (input.equals("exit 0")) break;
            else if(input.matches("^\\s*\\$\\s*echo.*")) System.out.println(input);
            else System.out.println(input + ": command not found");
            System.out.print("$ ");
            input = sc.nextLine();
        } while (!input.matches(""));

    }
}
