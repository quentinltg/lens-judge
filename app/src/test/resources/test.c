// test.c
#include <stdio.h>

int main() {
    int n;
    // Lire l'entrée
    if (scanf("%d", &n) != 1) {
        return 1; // Échec de la lecture
    }
    // Ajouter 10 au nombre
    n += 10;
    // Imprimer le résultat
    printf("%d\n", n);
    return 0;
}
