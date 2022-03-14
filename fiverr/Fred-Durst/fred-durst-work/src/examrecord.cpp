#include <iostream>
#include <sstream>
#include <iomanip>
#include <fstream>
#include <limits>
#include <string>
#include <climits>
#include <vector>
#include <cmath>
#include <algorithm>

bool Input(int& x, std::string const& msg = "", int low=1, int high=0) {
  while (true) {
    std::cout << msg;
    if (std::cin >> x) {
      if (low <= high && !(low <= x && x <= high)) {
        continue;
      }
      break;
    }
    std::cin.clear();
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
  }
  return true;
};

bool Search(int id, int(&exam)[3]) {
  std::ifstream ifs("grades.txt");
  int xid;
  while (ifs >> xid) {
    if (xid == id) {
      ifs >> exam[0] >> exam[1] >> exam[2];
      return true;
    }
    ifs.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
  }
  return false;
}

void DisplayStudent() {
  std::cout << "ID: ";
  int id; std::cin >> id;
  int exam[3] = {};
  double avg = 0;
  if (!Search(id, exam)) {
    std::cout << "The student ID and record cannot be found.\n";
    return;
  }
  std::system("cls");
  std::cin.get();
  std::cout << std::setw(10) << std::left << "Exam 1"
            << std::setw(10) << std::left << "Exam 2" 
                                << std::left << "Exam 3" << std::endl;
  for (int i=0; i<3; i++) {
    avg += exam[i]/3.0;
    std::cout << std::setw(10) << std::left << exam[i];
  }
  std::cout << "\nAverage = " << avg << std::endl;
}

void CreateStudent() {
  std::system("cls");
  int id, exam[3];
  Input(id, "ID: ", 1, 9999);
  for (int i=0; i<3; i++) {
    std::string msg = "Score for exam"; msg += '0'+(i+1); msg += ": ";
    Input(exam[i], msg, 0, 100);
  }
  std::ofstream("grades.txt", std::ios_base::app) << id << " " << exam[0] << " " << exam[1] << " " << exam[2] << std::endl;
}

void Exit() {
  exit(EXIT_SUCCESS);
}

void DisplayAll() {
  std::cout << std::setw(10) << std::left << "ID" << std::setw(10) << std::left << "Exam1" << std::setw(10) << std::left << "Exam2" << std::setw(10) << std::left << "Exam3" << std::endl;
  std::ifstream ifs("grades.txt");
  for (int data, y = 0; ifs >> data; y = (y+1)%4) {
    std::cout << std::setw(10) << std::left << data;
    if (y == 3) std::cout << '\n';
  }
}

double StandardDev(std::vector<int> grades, double avg) {
  double sum = 0;
  for (int grade : grades) {
    sum += (grade - avg) * (grade - avg);
  }
  return std::sqrt(sum/grades.size());
}

void GetExamStats() {
  using std::setw;
  using std::left;

  system("cls");
  int examNum;
  Input(examNum, "Enter exam number: ", 1, 3);
  std::ifstream ifs("grades.txt");

  double avg = 0;
  int gradeFreq[101] = {};

  std::vector<int> grades;

  for (std::string line; std::getline(ifs, line); ) {
    std::stringstream ss(line);
    int id; ss >> id;
    for (int i=1; i<=3; i++) {
      int x; ss >> x;
      if (i == examNum) {
        grades.push_back(x);
        gradeFreq[x]++;
        avg += x;
      }
    }
  }

  if (grades.empty()) {
    std::cout << "No data to report.";
    Exit();
  }
  
  avg = avg/grades.size();
  int minGrade = *min_element(grades.begin(), grades.end());
  int maxGrade = *max_element(grades.begin(), grades.end());

  std::cout << '\n';

  std::cout << setw(15) << left << "Test takers: " << grades.size() << std::endl;
  std::cout << setw(15) << left << "Exam Average: " << avg << std::endl;
  std::cout << setw(15) << left << "Min grade: " << minGrade << std::endl;
  std::cout << setw(15) << left << "Max grade: " << maxGrade << std::endl;
  std::cout << setw(15) << left << "Standard Deviation: " << StandardDev(grades, avg) << std::endl;
  std::cout << '\n';

  int dist[10] = {};
  int sum = 0;
  for (int i = 0, k = 0; i < 101; i++) {
    if (i > 0 && i % 10 == 0) {
      dist[k++] = sum;
      sum = 0;
    }
    sum += gradeFreq[i];
  }
  dist[9] += gradeFreq[100];
  
  std::cout << setw(26) << left << "Greater than 100" << 0 << std::endl;

  for (int i = 90, k = 9; i >= 0; i -= 10, k--) {
    std::cout << setw(2) << left << i << " - " << setw(3) << left << (i==90?100:(i+9));

    std::cout << setw(18) << left << " " << dist[k] << std::endl;
  }
}

void Menu() {
  while (true) {
    std::system("cls");
    std::cout << "1. Input a student grade\n"
              << "2. Display all grades\n"
              << "3. Display a Student’s exam statistics\n"
              << "4. Display an Exam’s statistics\n"
              << "5. Exit\n";
    int choice;
    Input(choice, "choice=", 1, 5);

    using fptr = void(*)();
    fptr table[5] = {CreateStudent,DisplayAll,DisplayStudent,GetExamStats,Exit};
    table[choice-1]();

    std::cout << "Press enter to return to the main menu.\n";
    std::cin.clear();
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    std::cin.get();
  }
}

int main() {
  Menu();
}
