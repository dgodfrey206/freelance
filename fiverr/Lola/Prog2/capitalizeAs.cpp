#include <string>
#include <fstream>

// Assuming space separated words

int main() {
  std::ifstream is("input.txt");
  std::ofstream os("output.txt");
  bool capitalizeNext = true;
  for (char c; is.get(c); ) {
    if (capitalizeNext && isalpha(c)) {
      c = toupper(c);
      capitalizeNext = false;
    } else if (c == ' ') {
      capitalizeNext = true;
    }
    os.put(c);
  }
}
