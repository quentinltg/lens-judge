package lens.judge.b5.execution;

import lens.judge.b5.process.IProcess;
import lens.judge.b5.process.ProcessAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CExecutionStrategy implements IExecutionStrategy {

    private String binaryName;
    private ProcessAdapter process;

    public CExecutionStrategy(String binaryName) {
        this.binaryName = binaryName;
    }

    @Override
    public IProcess getProcess() {
        return process;
    }

    @Override
    public void execute(File inputFile) throws Exception {
        // System.out.println("Executing C/C++ code : app/bin/" + binaryName);
        process = new ProcessAdapter("app/bin/" + binaryName);
        process.start();

        // Écrire l'entrée dans le processus
        try (FileInputStream fis = new FileInputStream(inputFile);
             OutputStream processInput = process.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                processInput.write(buffer, 0, bytesRead);
            }
            processInput.flush();  // S'assurer que toutes les données sont bien transmises
        } catch (IOException e) {
            System.out.println("Error writing to process input: " + e.getMessage());
            throw e;
        }
    }
}
