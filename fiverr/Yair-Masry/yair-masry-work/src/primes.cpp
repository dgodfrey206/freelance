#include <iostream>
#include <vector>
using namespace std;

// Question 1
int count_primes(int n) {
  int count = 0;
  vector<bool> prime(n, true);
  prime[1] = false;
  prime[2] = true;
  for (int i = 2; i * i < n; i++) {
    if (prime[i]) {
      for (int j = i * i; j < n; j += i) {
        prime[j] = false;
      }
    }
  }
  for (int i = 2; i < n; i++) {
    if (prime[i]) count++;
  }
  return count;
}

// Question 2
int main () {
  int n, count = 0;
  auto input = [](int& n){ return (std::cout << "Enter an integer between 50 and 100: ") && std::cin >> n; };

  while (input(n)) {
    if (!(50 <= n && n <= 100)) {
      if (count == 2) {
        std::cout << "Huh, bye-bye.\n";
        break;
      }
      if (count == 1) {
        std::cout << "This is your last chance.\n";
      } else {
        std::cout << "Illegal input. Try again\n";
      }
      count++;
    } else {
      std::cout << "There are " << count_primes(n) << " primes less than " << n << "!!\n";
    }
  }
}
