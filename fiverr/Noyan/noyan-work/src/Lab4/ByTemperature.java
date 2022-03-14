import java.util.*;

public class ByTemperature implements Comparator<WeatherObj> {
	public int compare(WeatherObj obj1, WeatherObj obj2) {
		return obj1.getTemperature() - obj2.getTemperature();
	}
}
