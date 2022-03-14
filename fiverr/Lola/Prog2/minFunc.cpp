#include <cassert>

template<class T>
T min(T a, T b) {
  return a < b ? a : b;
}

int main() {
  /*
    If a and b have the same value, a copy of the variable b is returned. Since a < b is false, the second expression of the conditional statement is evalutated which returns b.
  */
  assert(min(5, 5) == 5);
  assert(min(55, -24) == -24);
}
