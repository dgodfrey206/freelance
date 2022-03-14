#include <iostream>
#include <string>

class calendar {
  int month, week, day;
  bool warningOn = false;
  bool warn = false;
public:
  calendar()
    : month(1), week(1), day(1)
  { }
  calendar(int month, int week, int day)
    : month(month), week(week), day(day)
  { }

  void addOneDay() {
    if (month == 11 && week == 3 && day == 6) {
      return;
    }
    day = (day + 1) % 7;
    if (day == 0) {
      week = (week + 1) % 4;
      if (week == 0) {
        month = (month + 1) % 12;
      }
    }
    warn = false;
    if (month == 11 && week == 3 && day == 1) {
      warn = true;
    }
  }

  void displayDate() {
    std::cout << "The date is " << (month+1) << "/" << (week+1) << "/" << (day+1) << std::endl;
  }

  void message() const {
    if (warningOn && warn && month == 11 && week == 3 && day == 1) {
      std::cout << "TODAY IS THE LAST DAY TO DROP THE COURSE\n";
    }
  }
  
  void setWarningOn() { warningOn = true; }
};

int main() {
  // 12/4/1 is the last day to drop a course
  calendar c(11,2,1);
  for (int i=0; i<20; i++) {
    c.displayDate();
    c.message();
    c.addOneDay();
  }
}
