HW6
1.
a) HashMap<Set<String>, Set<Student>> stMap = new HashMap<Set<String>, Set<Student>>();
b) TreeMap<Set<Card>, Set<Integer>> cardValue = new TreeMap<Set<Card>, Set<Integer>>();

2.
a) The key set of a TreeMap object is sorted. [True]
b) The key set of a HashMap object is sorted. [False]
c) The value set of a HashMap object cannot have duplicated elements. [True]

3.
    Collection<Set<Student>> keySet = stMap.values();

    ArrayList<Student> stValues = new ArrayList<Student>();

    for (Set<Student> iSet : keySet) {
      for (Student s : iSet) {
        stValues.add(s);
      }
    }
