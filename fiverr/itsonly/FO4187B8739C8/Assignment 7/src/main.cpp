
#include <iostream>
#include <algorithm>
#include "CS2Container.h"
using namespace std;

int main(int argc, char* argv[]) {
  int num;
  if (argc == 2)
    num = std::stoi(argv[1]);
  else
    num = 5;  // replace this line of code with code to retrieve the number of
              // elements from the command line
  cout << "num=" << num << endl;
  CS2Container<string, string> C(num);
  element<string, string> temp, temp2;
  bool b;
  // number of elements coming from the command line will be 5
  // push_back three elements into C
  temp.key = "111";
  temp.data = "aaa";
  C.push_back(temp);
  temp.key = "222";
  temp.data = "bbb";
  C.push_back(temp);
  temp.key = "333";
  temp.data = "ccc";
  C.push_back(temp);
  // test size functions
  cout << "main: current size of container " << C.size() << endl;
  cout << "main: max size of container " << C.max_size() << endl;
  // test retrieve_front
  b = C.retrieve_front(temp2);
  cout << "main: retrieve_front gets " << temp2.key << " " << temp2.data
       << endl;
  // test start and get_next functions
  C.start();
  while (C.get_next(temp2)) {
    cout << "main: in get next loop #1, data retrieved ->" << temp2.key << " "
         << temp2.data << endl;
  }
  cout << "main: after get next loop #1" << endl;
  // try to push_back three more elements into C
  // the first two should work; the last one should fail
  temp.key = "444";
  temp.data = "ddd";
  C.push_back(temp);
  temp.key = "555";
  temp.data = "eee";
  C.push_back(temp);
  temp.key = "666";
  temp.data = "fff";
  b = C.push_back(temp);
  if (!b) {
    cout << "main: push_back successfully failed on push_back # 6" << endl;
  } else {
    cout << "main: push_back returned true on push_back # 6" << endl;
  }
  C.retrieve_front(temp2);
  cout << "main: after retrieve_front, got " << temp2.key << " " << temp2.data
       << endl;
  C.retrieve_back(temp2);
  cout << "main: after retrieve_back, got " << temp2.key << " " << temp2.data
       << endl;
  // dumpo the entire container again
  C.start();
  while (C.get_next(temp2)) {
    cout << "main: in get next loop #2, data retrieved ->" << temp2.key << " "
         << temp2.data << endl;
  }
  cout << "main: after get next loop #2" << endl;
  // test retrieve_by_key, search for key that exists
  temp2.key = "333";
  b = C.retrieve_by_key(temp2);
  if (b) {
    cout << "main: after retrieve_by_key for key 333, got " << temp2.key << " "
         << temp2.data << endl;
  } else {
    cout << "main: error, retrieve_by_key for key 333 failed" << endl;
  }
  // test retrieve_by_key, search for key that isn't there
  temp2.key = "123";
  b = C.retrieve_by_key(temp2);
  if (b) {
    cout << "main: after retrieve_by_key for nonexistent key, returned found"
         << endl;
  } else {
    cout << "main: success, retrieve_by_key for nonexistent key failed" << endl;
  }
  // test retrieve_by_key followed by get_next - should retrieve keys 444, then
  // 555
  temp2.key = "444";
  b = C.retrieve_by_key(temp2);
  C.get_next(temp2);
  cout << "main: after retrieve_by_key for key 444 followed by get_next, got "
       << temp2.key << " " << temp2.data << endl;
  b = C.get_next(temp2);
  if (b) {
    cout << "main: final get_next should have failed, but it didn't" << endl;
  } else {
    cout << "main: success, final get_next should have failed, and it did"
         << endl;
  }
  return 0;
}
