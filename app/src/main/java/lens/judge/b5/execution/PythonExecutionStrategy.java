package lens.judge.b5.execution;

import lens.judge.b5.process.IProcess;
import lens.judge.b5.process.ProcessAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PythonExecutionStrategy implements IExecutionStrategy {

    private String scriptName;
    private ProcessAdapter process;

    public PythonExecutionStrategy(String scriptName) {
        this.scriptName = scriptName;
    }

    @Override
    public IProcess getProcess() {
        return process;
    }

    @Override
    public void execute(File inputFile) throws Exception {
        // System.out.println("Executing Python code : python3 " + scriptName);
        process = new ProcessAdapter("python3", scriptName);
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
