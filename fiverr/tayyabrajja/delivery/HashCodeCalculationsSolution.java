import java.util.*;
import java.io.*;

public class HashCodeCalculationsSolution {

    /**
     * Always returns 0.
     */
    public static int hashCode0(String s) {
        return 0;
    }
    
    /**
     * Returns the ASCII code of the first character in the string.
     */
    public static int hashCode1(String s) {
        if(s.isEmpty()) {
            return 0;
        } else {
            return (int) s.charAt(0); // (int) casts a value to an integer
        }
    }
    
    /**
     * Returns the sum of the ASCII codes of the characters in the string.
     */
    public static int hashCode2(String s) {
        int hashCode = 0;
        for(int i = 0; i < s.length(); i++) {
            hashCode += (int) s.charAt(i);
        }
        return hashCode;
    }
    
    /**
     * Returns the sum of the values in this form:
     *   sum = a_1*129^(n-1) + a_2*129^(n-2) + ... + a_{n-1}*129^(1) + a_n*129^(0)
     * where a_{i} is the ASCII code of the ith character in the string, and n is
     * the length of the string.
     */
    public static int hashCode3(String s) {
        int hashCode = 0;
        for(int i = 0; i < s.length(); i++) {
            hashCode = 129*hashCode + (int) s.charAt(i);
        }
        return hashCode;
    }

  public static int numberOfEmptyBuckets(int[] buckets) {
    int count = 0;
    for (int i = 0; i < buckets.length; i++) {
      if (buckets[i] == 0) {
        count++;
      }
    }
    return count;
  }
  public static double averageNumberOfItemsPerNonEmptyBucket(int[] buckets) {
    int numberOfItems = 0;
    int numberOfNonEmptyBuckets = 0;
    for (int i = 0; i < buckets.length; i++) {
      if (buckets[i] != -1) {
        numberOfItems += buckets[i];
        numberOfNonEmptyBuckets++;
      }
    }
    return numberOfItems * 1.0 / numberOfNonEmptyBuckets;
  }

  public static int compressToSize(int hashCode, int numberOfBuckets) {
    int hashIndex = hashCode % numberOfBuckets;
    if (hashIndex < 0) {
      hashIndex = hashIndex + numberOfBuckets;
    }
    return hashIndex;
  }

  private static int getHashCode(String word, int hashCodeFunctionToUse) {
    int hashCode = -1;
    if (hashCodeFunctionToUse == -1) {
      hashCode = word.hashCode();
    } else if (hashCodeFunctionToUse == 0) {
      hashCode = hashCode0(word);
    } else if (hashCodeFunctionToUse == 1) {
      hashCode = hashCode1(word);
    } else if (hashCodeFunctionToUse == 2) {
      hashCode = hashCode2(word);
    } else if (hashCodeFunctionToUse == 3) {
      hashCode = hashCode3(word);
    } else {
      System.err.println("Incorrect hashCodeFunctionToUse in collisionCounter!");
      System.exit(0);
    }
    return hashCode;
  }

  public static int[] collisionCounter(int numBuckets, String file, int hashCodeFunctionToUse) {
    // Initialize the variables you'll need to count collisions (an array, a set)
    int[] counts = new int[numBuckets];
    Set<String> seenWords = new HashSet<>();
    try {
      Scanner scanner = new Scanner(new File(file));
      //Write your code for counting collisions here.
      while (scanner.hasNext()) {
        String word = scanner.next().toLowerCase();
        if (!seenWords.contains(word)) { // only consider words not seen yet.
          seenWords.add(word);
          int hashIndex = compressToSize(getHashCode(word, hashCodeFunctionToUse), numBuckets);
          counts[hashIndex]++;
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return counts; // Change this line to return your count of collisions
  }

  public static void main(String[] args) {
    int[] sizes = {196613, 200000};
    for (int i = 0; i < 2; i++) {
      for (int j = -1; j < 4; j++) {
        System.out.println("Size = " + sizes[i] + ", Hash function #" + j);
        int[] buckets = HashCodeCalculationsSolution.collisionCounter(sizes[i], args[0], j);
        System.out.println("Number of empty buckets: " + HashCodeCalculationsSolution.numberOfEmptyBuckets(buckets));
        System.out.println("Average number of items per non-empty bucket: " + HashCodeCalculationsSolution.averageNumberOfItemsPerNonEmptyBucket(buckets));
      }
    }
  }
}
