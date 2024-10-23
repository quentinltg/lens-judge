package lens.judge.b5;

import lens.judge.b5.problem.Problem;
import lens.judge.b5.problem.TestCase;
import lens.judge.b5.verifier.StrictComparer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Demander à l'utilisateur de saisir les noms des fichiers
            System.out.print("Entrez le chemin du fichier d'entrée: ");
            String inputFilePath = scanner.nextLine();
            System.out.print("Entrez le chemin du fichier attendu: ");
            String expectedFilePath = scanner.nextLine();

            // Créer une liste de cas de test
            List<TestCase> testCases = new ArrayList<>();
            testCases.add(new TestCase(
                    new String(Files.readAllBytes(Paths.get("app/src/main/java/lens/judge/b5/verifier/"+inputFilePath))),
                    new String(Files.readAllBytes(Paths.get("app/src/main/java/lens/judge/b5/verifier/"+expectedFilePath)))
            ));

            // Créer un StrictComparer
            StrictComparer comparer = new StrictComparer();

            // Créer une instance de Problem avec les cas de test et le comparer
            Problem problem = new Problem(testCases, 1000, 256, comparer);

            // Vérifier chaque cas de test
            for (TestCase testCase : problem.getTestCases()) {
                boolean result = problem.verifyTestCase(testCase);
                System.out.println("Résultat du cas de test: " + result);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des fichiers: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}