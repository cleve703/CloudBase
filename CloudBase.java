import java.util.Scanner;

public class CloudBase {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String unitOfMeasure = "None chosen";
    while(unitOfMeasure == "None chosen") {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("Welcome to Cloud Base Calculator\n");
      System.out.println("Select your unit of measure:\n1) Celsius\n2) Fahrenheit\n3) Quit\n");
      int choice = input.nextInt();
      if(choice == 1) {
        unitOfMeasure = "Celsius";
      } 
      else if(choice == 2) {
        unitOfMeasure = "Fahrenheit";
      }
      else if(choice == 3) {
        break;
      }
      System.out.println("\nUnit of measure is now set to: " + unitOfMeasure + ".\n");
    }

  }
}