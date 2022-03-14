#include <iostream>
#include <string>

class Date {
  int month, day, year;
public:
  Date(int m, int d, int y)
    : month(m), day(d), year(y)
  { }

  void print() const {
    std::cout << month << "/" << day << "/" << year << '\n';
  }

  void printAlpha() const {
    std::string words[] = {"January", "Febuary", "March", "April", "May", "June", "July", "August","September", "October", "November", "December"};
    std::cout << words[month-1] << " " << day << ", " << year << '\n';
  }
};

int main() {
  Date d(2, 25, 1946);
  d.print();
  d.printAlpha();
}
