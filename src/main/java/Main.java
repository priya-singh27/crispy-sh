import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String input;
        String[] commands = { "echo", "exit", "type" };
        
        while (true) {
            System.out.print("$ ");
            input = sc.nextLine().trim();

            if (input.equals("exit 0")) {
                break;
            }
                
            else if (input.startsWith("echo")) {
                System.out.println(input.substring(5));
            }

            else if (input.startsWith("type")) {
                String typeSubstring = input.substring(5).trim();
                if (Arrays.asList(commands).contains(typeSubstring)) {
                    System.out.println(typeSubstring + " is a shell builtin");
                }
                else {
                    String path = searchInPath(typeSubstring);
                    if (path != null) {
                        System.out.println(typeSubstring + " is " + path);
                    } else {
                        System.out.println(typeSubstring + ": not found");
                    }
                }
            }
            
            else {
                String[] parts = input.split("\\s+");
                String command = parts[0];
                
                // Search for the command in PATH
                String programPath = searchInPath(command);
                
                if (programPath != null) {
                    try {
                        // Create process builder with command and all arguments
                        ProcessBuilder processBuilder = new ProcessBuilder(parts);
                        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
                        
                        // Start the process and wait for it to complete
                        Process process = processBuilder.start();
                        process.waitFor();
                    } catch (IOException | InterruptedException e) {
                        System.out.println(command + ": execution failed");
                    }
                } else {
                    System.out.println(command + ": command not found");
                }
            }
        }
    }

    private static String searchInPath(String command) {
        String pathEnv = System.getenv("PATH");
        if (pathEnv == null) return null;

        String[] directories = pathEnv.split(":");
        
        for (String directory : directories) {
            File file = new File(directory + "/" + command);
            if (file.exists() && file.canExecute()) {
                return file.getAbsolutePath();
            }
        }
        
        return null;
    }
}