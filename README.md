


## Liste des tâches :


| Fonctionnalités                            | Auteur(s) | Patron de conception choisi |
| ------------------------------------------ |-----------|-----------------------------|
| Représentation d’un cas de test                 | Quentin   | Pas de patron indispensable |
| Représentation d'un problème    | Quentin   | Composite                   |
| Configuration d'un problème                   | Quentin   | Builder                     |
| Représentation d'un processus       | Quentin   | State                       |
| Limitation du temps d’exécution d’un processus     | Quentin   | Decorateur                  |
| Limitation de la mémoire d’un processus    | Quentin   |          Stratégie                   |
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


 class TestCase {
        - input: String
        - expectedOutput: String
        - validationCriteria: String
        + validate(): boolean
    }

    interface IProblemComponent {
        + add(component: IProblemComponent)
        + remove(component: IProblemComponent)
        + getChild(index: int): IProblemComponent
    }

    class SingleProblem implements IProblemComponent {
        - name: String
        - language: String
        - testCases: List<TestCase>
        + executeTests()
    }

    class CompositeProblem implements IProblemComponent {
        - name: String
        - problems: List<IProblemComponent>
        + add(component: IProblemComponent)
        + remove(component: IProblemComponent)
        + getChild(index: int): IProblemComponent
    }

    class ProblemBuilder {
        - name: String
        - language: String
        - testCases: List<TestCase>
        + setName(name: String): ProblemBuilder
        + setLanguage(lang: String): ProblemBuilder
        + addTestCase(testCase: TestCase): ProblemBuilder
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

    class RunningState implements IProcessState {
        + handle()
    }

    class WaitingState implements IProcessState {
        + handle()
    }

    class TerminatedState implements IProcessState {
        + handle()
    }

    interface IProcessDecorator {
        + execute()
    }

    class TimedProcessDecorator implements IProcessDecorator {
        - process: Process
        - timeout: long
        + execute()
    }

    class MemoryLimitedProcessDecorator implements IProcessDecorator {
        - process: Process
        - memoryLimit: long
        + execute()
    }

' Compiler Interface
interface ICompiler {
    + compile(sourceFile: String): void
}

' Specific implementations for each language
class JavaCompiler implements ICompiler {
    + compile(sourceFile: String): void
}
class CCompiler implements ICompiler {
    + compile(sourceFile: String): void
}
class CPlusPlusCompiler implements ICompiler {
    + compile(sourceFile: String): void
}
class PythonCompiler implements ICompiler {
    + compile(sourceFile: String): void
}

' Judge uses a compiler
class Judge {
    - ICompiler compiler
    + setCompiler(ICompiler): void
    + evaluate(sourceFile: String): void
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

class Context {
- executor: Executor
+ setExecutor(e: Executor): void
+ execute(): void
  }
  
interface IVerify {
    + verify(solution, expected) : bool
}

class VerificationContext {
    - verify: Verify
    + setVerifier(verifier: Verifier)
    + verify(solution, expected) : bool
}


class StrictVerify {
    + verify(solution, expected) : bool
}

class RealToleranceVerify {
    + verify(solution, expected) : bool
}


class CaseInsensitiveVerify {
    + verify(solution, expected) : bool
}


class SpaceToleranceVerify {
    + verify(solution, expected) : bool
}


class OrderToleranceVerify {
    + verify(solution, expected) : bool
}


class MultipleSolutionsVerify {
    + verify(solution, expected) : bool
}


class ExternalProgramVerify {
    + verify(solution, expected) : bool
}

' Relationships

VerificationContext --> IVerify

IVerify <|-- StrictVerify

IVerify <|-- RealToleranceVerify

IVerify <|-- ExternalProgramVerify

IVerify <|-- SpaceToleranceVerify

IVerify <|-- CaseInsensitiveVerify

IVerify <|-- OrderToleranceVerify

IVerify <|-- MultipleSolutionsVerify





JavaCompiler --|> ICompiler
CCompiler --|> ICompiler
CPlusPlusCompiler --|> ICompiler
PythonCompiler --|> ICompiler
Judge --> ICompiler

Context --> IExecutor
IExecutor <|.. ExecutorC
IExecutor <|.. ExecutorCPP
IExecutor <|.. ExecutorJava
IExecutor <|.. ExecutorPython




@enduml

```

