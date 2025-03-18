import random
import sys
import time

# ANSI color codes
COLORS = [
    "\033[91m", "\033[92m", "\033[93m", "\033[94m",
    "\033[95m", "\033[96m", "\033[97m", "\033[90m"
]
RESET = "\033[0m"

def draw_rectangle():
    width = random.randint(5, 15)
    height = random.randint(3, 8)
    char = random.choice(["#", "*", "@", "&", "%"])
    color = random.choice(COLORS)

    for _ in range(height):
        print(color + char * width + RESET)

def draw_triangle():
    size = random.randint(5, 10)
    char = random.choice(["#", "*", "@", "&", "%"])
    color = random.choice(COLORS)

    for i in range(1, size + 1):
        print(color + (char * i).center(size) + RESET)

def draw_circle():
    radius = random.randint(3, 6)
    char = random.choice(["#", "*", "@", "&", "%"])
    color = random.choice(COLORS)

    for y in range(-radius, radius + 1):
        row = ""
        for x in range(-radius * 2, radius * 2 + 1, 2):
            if (x / 2) ** 2 + y ** 2 <= radius ** 2:
                row += char
            else:
                row += " "
        print(color + row + RESET)

def generate_random_shape():
    shape = random.choice([draw_rectangle, draw_triangle, draw_circle])
    shape()
    time.sleep(0.5)  # Short delay for better visibility

if __name__ == "__main__":
    while True:
        choice = input("\nType 'generate' to create a random shape or 'exit' to quit: ").strip().lower()
        
        if choice == "generate":
            generate_random_shape()
        elif choice == "exit":
            print("Exiting program. Goodbye!")
            break
        else:
            print("Invalid command! Type 'generate' or 'exit'.")





