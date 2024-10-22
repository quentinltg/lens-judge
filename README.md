


## Liste des tâches :


| Fonctionnalités                            | Auteur(s) | Patron de conception choisi |
| ------------------------------------------ |-----------|-----------------------------|
| Représentation d’un cas de test                 | Quentin   | Pas de patron indispensable |
| Représentation d'un problème    | Quentin   | Composite                   |
| Configuration d'un problème                   | Quentin   | Builder                     |
| Représentation d'un processus       | Quentin   | State                       |
| Limitation du temps d’exécution d’un processus     | Quentin   | Decorateur                  |
| Limitation de la mémoire d’un processus    | Quentin   |          Decorateur                   |
| Compilation d’un programme C               | Bylel     |             Stratégie                |
| Compilation d’un programme C++               | Bylel     |            Stratégie                 |
| Compilation d’un programme Java               | Bylel     |            Stratégie                 |
| Compilation d’un programme Python               | Bylel     |          Stratégie                   |
| Exécution d’un programme C compilé               | Axel      |           Stratégie                  |
| Exécution d’un programme C++ compilé         | Axel      |                 Stratégie            |
| Exécution d’un programme Java compilé            | Axel      |           Stratégie                  |
| Vérification stricte de la solution          | Isaac     |            Stratégie                 |
| Vérification avec tolérance sur les réels               | Isaac     |     Stratégie                        |
| Vérification avec tolérance sur la casse               | Isaac     |      Stratégie                       |
| Vérification avec tolérance sur les espaces                | Isaac     |     Stratégie                        |
| Vérification avec tolérance sur l’ordre             | Isaac     |           Stratégie                  |
| Vérification d’une solution parmi plusieurs               | Isaac     |      Stratégie                       |
| Vérification déléguée à un programme externe               | Isaac     |     Stratégie                        |
| Configuration de l’exécution sur un cas de test               | Axel      |          Pas de patron indispensable                   |
| Programme principal du juge automatique               | Axel      |                       Pas de patron indispensable      |

## Diagramme de classes : 


```plantuml
@startuml

class SingleProblem  {
    - name: String
    - language: String
    - testCases: List<String>
    + executeTests()
}

class CompositeProblem  {
    - name: String
    - problems: List<IProblemComponent>
    + add(component: IProblemComponent)
    + remove(component: IProblemComponent)
    + getChild(index: int): IProblemComponent
}

interface IProblemComponent {
    + add(component: IProblemComponent)
    + remove(component: IProblemComponent)
    + getChild(index: int): IProblemComponent
}


class ProblemBuilder {
    - name: String
    - language: String
    - testCases: List<String>
    + setName(name: String): ProblemBuilder
    + setLanguage(lang: String): ProblemBuilder
    + addTestCase(testCase: String): ProblemBuilder
    + build(): IProblemComponent
}

class Process {
    - state: IProcessState
    + setState(state: IProcessState)
    + execute()
}

interface IProcessState {
    + handle()
}

class RunningState {
    + handle()
}

class WaitingState {
    + handle()
}

class TerminatedState {
    + handle()
}

interface IProcessDecorator {
    + execute()
}

class TimedProcessDecorator {
    - process: Process
    - timeout: long
    + execute()
}

class MemoryLimitedProcessDecorator {
    - process: Process
    - memoryLimit: long
    + execute()
}

' Compiler Interface
interface ICompiler {
    + compile(sourceFile: String): void
}

' Specific implementations for each language
class JavaCompiler {
    + compile(sourceFile: String): void
}
class CCompiler {
    + compile(sourceFile: String): void
}
class CPPCompiler  {
    + compile(sourceFile: String): void
}
class PythonCompiler {
    + compile(sourceFile: String): void
}

' Judge uses a compiler
class Judge {
    - ICompiler compiler
    - IExecutor: executor
    - IVerify: verify
    + setCompiler(ICompiler): void
    + evaluate(sourceFile: String): void
    + setExecutor(e: Executor): void
    + execute(): void
    + setVerifier(verifier: IVerify)
    + verify(solution, expected): bool
}

interface IExecutor {
    + executeProgram(): void
}

class ExecutorC {
    + executeProgram(): void
}

class ExecutorCPP {
    + executeProgram(): void
}

class ExecutorJava {
    + executeProgram(): void
}

class ExecutorPython {
    + executeProgram(): void
}

interface IVerify {
    + verify(solution, expected): bool
}

class StrictVerify {
    + verify(solution, expected): bool
}

class RealToleranceVerify {
    + verify(solution, expected): bool
}

class CaseInsensitiveVerify {
    + verify(solution, expected): bool
}

class SpaceToleranceVerify {
    + verify(solution, expected): bool
}

class OrderToleranceVerify {
    + verify(solution, expected): bool
}

class MultipleSolutionsVerify {
    + verify(solution, expected): bool
}

class ExternalProgramVerify {
    + verify(solution, expected): bool
}

' Relationships

IVerify <|-- StrictVerify
IVerify <|-- RealToleranceVerify
IVerify <|-- ExternalProgramVerify
IVerify <|-- SpaceToleranceVerify
IVerify <|-- CaseInsensitiveVerify
IVerify <|-- OrderToleranceVerify
IVerify <|-- MultipleSolutionsVerify

JavaCompiler --|> ICompiler  : <<Implements>>
CCompiler --|> ICompiler : <<Implements>>
CPPCompiler --|> ICompiler : <<Implements>>
PythonCompiler --|> ICompiler : <<Implements>>
Judge --> ICompiler : <<Use>>
Judge --> Process : <<Manage>>
Judge --> IVerify : <<Use>>
Judge --> IExecutor : <<Use>>
IExecutor <|.. ExecutorC : <<Implements>>
IExecutor <|.. ExecutorCPP : <<Implements>>
IExecutor <|.. ExecutorJava : <<Implements>>
IExecutor <|.. ExecutorPython : <<Implements>>

IProcessDecorator <|.. TimedProcessDecorator : <<Decorate>>
IProcessDecorator <|.. MemoryLimitedProcessDecorator : <<Decorate>>
Process *-- IProcessDecorator : <<Decorate>>
Process *-- IProcessState : <<State>>
IProcessState <|.. RunningState  : <<Implements>>
IProcessState <|.. WaitingState : <<Implements>>
IProcessState <|.. TerminatedState : <<Implements>>

ProblemBuilder --> SingleProblem : <<Created>>
ProblemBuilder --> CompositeProblem : <<Created>>
ProblemBuilder <-- Judge : <<Use>>

SingleProblem --> IProblemComponent : <<Implements>>
CompositeProblem --> IProblemComponent : <<Implements>>

@enduml

```

Diagramme (Dernière MAJ : 22/10/23 13:03)

```plantuml
@startuml
' Classe TestCase
class TestCase {
	- input : String
	- output : String
	+ getInput() : String
	+ setInput(input : String) : void
	+ getOutput() : String
	+ setOutput(output : String) : void
}

' Classe ProblemBuilder (pattern builder avec newInstance)
class ProblemBuilder {
	- testCases : List<TestCase>
	- timeLimit : int
	- memoryLimit : int
	+ newInstance() : ProblemBuilder
	+ withTestCases(testCases : List<TestCase>) : ProblemBuilder
	+ withTimeLimit(timeLimit : int) : ProblemBuilder
	+ withMemoryLimit(memoryLimit : int) : ProblemBuilder
	+ build() : Problem
}

' Classe Problem implémentant Iterable<TestCase>
class Problem implements Iterable<TestCase> {
	- testCases : List<TestCase>
	- timeLimit : int
	- memoryLimit : int
	+ getTestCases() : List<TestCase>
	+ setTestCases(testCases : List<TestCase>) : void
	+ getTimeLimit() : int
	+ setTimeLimit(timeLimit : int) : void
	+ getMemoryLimit() : int
	+ setMemoryLimit(memoryLimit : int) : void
	+ iterator() : Iterator<TestCase>
	+ addTestCase(testCase : TestCase) : void
	+ removeTestCase(testCase : TestCase) : void
}

' Interface IProcess
interface IProcess {
	+ getOutput() : String
+ getErrors() : List<String>
	+ start() : void
	+ stop() : void
}

' Classe ProcessAdapter implements IProcess
class ProcessAdapter implements IProcess {
	- processBuilder : ProcessBuilder
	- process : Process
	+ start() : void
	+ stop() : void
	+ getOutput() : String
}

' Classe abstraite ProcessDecorator
abstract class ProcessDecorator implements IProcess {
	- wrappedProcess : IProcess
	+ start() : void
	+ stop() : void
	+ getOutput() : String
}

' Classe TimeLimitDecorator étendant ProcessDecorator
class TimeLimitDecorator extends ProcessDecorator {
	- timeLimit : int
	- timer : Timer
	+ start() : void
	+ stop() : void
	+ checkTimeLimit() : void
}

' Classe MemoryLimitDecorator étendant ProcessDecorator
class MemoryLimitDecorator extends ProcessDecorator {
	- memoryLimit : int
	+ start() : void
	+ stop() : void
	+ checkMemoryLimit() : void
}

TestCase --> ProblemBuilder
TestCase --> Problem


interface CompilationStrategy {
    +isCompatible(String sourceFile) : boolean
    +getBinaryName(String sourceFile) : String
    +compile(String sourceFile) : void
}

abstract class AbstractCompilationStrategy implements CompilationStrategy {
    +compile(String sourceFile) : void
    #getCompileCommand(String sourceFile, String binaryName) : String
    #executeCommand(String command) : void
    +isCompatible(String sourceFile) : boolean
    +getBinaryName(String sourceFile) : String
}

class CCompilationStrategy {
    +isCompatible(String sourceFile) : boolean
    +getBinaryName(String sourceFile) : String
    +getCompileCommand(String sourceFile, String binaryName) : String
}

class CppCompilationStrategy {
    +isCompatible(String sourceFile) : boolean
    +getBinaryName(String sourceFile) : String
    +getCompileCommand(String sourceFile, String binaryName) : String
}

class JavaCompilationStrategy {
    +isCompatible(String sourceFile) : boolean
    +getBinaryName(String sourceFile) : String
    +getCompileCommand(String sourceFile, String binaryName) : String
}

class CompilerContext {
    -strategy : CompilationStrategy
    +setCompilationStrategy(CompilationStrategy strategy) : void
    +compile(String sourceFile) : void
}

CompilerContext --> CompilationStrategy
AbstractCompilationStrategy <|-- CCompilationStrategy
AbstractCompilationStrategy <|-- CppCompilationStrategy
AbstractCompilationStrategy <|-- JavaCompilationStrategy
@enduml
```
