package lens.judge.b5.execution;

import lens.judge.b5.process.IProcess;
import lens.judge.b5.process.ProcessAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JavaExecutionStrategy implements IExecutionStrategy {

    private String className;
    private ProcessAdapter process;

    public JavaExecutionStrategy(String className) {
        this.className = className;
    }

    @Override
    public IProcess getProcess() {
        return process;
    }

    @Override
    public void execute(File inputFile) throws Exception {
        // System.out.println("Executing Java code : java -cp app/bin " + className);
        process = new ProcessAdapter("java", "-cp", "app/bin", className);
        process.start();

        // Écrire l'entrée dans le processus
        try (FileInputStream fis = new FileInputStream(inputFile);
             OutputStream processInput = process.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                processInput.write(buffer, 0, bytesRead);
            }
            processInput.flush();
        } catch (IOException e) {
            System.out.println("Error writing to process input: " + e.getMessage());
            throw e;
        }
    }
}
