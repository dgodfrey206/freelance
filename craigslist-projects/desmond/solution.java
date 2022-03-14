import java.util.*;

public class Main {
  static Scanner scan = new Scanner(System.in);

 public static void main(String[] args) {
    CourseCSC225 csc225 = new CourseCSC225("Data Structure");

    csc225.addStudent("alma", 34);

    csc225.addStudent("john", 66);

    csc225.addStudent("baez", 90);

    csc225.addStudent("michael", 100);

    csc225.addStudent("james", 87);

    System.out.println("Students:");
    System.out.println(csc225);

    ///// insert or add more students ./////////

    System.out.println("Enter the name of student: ");

    String student = scan.next();

    System.out.println("Enter the student grade: ");

    double grade1 = scan.nextDouble();

    csc225.addStudent(student, grade1);

    System.out.println(csc225);
  }
}

class CourseCSC225 {
 private String courseName;
 private int numberOfStudents = 0;

 public
  class Student {
   private String studentName;
   private double grade;
   public String getStudentName() { return studentName; }
   public double getStudentGrade() { return grade; }
   public Student(String name, double grade) {
      this.studentName = name;
      this.grade = grade;
    }
   public Student(Student other) {
      this.studentName = other.studentName;
      this.grade = other.grade;
    }
  }

  private Student[] students = new Student[1];

 public CourseCSC225(String courseName) { this.courseName = courseName; }

 public Student[] getStudents() {
    Student[] slice = new Student[numberOfStudents];
    for (int i = 0; i < numberOfStudents; i++) {
      slice[i] = new Student(students[i]);
    }
    return slice;
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < numberOfStudents; i++) {
      result.append("Name = " + students[i].getStudentName() + ", Grade = " 
               + students[i].getStudentGrade() + "\n");
    }
    return result.toString();
  }

 public int getNumberOfStudents() { return students.length; }

 public String getCourseName() { return courseName; }

  ///////////////// add students method ///////////////////

 public void addStudent(String student, double grade) {
    Student newStudent = new Student(student, grade);
    if (numberOfStudents == students.length) {
      Student[] a = new Student[students.length * 2];

      for (int i = 0; i < numberOfStudents; i++) {
        a[i] = students[i];
      }

      students = a;
    }

    students[numberOfStudents++] = newStudent;
  }

  /////////////////////////////////////

 public void dropStudent(String student) {
    int position = findStudent(student);

    if (position >= 0) {
      dropStudent(position);

    }

    else {
      System.out.println(
          "The student doesnt exists in this course.. try again");
    }
  }

 private void dropStudent(int position) {
    for (int i = 0; i < numberOfStudents; i++) {
      if (position == i) {
        while (i + 1 < numberOfStudents) {
          students[i] = students[i + 1];
          i++;
        }
      }
    }

    numberOfStudents--;
  }

 private int findStudent(String student) {
    for (int i = 0; i < numberOfStudents; i++) {
      if (students[i].getStudentName().equals(student)) {
        return i;
      }
    }

    return -1;
  }
}