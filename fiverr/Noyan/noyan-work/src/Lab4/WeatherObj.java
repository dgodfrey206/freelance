
public class WeatherObj implements Comparable<WeatherObj> {
	private String location, condition;
	private int temperature;
	
	public void setLocation(String loc) {
		location = loc;
	}
	public void setCondition(String cond) {
		condition = cond;
	}
	public void setTemperature(int temp) {
		temperature = temp;
	}
	
	public int compareTo(WeatherObj obj) {
		return this.location.compareTo(obj.location);
	}
	
	public String getLocation() {
		return location;
	}
	public String getCondition() {
		return condition;
	}
	public int getTemperature() {
		return temperature;
	}
	
	public String toString() {
		return getLocation() + " " + getCondition() + " " + getTemperature();
	}
}
