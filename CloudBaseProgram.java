import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CloudBaseProgram {
  public static void main(String[] args) {
    // Scanner to read user input for unit of measure, temperature, and dew point 
    Scanner input = new Scanner(System.in);
    String unitOfMeasure = "";
    // Clear screen
    System.out.print("\033[H\033[2J");
    System.out.flush();
    // Greet user and request user to select Celsius or Fahrenheit
    System.out.println("Welcome to Cloud Base Calculator\n");
    System.out.println("Select your unit of measure:\n\n1) Celsius\n2) Fahrenheit\n");

    // Get user input, 1 for Celsius, or 2 for Fahrenheit, saved to variable "choice"
    String choice = input.next();
    // Validation - if the user input was not either 1 or 2 user will be prompted to reenter selection
    while (!choice.equals("1") && !choice.equals("2")) {
      System.out.println("Must enter either 1 or 2. Try again.");
      choice = input.next();
    }
    // After validation, the unit of measure is set according to the input provided
    if(choice.equals("1")) {
      unitOfMeasure = "Celsius";
    } 
    else if(choice.equals("2")) {
      unitOfMeasure = "Fahrenheit";
    }

    System.out.println("\nUnit of measure is now set to: " + unitOfMeasure + ".\n");

    // User is prompted to enter the temperature.
    System.out.println("Enter the temperature in degrees " + unitOfMeasure + ":\n");
    String temperatureInput = input.next();
    // If user input is invalid, the user will be prompted to reenter the temperature
    while (!isNumeric(temperatureInput) || -150 > Integer.valueOf(temperatureInput)  || Integer.valueOf(temperatureInput) > 200) {
      System.out.println("\nMust enter an integer between -150 and 200");
      temperatureInput = input.next();
    }
    // Upon validating user input, the string is converted to an integer
    int temperature = Integer.valueOf(temperatureInput);

    // User is prompted to enter the dew point
    System.out.println("\nEnter the dew point in degrees " + unitOfMeasure + ":\n");
    String dewPointInput = input.next();
    // If user input is invalid, the user will be prompted to reenter the dew point
    while (!isNumeric(dewPointInput) || -150 > Integer.valueOf(dewPointInput)  || Integer.valueOf(dewPointInput) > 200) {
      System.out.println("\nMust enter an integer between -150 and 200");
      dewPointInput = input.next();
    }
    // Upon validating user input, the string is converted to an integer
    int dewPoint = Integer.valueOf(dewPointInput);
    
    // Close the input scanner
    input.close();

    // Create an instance of the CloudBase class
    CloudBase currentData = new CloudBase(temperature, dewPoint, unitOfMeasure);
    int currentCloudBaseAltitude = currentData.getCloudBaseAltitude();
    // Provide estimated cloud base altitude to user
    System.out.println("\nEstimated cloud base altitude is " + currentCloudBaseAltitude + " feet above ground level.\n");
    // Generate warning message if cloud base is low
    if(currentCloudBaseAltitude < 1000) {
      System.out.println("Caution! Cloud base extremely low. VFR flight may be prohibited by law.\n");
    }
    // Get one line of CSV data from the CloudBase class and save it to a variable
    String currentDataCSV = currentData.toCSV();
    // Set path to log file
    Path path = Paths.get("./cloudBaseLog.csv");
    // Save one line of CSV data to the log file
    try {
      Files.writeString(path, currentDataCSV + "\n", StandardOpenOption.APPEND);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Helper function to confirm that String values are numeric
  public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}

}