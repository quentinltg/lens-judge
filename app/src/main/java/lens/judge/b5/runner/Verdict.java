package lens.judge.b5.runner;

/**
 * Enum representing the possible verdicts of a program execution and verification process.
 */
public enum Verdict {
    /**
     * Indicates that the program's output matches the expected output.
     */
    ACCEPTED,

    /**
     * Indicates that the program's output does not match the expected output.
     */
    WRONG_ANSWER,

    /**
     * Indicates that the program exceeded the allowed time limit during execution.
     */
    TIME_LIMIT_EXCEEDED,

    /**
     * Indicates that the program exceeded the allowed memory limit during execution.
     */
    MEMORY_LIMIT_EXCEEDED,

    /**
     * Indicates that the program encountered a runtime error during execution.
     */
    RUNTIME_ERROR,

    /**
     * Indicates that the program failed to compile.
     */
    COMPILATION_ERROR,

    /**
     * Indicates that an internal error occurred during the execution or verification process.
     */
    INTERNAL_ERROR
}