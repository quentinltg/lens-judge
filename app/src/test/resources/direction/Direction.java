import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();  // Consommer le retour de ligne

        // Directions dans l'ordre
        String[] directions = {"north", "east", "south", "west"};
        int currentDirection = 0;  // Alexis commence face au nord

        for (int i = 0; i < n; i++) {
            String command = scanner.nextLine();

            switch (command) {
                case "right":
                    currentDirection = (currentDirection + 1) % 4;
                    break;
                case "left":
                    currentDirection = (currentDirection + 3) % 4;
                    break;
                case "back":
                    currentDirection = (currentDirection + 2) % 4;
                    break;
            }
        }

        System.out.println(directions[currentDirection]);
        scanner.close();
    }
}
