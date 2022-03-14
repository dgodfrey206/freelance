#include <algorithm>
#include <climits>
#include <iostream>
#include <list>
#include <map>
#include <queue>
#include <utility>
#include <fstream>
using namespace std;

template <class T, class Comp = std::less<T>> class Heap {
public:
  Heap() = default;
  Heap(size_t s) { a.reserve(s); }
  void push(T const &);
  int size() const;
  T pop();
  void heapifyUp(int);
  void heapifyDown(int);
  T top() {
    return a[0];
  }
  int Left(int i) const { return i * 2 + 1; }
  int Right(int i) const { return i * 2 + 2; }
  int Parent(int i) const { return (i - 1) / 2; }
  bool remove(int n);
  bool empty() const { return a.empty(); }

private:
  vector<T> a; // heap structure
};

template <class T, class Comp> void Heap<T, Comp>::heapifyUp(int i) {
  int smallest = i;
  while (Parent(smallest) >= 0 && Comp{}(a[smallest], a[Parent(smallest)])) {
    swap(a[smallest], a[Parent(smallest)]);
    smallest = Parent(smallest);
  }
}

template <class T, class Comp> void Heap<T, Comp>::heapifyDown(int i) {
  int largest = i;
  int left = Left(i);
  int right = Right(i);

  if (left < size() && Comp{}(a[left], a[largest]))
    largest = left;
  if (right < size() && Comp{}(a[right], a[largest]))
    largest = right;
  if (largest != i) {
    swap(a[i], a[largest]);
    heapifyDown(largest);
  }
}

template <class T, class Comp> void Heap<T, Comp>::push(T const &n) {
  a.push_back(n);
  heapifyUp(size()-1);
}

template <class T, class Comp> T Heap<T, Comp>::pop() {
  T old = top();
  swap(a[0], a.back());
  a.pop_back();
  heapifyDown(0);
  return old;
}

template <class T, class Comp> int Heap<T, Comp>::size() const {
  return a.size();
}

template <class T, class Comp> bool Heap<T, Comp>::remove(int n) {
  auto it = std::find(a.begin(), a.end(), n);
  if (it == a.end()) {
    return false;
  }

  int k = distance(a.begin(), it);
  swap( a[k], a.back() );
  a.pop_back();
  
  int parent = Parent(k);
  if (Comp{}(a[k], a[parent])) {
      heapifyUp(k);
  } else {
      heapifyDown(k);
  }
  return true;
}

int main() {
  Heap<int> heap;
  int n;
  std::ifstream ifs("QHeap_input");
  cin.rdbuf(ifs.basic_ios<char>::rdbuf());
  cin >> n;
  while (n--) {
    int x;
    cin >> x;
    if (x == 1) {
      int k;
      cin >> k;
      heap.push(k);
    } else if (x == 2) {
      int k;
      cin >> k;
      heap.remove(k);
    } else {
      cout << heap.top() << endl;
    }
  }
}
