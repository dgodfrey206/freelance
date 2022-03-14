#include <iostream>
#include <climits>

double smaller(double x, double y) {
  return x < y ? x : y;
}

int main() {
  std::cout << "Enter 15 numbers: ";
  double smallest = INT_MAX;
  for (int i=0; i<15; i++) {
    double x; std::cin >> x;
    smallest = smaller(smallest, x);
  }
  std::cout << "The smallest number that has been entered at this time is " << smallest << ".\n";
}
