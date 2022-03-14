#ifndef CS2CONTAINER_H_
#define CS2CONTAINER_H_

#include <algorithm>

template <class T, class U>
struct element {
  T key;
  U data;
};

template <class T, class U>
class CS2Container {
  element<T, U>* arr;
  int max_len;
  int sz = 0;
  int next = 0;

 public:
  CS2Container(int n) : arr(new element<T, U>[n]), max_len(n) {}

  ~CS2Container() { delete[] arr; }

  bool push_back(element<T, U> elem) {
    if (next == max_len) {
      return false;
    }
    arr[next++] = elem;
    sz = std::max(next, sz);
    return true;
  }

  bool retrieve_front(element<T, U>& e) const {
    if (size() == 0)
      return false;
    e = arr[0];
    return true;
  }

  bool retrieve_back(element<T, U>& e) const {
    if (size() == 0)
      return false;
    e = arr[sz - 1];
    return true;
  }

  bool retrieve_by_key(element<T, U>& e) {
    if (size() == 0)
      return false;
    for (int i = 0; i < size(); i++) {
      if (arr[i].key == e.key) {
        e.data = arr[i].data;
        next = i + 1;
        return true;
      }
    }
    return false;
  }

  int size() const { return sz; }

  int max_size() const { return max_len; }

  bool start() {
    if (size() == 0)
      return false;
    next = 0;
    return true;
  }

  bool get_next(element<T, U>& e) {
    if (next == size()) {
      return false;
    }
    e = arr[next++];
    return true;
  }
};



#endif /* CS2CONTAINER_H_ */
