public class City implements Comparable<City> {
  String name, state;
  int population;
  public City(String n, String s, int p) {
    name = n;
    state = s;
    population = p;
  }
  
  public String getName() { return name; }
  public String getState() { return state; }
  public int getPopulation() { return population; }

  public String toString() {
    return getName() + " is a city in " + getState() + ". It has a population of " + getPopulation(); 
  }

  public int compareTo(City ct) {
		int temp = state.compareTo(ct.state);
		if (temp != 0) return temp;
		else return name.compareTo(ct.name);
	}

	public boolean equals(Object obj) {
		City ct = (City) obj;
		return this.compareTo(ct) == 0;
	}
}