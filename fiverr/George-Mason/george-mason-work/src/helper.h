#ifndef HELPER_H
#define HELPER_H

#include <limits>
#include <iostream>
#include <iomanip>

bool Input(std::istream& is, int& x, std::string const& str = "") {
  while (true) {
    if (!str.empty()) std::cout << str;
    if (is >> x) {
      break;
    }
    is.clear();
    is.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
  }
  return true;
};

bool InputWithRange(std::istream& is, int& x, int low, int high, std::string const& msg = "") {
  while (true) {
    if (!msg.empty()) std::cout << msg;
    if (is >> x) {
      if (!(low <= x && x <= high)) {
        continue;
      }
      break;
    }
    is.clear();
    is.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
  }
  return true;
};

struct PrintHelper {
    bool b;
    template<class T>
    void operator()(T&& x) { if(b) std::cout << x; }
};

PrintHelper print_if(bool b) {
    return {b};
}

#endif
