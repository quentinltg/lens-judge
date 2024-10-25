# Directions dans l'ordre
directions = ["north", "east", "south", "west"]
current_direction = 0  # Alexis commence face au nord

n = int(input())  # Nombre de commandes

for _ in range(n):
    command = input().strip()

    if command == "right":
        current_direction = (current_direction + 1) % 4
    elif command == "left":
        current_direction = (current_direction + 3) % 4
    elif command == "back":
        current_direction = (current_direction + 2) % 4

print(directions[current_direction])
