


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

' Relationships
JavaCompiler --|> ICompiler
CCompiler --|> ICompiler
CPlusPlusCompiler --|> ICompiler
PythonCompiler --|> ICompiler
Judge --> ICompiler
@enduml

```

