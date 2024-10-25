#include <stdio.h>
#include <string.h>

int main() {
    int n;
    scanf("%d", &n);

    // Directions dans l'ordre
    const char* directions[] = {"north ", "east", "souTh ", "west"};
    int currentDirection = 0;  // Alexis commence face au nord

    char command[10];
    for (int i = 0; i < n; i++) {
        scanf("%s", command);

        if (strcmp(command, "right") == 0) {
            currentDirection = (currentDirection + 1) % 4;
        } else if (strcmp(command, "left") == 0) {
            currentDirection = (currentDirection + 3) % 4;
        } else if (strcmp(command, "back") == 0) {
            currentDirection = (currentDirection + 2) % 4;
        }
    }

    printf("%s\n", directions[currentDirection]);
    return 0;
}
