#include <iostream>

void getScore(int& value) {
  std::cout << "Enter a score from 1 to 5: ";
  std::cin >> value;
  std::cout << "Your score is: " << value << '\n';
}

void printDecision(int value) {
  int decision[] = {-1,-1,7,6,5};
  std::cout << "The entered borrowing score of " << value << " is considered to be an assessment for the current loan application:\n";
  if (decision[value-1] != -1) {
    std::cout << "Provide mortgage at " << decision[value-1] << "% interest rate.\n";
  } else {
    std::cout << "No applicable mortgage.\n";
  }
}

int main() {
  int val;
  getScore(val);
  printDecision(val); 
}
