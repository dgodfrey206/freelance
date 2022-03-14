#include <iostream>
#include <string>
#include <cstdlib>
#include <limits>
#include <locale>
#include <sstream>
#include <iomanip>
#include <cassert>

template<class T>
struct node {
  T data;
  node* next;
};

template<class T>
class LinkedList {
  node<T>* head = nullptr;

  node<T>* NewNode(T data) {
    node<T>* n = new node<T>;
    n->data = data;
    n->next = NULL;
    return n;
  }

  void Push(node<T>** head_ref, node<T>* newNode) {
    newNode->next = *head_ref;
    *head_ref = newNode;
  }

  void Display(node<T>* root) {
    while (root) {
      std::cout << root->data << '\n';
      root = root->next;
    }
    std::cout << '\n';
  }

  void Insert(node<T>* before, node<T>* newNode) {
    if (!before) {
      Push(&head, newNode);
    } else {
      newNode->next = before->next;
      before->next = newNode;
    }
  }

  template<class P>
  std::pair<node<T>*, node<T>*> Find(node<T>* root, P p) {
    node<T>* prev = nullptr;
    node<T>* cur = root;
    while (cur != nullptr && !p(cur->data)) {
      prev = cur;
      cur = cur->next;
    }
    return std::make_pair(prev, cur);
  }

  std::pair<node<T>*, node<T>*> Find(T key) {
    return Find(head, [&](T const& d){ return d == key; });
  }
public:
  void push(T key) {
    insertSorted(key);
  }

  void insertSorted(T key) {
    Insert(Find(head, [&](T const& t) {
      return !(t < key);
    }).first, NewNode(key));
  }

  void display() {
    Display(head);
  }

  node<T>* detach(T key) {
    auto p = Find(key);
    if (!p.second) {
      return nullptr;
    }
    if (!p.first) {
      head = head->next;
    } else {
      p.first->next = p.second->next;
    }
    p.second->next = nullptr;
    return p.second;
  }

  void erase(T key) {
    delete detach(key);
  }

  void refresh(T key) {
    if (!head) return;
    auto i = Find(head, [&](T const& d){ return !(d < key); });
    if (!i.second) {
      i.first->next = detach(key);
    }
    else if (key != i.second->data) {
      auto p = detach(key);
      p->next = i.second;
      if (!i.first) {
        head = p;
      } else {
        i.first->next = p;
      }
    }
  }

  node<T>* find(T key) {
    node<T>* cur = head;
    while (cur != nullptr && cur->data != key) {
      cur = cur->next;
    }
    return cur;
  }

  friend class AddressBook;
};

struct date {
private:
    std::tm tm = {};
    const char* fmt = "%D";
public:
    friend std::ostream& operator<<(std::ostream& os, date const& d) {
        return os << std::put_time(&d.tm, d.fmt) << '\n';
    }
    
    friend std::istream& operator>>(std::istream& is, date& d) {
        std::cout << "[mm/dd(/yyyy)]: ";
        std::string txt; std::getline(is >> std::ws, txt);
        std::stringstream ss(txt);
        if (!(ss >> std::get_time(&d.tm, "%m/%d/%Y"))) {
            ss.clear();
            ss.seekg(0);
            if (!(ss >> std::get_time(&d.tm, "%m/%d"))) {
                is.setstate(std::ios_base::failbit);
            }
            d.fmt = "%B %d";
        } else {
            d.fmt = "%B %d, %Y";
        }
        return is;
    }

    static std::tm get_current_time() {
      std::time_t x = std::time(0);
      std::tm p = *std::localtime(&x);
      p.tm_sec = 0; p.tm_min = 0; p.tm_hour = 0; p.tm_wday = 0;
      return p;
    }
    friend class AddressBook;
};

struct record {
  std::string name, address, city, state;
  date birthday, anniversary;
  int zip;
  record() = default;
  record(std::string const& n) : name(n) {}
  friend bool operator<(record const& r1, record const& r2) {
    return r1.name < r2.name;
  }
  friend bool operator==(record const& r1, record const& r2) {
    return !(r1 < r2) && !(r2 < r1);
  }
  friend bool operator!=(record const& r1, record const& r2) {
    return !(r1 == r2);
  }
  friend std::ostream& operator<<(std::ostream& os, record const& r) {
    return os << "Name: " << r.name << '\n'
              << "Address: " << r.address << '\n'
              << "Birthday: " << r.birthday << ""
              << "Anniversary: " << r.anniversary << ""
              << "City: " << r.city << '\n'
              << "State: " << r.state << '\n'
              << "Zip: " << r.zip << '\n';
  }
  friend std::istream& operator>>(std::istream& is, record& r) {
    std::cout << "Enter an address: ";
    assert(std::getline(is >> std::ws, r.address));
    std::cout << "Birthday:\n";
    assert(is >> r.birthday);
    std::cout << "Anniversary:\n";
    assert(is >> r.anniversary);
    std::cout << "City: ";
    assert(is >> r.city);
    std::cout << "State: ";
    assert(is >> r.state);
    std::cout << "Zip: ";
    assert(is >> r.zip);
    return is;
  }
};

class AddressBook {
  LinkedList<record> list;
public:
  void insertNewName() {
    std::cout << "Enter a full name: ";
    std::string name; std::cin >> name;
    record newRecord(name);
    if (list.find(newRecord) == nullptr) {
      std::cin >> newRecord;
      list.push(newRecord);
    } else {
      std::cout << "Name already in address book. Aborting.\n";
    }
  }

  void deleteName() {
    std::cout << "What name do you want to delete? ";
    std::string name; std::cin >> name;
    list.erase(record(name));
  }

  void update() {
    std::cout << "Whose record would you like to update? (Enter a full name) ";
    std::string name; std::cin >> name;
    node<record>* ptr = list.find(name);
    if (!ptr) {
      std::cout << "Name not found. Aborted.\n";
      return;
    }
    std::cout << "Change name or date? [n/d] ";
    char c; std::cin >> c;
    if (c == 'n') {
      std::cout << "Change to: ";
      std::cin >> ptr->data.name;
      list.refresh(ptr->data);
      std::cout << "Name successfully changed.\n";
    } else {
      std::cout << "Anniversary or birthday? [a/b] ";
      std::cin >> c;
      std::cin >> ((c == 'a') ? ptr->data.anniversary : ptr->data.birthday);
    }
  }

  void displayBirthdays() {
    std::tm t1 = date::get_current_time();
    std::tm t2 = t1; t2.tm_year = 0;
    node<record>* cur = list.head;
    while (cur) {
      std::time_t time1 = mktime(&cur->data.birthday.tm);
      if (difftime(mktime(&t1), time1) == 0 || difftime(mktime(&t2), time1) == 0) {
        std::cout << "Dear " << cur->data.name << ",\n\n";
        std::cout << "Have a great birthday.\n\nLove, Joanne\n";
      }
      cur = cur->next;
    }
    std::cin.get();
  }

  void displayAnniversaries() {
    std::tm t1 = date::get_current_time();
    std::tm t2 = t1; t2.tm_year = 0;
    node<record>* cur = list.head;
    while (cur) {
      std::time_t time1 = mktime(&cur->data.anniversary.tm);
      if (difftime(mktime(&t1), time1) == 0 || difftime(mktime(&t2), time1) == 0) {
        std::cout << "Dear " << cur->data.name << ",\n\n";
        std::cout << "Happy anniversary.\n\nLove, Joanne\n";
      }
      cur = cur->next;
    }
    std::cin.get();
  }

  void display() {
    list.display();
  }
};

int main() {
  AddressBook ab;
  while (true) {
    std::cout << "Please choose an option from the menu below.\n";
    std::cout << "1. Enter a new name.\n";
    std::cout << "2. Delete a name from the address book.\n";
    std::cout << "3. Change a name or date in the address book.\n";
    std::cout << "4. Display the whole address book.\n";
    std::cout << "5. Generate anniversary cards.\n";
    std::cout << "6. Display birthday cards.\n";
    std::cout << "7. Exit the card program.\n";
    int option; std::cin >> option;
    switch (option) {
    case 1:
      ab.insertNewName();
      break;
    case 2:
      ab.deleteName();
      break;
    case 3:
      ab.update();
      break;
    case 4:
      ab.display();
      break;
    case 5:
      ab.displayAnniversaries();
      break;
    case 6:
      ab.displayBirthdays();
      break;
    case 7:
      std::exit(0);
    }
    std::cout << '\n';
  }
}
