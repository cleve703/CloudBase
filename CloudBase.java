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
    if(this.unitOfMeasure == "Celsius") {
      formulaDivisor = 4.4;  
    } else if(this.unitOfMeasure == "Fahrenheit") {
      formulaDivisor = 2.5;
    }
    int result = (int) Math.round(1000 * (temperature - dewPoint) / formulaDivisor);
    if(result < 0) {
      result = 0;
    }
    return result;
  }

  public String toCSV() {
    return timeStamp + "," + cloudBaseAltitude + "," + temperature + "," + dewPoint + "," + unitOfMeasure;
  }

}
