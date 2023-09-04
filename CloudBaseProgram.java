import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CloudBaseProgram {
  public static void main(String[] args) {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    Scanner input = new Scanner(System.in);
    String unitOfMeasure = "None chosen";
    int choice;
    while(true) {
      try {
        System.out.println("Welcome to Cloud Base Calculator\n");
        System.out.println("Select your unit of measure:\n1) Celsius\n2) Fahrenheit\n");
        choice = input.nextInt();
        if(choice == 1) {
          unitOfMeasure = "Celsius";
        } 
        else if(choice == 2) {
          unitOfMeasure = "Fahrenheit";
        }
        else {
          throw new RuntimeException();
        }
        System.out.println("\nUnit of measure is now set to: " + unitOfMeasure + ".\n");
        break;
      }
      catch (Exception e) {
        System.out.println("\nSomething went wrong. Let's try that again.\n");
      }
    }
    int temperature;
    while(true) {
      try {
        System.out.println("Enter the temperature in degrees " + unitOfMeasure + ":\n");
        temperature = input.nextInt();
        break;
      }
      catch (Exception e) {
        System.out.println("\nSomething went wrong. Let's try that again.\n");
      }
    }
    int dewPoint;
    while(true) {
      try {
        System.out.println("\nEnter the dew point in degrees " + unitOfMeasure + ":\n");
        dewPoint = input.nextInt();
        break;
      }
      catch (Exception e) {
        System.out.println("\nSomething went wrong. Let's try that again.\n");
      }
    }
    input.close();
    CloudBase currentData = new CloudBase(temperature, dewPoint, unitOfMeasure);
    System.out.println("\nEstimated cloud base altitude is " + currentData.getCloudBaseAltitude() + " feet above ground level.\n");
    System.out.println(currentData.toCSV() + "\n");
    String currentDataCSV = currentData.toCSV();
    Path path = Paths.get("./cloudBaseLog.csv");
    try {
      Files.writeString(path, currentDataCSV + "\n", StandardOpenOption.APPEND);
      // Files.writeString(path, "test", StandardOpenOption.APPEND);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}