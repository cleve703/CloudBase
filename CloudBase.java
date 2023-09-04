import java.util.Scanner;

public class CloudBase {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String unitOfMeasure = "None chosen";
    double formulaDivisor = 1;
    while(unitOfMeasure == "None chosen") {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("Welcome to Cloud Base Calculator\n");
      System.out.println("Select your unit of measure:\n1) Celsius\n2) Fahrenheit\n3) Quit\n");
      int choice = input.nextInt();
      if(choice == 1) {
        unitOfMeasure = "Celsius";
        formulaDivisor = 4.4;
      } 
      else if(choice == 2) {
        unitOfMeasure = "Fahrenheit";
        formulaDivisor = 2.5;
      }
      else if(choice == 3) {
        break;
      }
      System.out.println("\nUnit of measure is now set to: " + unitOfMeasure + ".\n");
    }
    int temperature = 999;
    while(temperature == 999) {
      System.out.println("Enter the temperature in degrees " + unitOfMeasure + ":\n");
      temperature = input.nextInt();
    }
    int dewPoint = 999;
    while(dewPoint == 999) {
      System.out.println("\nEnter the dew point in degrees " + unitOfMeasure + ":\n");
      dewPoint = input.nextInt();
    }
    int cloudBaseAltitude = (int) Math.round(1000 * (temperature - dewPoint) / formulaDivisor);
    if(cloudBaseAltitude < 0){
      cloudBaseAltitude = 0;
    }
    System.out.println("\nEstimated cloud base altitude is " + cloudBaseAltitude + " feet above ground level.\n");
  }
}