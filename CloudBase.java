import java.time.LocalDateTime;

public class CloudBase {
  private int temperature;
  private int dewPoint;
  private String unitOfMeasure;
  private LocalDateTime timeStamp;
  private int cloudBaseAltitude;

  public CloudBase(int temperature, int dewPoint, String unitOfMeasure) {
    this.temperature = temperature;
    this.dewPoint = dewPoint;
    this.unitOfMeasure = unitOfMeasure;
    this.timeStamp = LocalDateTime.now();
    this.cloudBaseAltitude = calculateCloudBase();
  }

  public int getTemperature() {
    return temperature;
  }

  public int getDewPoint() {
    return dewPoint;
  }

  public String getUnitOfMeasure() {
    return unitOfMeasure;
  }

  public LocalDateTime getTimeStamp() {
    return timeStamp;
  }

  public int getCloudBaseAltitude() {
    return cloudBaseAltitude;
  }
  
  public int calculateCloudBase() {
    double formulaDivisor = 1;
    // Select correct divisor depending on unit of measure
    if(this.unitOfMeasure == "Celsius") {
      formulaDivisor = 4.4;  
    } else if(this.unitOfMeasure == "Fahrenheit") {
      formulaDivisor = 2.5;
    }
    // Put numbers into formula and calculate cloud base altitude as integer
    int result = (int) Math.round(1000 * (temperature - dewPoint) / formulaDivisor);
    // It makes no sense that clouds would be below ground level, so negative values become zero
    if(result < 0) {
      result = 0;
    }
    return result;
  }

  // Return data in CSV format for convenient addition to log file
  public String toCSV() {
    return timeStamp + "," + cloudBaseAltitude + "," + temperature + "," + dewPoint + "," + unitOfMeasure;
  }

}
