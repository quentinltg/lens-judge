package lens.judge.b5.compiler;

import lens.judge.b5.process.ProcessAdapter;

public abstract class AbstractCompilationStrategy implements ICompilationStrategy {

    @Override
    public void compile(String sourceFile) throws Exception {
        String binaryName = getBinaryName(sourceFile);
        String compileCommand = getCompileCommand(sourceFile, binaryName);
        executeCommand(compileCommand);
    }

    protected abstract String getCompileCommand(String sourceFile, String binaryName);

//    protected void executeCommand(String command) {
//        try {
////            ProcessBuilder builder = new ProcessBuilder(command.split(" "));
////            ProcessAdapter process = new ProcessAdapter(String.valueOf(builder));
//            ProcessAdapter process = new ProcessAdapter(command.split(" "));
//            process.start();
//            process.waitFor();
//
//            if (!process.getErrorOutput().isEmpty()) {
//                System.out.println("Compilation Errors: " + process.getErrorOutput());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void executeCommand(String command) throws Exception {
        ProcessAdapter process = new ProcessAdapter(command.split(" "));
        process.start();

        // Attendre la fin du processus avant de continuer
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new Exception("Process exited with non-zero code: " + exitCode);
        }
    }
}
