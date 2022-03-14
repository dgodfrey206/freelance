class circle {
public:
  double calcVal() const {
    return PI*radius*radius;
  }
protected:
  double radius = 1.0;
private:
  static const double PI;
};

const double circle::PI = 3.14;

class cylinder : public circle {
public:
  double calcVal() const {
    return length * circle::calcVal();
  }
protected:
  double length = 1;
};

int main() {
  
}
