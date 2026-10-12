package ui;

import java.util.Scanner;


public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Қате: сан енгізіңіз!");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
