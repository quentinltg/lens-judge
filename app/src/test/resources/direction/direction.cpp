#include <iostream>
#include <string>

int main() {
    int n;
    std::cin >> n;

    // Directions dans l'ordre
    std::string directions[] = {"north", "east", "south", "west"};
    int currentDirection = 0;  // Alexis commence face au nord

    std::string command;
    for (int i = 0; i < n; i++) {
        std::cin >> command;

        if (command == "right") {
            currentDirection = (currentDirection + 1) % 4;
        } else if (command == "left") {
            currentDirection = (currentDirection + 3) % 4;
        } else if (command == "back") {
            currentDirection = (currentDirection + 2) % 4;
        }
    }

    std::cout << directions[currentDirection] << std::endl;
    return 0;
}
