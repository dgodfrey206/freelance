import java.io.*;
import java.util.*;

public class WeatherReport {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("weather_report.txt");
    	Scanner sc = new Scanner(file);
    	ArrayList<WeatherObj> weatherArr = new ArrayList<WeatherObj>();
    	
    	
        while (sc.hasNextLine()) {
        	WeatherObj obj = new WeatherObj();
        	String pre = new String();
        	int countna = 0;
        	while (!sc.hasNextInt()) {
        		String nxt = sc.next();
        		if (nxt.equals("N/A")) {
        			if (++countna == 2) {
        				break;
        			}
        		}
        		pre += nxt + " ";
        	}
        	pre = pre.trim();
        	int last = pre.lastIndexOf(" ");
        	String condition = pre.substring(last);
        	String location = pre.substring(0, last);
        	int temperature = -1;
        	
        	if (countna != 2) {
        		temperature = sc.nextInt();
        	}
        	obj.setLocation(location.trim());
        	obj.setCondition(condition.trim());
        	obj.setTemperature(temperature);
        	weatherArr.add(obj);
        	sc.nextLine();
        }
        
        BufferedWriter br = new BufferedWriter(new FileWriter(new File("output_location_only.txt")));
        Collections.sort(weatherArr, new ByTemperature());
        for (WeatherObj wobj : weatherArr) {
        	br.write(wobj.getLocation() + " " + wobj.getTemperature());
        }
        br.close();
	}

}
