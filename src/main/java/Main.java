import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        System.out.print("$ ");

        Scanner sc = new Scanner(System.in);
        String input, typeSubstring;
        String[] commands = { "echo", "exit", "type" };
        while (true) {
            System.out.println("$ ");
            input = sc.nextLine();

            if (input.equals("exit 0"))
                break;

            else if (input.startsWith("echo")) {
                System.out.println(input.substring(5));
            }

            else if (input.startsWith("type")) {
                typeSubstring = input.substring(5);//after type type echo or type invalid_input
                System.out.println("TRUE/FALSE"+Arrays.asList(commands).contains(typeSubstring));
                if (Arrays.asList(commands).contains(typeSubstring))
                    System.out.println(typeSubstring + " is a shell builtin");
                else
                    System.out.println(typeSubstring + " not found");
            }
            
             else {
                System.out.println(input + ": command not found");
            }
        }

    }
}
