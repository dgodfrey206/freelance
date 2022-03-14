import java.util.*;
import java.lang.*;
import java.io.*;

public class ByPopulation implements Comparator<City> {
  public int compare(City a, City b) {
    return a.getPopulation() - b.getPopulation();
  }
}