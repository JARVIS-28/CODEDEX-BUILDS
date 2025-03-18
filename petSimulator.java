import java.util.Scanner;

public class petSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hunger = 10, energy = 10, social = 10;
        String petName;
        
        System.out.print("Enter your pet's name: ");
        petName = scanner.nextLine();
        
        boolean playing = true;
        while (playing) {
            System.out.println("\nName of Sim: " + petName);
            System.out.println(" |\\__/|   (`\\");
            System.out.println(" |o o  |__ _)");
            System.out.println(" _.( T   )  `  /");
            System.out.println("((` `^--' /_<  \\");
            System.out.println(" `` `-'(((/  (((/");
            System.out.println("\nHunger: " + hunger);
            System.out.println("Energy: " + energy);
            System.out.println("Social: " + social);
            
            System.out.println("\nWhat's your next action?");
            System.out.println("[p] Play  (Hunger -2, Energy -2, Social +1)");
            System.out.println("[e] Eat   (Hunger +2 if < 5, Energy -1)");
            System.out.println("[s] Sleep (Energy +3, Hunger -1)");
            System.out.println("[q] Quit");
            System.out.print(">> ");
            
            String action = scanner.nextLine();
            
            switch (action.toLowerCase()) {
                case "p":
                    hunger -= 2;
                    energy -= 2;
                    social += 1;
                    System.out.println("\nYou played with " + petName + "! They are happy but a little tired.");
                    break;
                case "e":
                    if (hunger < 5) hunger += 2;
                    energy -= 1;
                    System.out.println("\n" + petName + " enjoyed a tasty meal!");
                    break;
                case "s":
                    energy += 3;
                    hunger -= 1;
                    System.out.println("\n" + petName + " took a nap and feels refreshed.");
                    break;
                case "q":
                    playing = false;
                    System.out.println("\nThanks for playing with " + petName + "!");
                    break;
                default:
                    System.out.println("\nInvalid input. Try again.");
            }
            
            if (hunger <= 0 || energy <= 0) {
                System.out.println("\nOh no! " + petName + " has collapsed from exhaustion or hunger. Game over.");
                playing = false;
            }
        }
        
        scanner.close();
    }
}
