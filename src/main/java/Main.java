import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String input, typeSubstring;
        String[] commands = { "echo", "exit", "type" };
        System.setProperty("PATH", "C:\\Windows\\System32;C:\\Program Files");
        
        while (true) {
            System.out.print("$ ");
            input = sc.nextLine();

            if (input.equals("exit 0")) {
                break;
            }
                
            else if (input.startsWith("echo")) {
                System.out.println(input.substring(5));
            }

            else if (input.startsWith("type")) {
                typeSubstring = input.substring(5).trim();
                if (Arrays.asList(commands).contains(typeSubstring)) {
                    System.out.println(typeSubstring + " is a shell builtin");
                }
                else {
                    // Add .exe extension for Windows if no extension is provided
                    String searchCommand = typeSubstring;
                    if (System.getProperty("os.name").toLowerCase().contains("windows") 
                        && !typeSubstring.toLowerCase().endsWith(".exe")) {
                        searchCommand = typeSubstring + ".exe";
                    }
                    
                    String path = searchInPath(searchCommand);
                    if (path != null) {
                        System.out.println(typeSubstring + " is " + path);
                    } else {
                        System.out.println(typeSubstring + ": not found");
                    }
                }
            }
            
            else {
                System.out.println(input + ": command not found");
            }
        }
    }

    private static String searchInPath(String command) {
        String pathEnv = System.getenv("PATH");
        if (pathEnv == null) return null;

        String[] directories = pathEnv.split(";");
        
        for (String directory : directories) {
            File file = new File(directory + File.separator + command);
            if (file.exists() && file.canExecute()) {
                return file.getAbsolutePath();
            }
        }
        
        return null;
    }
}