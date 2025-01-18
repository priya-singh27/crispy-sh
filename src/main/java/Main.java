import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        System.out.print("$ ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        do {
            if (input.equals("exit 0"))
                break;

            if (input.startsWith("type") ) {
                if (!input.substring(5).startsWith("invalid_command"))
                    System.out.println(input.substring(5) + " is a shell builtin");
                else System.out.println(input.substring(5)+": command not found" );
            }
            else if (input.startsWith("echo")) {
                System.out.println(input.substring(5));
            } else {
                System.out.println(input + ": command not found");
            }
            System.out.print("$ ");
            input = sc.nextLine();
        } while (!input.matches(""));

    }
}
