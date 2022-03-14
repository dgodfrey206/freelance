import java.io.*;
import java.util.*;

public class ArrayAppS19 {
   private static ArrayList<City> citylist = new ArrayList<City>(); 
   private static ArrayList<City> shortlist = new ArrayList<City>();
   private static String state = ""; 
   public static void main(String args[]) {
	try {  createCityList(); 	} 
	catch (IOException e)  {
         		 System.out.println("Input problem. Check the location of your data file.");
	 }
	City ct = citylist.get(100);
	String sample_city_name = ct.getName();
	String sample_state_name =ct.getState(); 
	int sample_city_population = ct.getPopulation();
	System.out.println("Sample city is " + sample_city_name); 
	System.out.println("Sample city is in state " + sample_state_name ); 
	System.out.println("Sample city is of population " + sample_city_population); 
	state = "/*TODO1 your assigned state!*/"; 
	arrayOperation1(state);
	try { saveOutput();  } 
	catch (IOException e) {
         	    System.out.println("Output problem.");
 	}
   } // end main

   private static void createCityList() throws IOException { 
      File inputfile = new File("usa_city.txt");  
      BufferedReader in = new BufferedReader(
		new InputStreamReader(new FileInputStream(inputfile))); 
      City c = null;  
      String line = "";
         //loop to read each line, create a City object per line, add to citylist
      while ((line = in.readLine() ) != null) {
		c = process_city(line);
		citylist.add(c);	
       } // end loop
       in.close();
       System.out.println("List created, size is " + citylist.size());
   }

   private static City process_city(String line) {
		String [] sp = new String[5];
		String name, state;
		int population;
		sp = line.split(",", 5);
		name = sp[1].substring(2, sp[1].length() - 1);
		state = sp[3].substring(2, sp[3].length() - 1);
		population = Integer.parseInt(sp[4].substring(1, sp[4].length() - 1));
		return new City(name, state, population); 
   }

   private static void arrayOperation1(String state) {
        for (City ct:citylist)
	if ((ct.getState()).equals(state) || (ct.getState()).equals("New Jersey")) shortlist.add(ct);
       Collections.sort(shortlist); 
  }

   private static void saveOutput() throws IOException {
      File outputfile = new File("studentouput.txt"); 
  	PrintStream out = new PrintStream( new FileOutputStream(outputfile));
	out.println("Student name: " + "/*TODO2* your name*/" + "\n");
	out.println("Assigned state is  " +  state + "\n");
	out.println("input city list size is " + citylist.size());
	out.println("short citylis length is " + shortlist.size());
      for (City ct:shortlist) {
          out.println(ct.toString());
     }
      out.flush();
      out.close();
   }
} // end class