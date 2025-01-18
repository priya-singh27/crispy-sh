import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        System.out.print("$ ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        do {
            System.out.println(input + ": command not found");
            System.out.print("$ ");
            input = sc.nextLine();
        } while (!input.matches(""));

        System.out.println("$ exit 0");
        System.exit(0);
    }
}
