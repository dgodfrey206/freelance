#include <iostream>
#include <string>
#include <cstring>
#include <sstream>
#include<cassert>
using namespace std;

class Ring {
public:
    virtual ~Ring() {}
    // a heap-allocated copy of this element
    virtual Ring* copy() const = 0;
    // the string representation of this element
    virtual std::string str() const = 0;
    // the constant of the type of this element and the inverse of this element
    virtual Ring* zero() const = 0;
    virtual Ring* operator-() const = 0; // unary operator
    // sum and product of this element and c
    virtual Ring* operator+(Ring* c) const = 0;
    virtual Ring* operator*(Ring* c) const = 0;
    // comparison function
    virtual bool operator==(Ring* c) const = 0;
};

//--------------------------------
// Implement the following classes
//--------------------------------
class Integer: public Ring {
  long n;
public:
    // integer with value n (default 0)
    Integer(long v = 0) : n(v) { }

    Ring* copy() const {
      return new Integer(*this);
    }

    std::string str() const {
      return std::to_string(n);
    }

    Ring* zero() const {
      return new Integer(0);
    }

    Ring* operator-() const {
      return new Integer(-n);
    }

    Ring* operator+(Ring* c) const {
      return new Integer(n + dynamic_cast<Integer*>(c)->n);
    }

    Ring* operator*(Ring* c) const {
      return new Integer(n * dynamic_cast<Integer*>(c)->n);
    }

    bool operator==(Ring* c) const {
      return n == dynamic_cast<Integer*>(c)->n;
    }
};

class Polynomial {
    int dim;
    Ring** coeffs;
    
    void assign(Ring*& a, Ring* b, bool f) {
        swap(a, b);
        if (f)
            delete b;
    }
public:
    // polynomial with n>=0 coefficients
    Polynomial(int n, Ring** cs) : dim(n), coeffs(new Ring*[n]) {
      for (int i = 0; i < dim; i++) {
        coeffs[i] = cs[i]->copy();
      }
    }
    // destructor
    virtual ~Polynomial() {
      for (int i = 0; i < dim; i++) {
        delete coeffs[i];
      }
      delete[] coeffs;
    }
    // evaluate this polynomial on c
    Ring* eval(Ring* c) {
      Ring* r = c->zero();
      for (int i = 0; i < dim; i++) {
        Ring* p = nullptr;
        for (int j = 0; j < i; j++) {
            if (!p)
                assign(p, c, false);
            else
                assign(p, *p * c, p != c);
        }
        
        if (p != nullptr) {
            Ring* mul = *coeffs[i] * p;
            assign(r, *r + mul, true);
            delete mul;
        }
        else
            assign(r, *r + coeffs[i], true);
      }
      return r;
    }
    // the string representation of the polynomial
    std::string str() const {
      std::ostringstream oss;
      oss << coeffs[dim-1]->str() << "x^" << dim-1;
      for (int i = dim - 2; i > 0; i--) {
        if (coeffs[i]->str() != "0")
          oss << "+" << coeffs[i]->str() << "x^" << i;
      }
      oss << "+" << coeffs[0]->str();
      return oss.str();
    }
};

int main() {
    Ring* ii[] = { new Integer(-5), new Integer(2), new Integer(0), new Integer(-3) };
    Polynomial* p = new Polynomial(4, ii);
    assert(p->str() == "-3x^3+2x^1+-5");
    
    {
        Polynomial* p = new Polynomial(4, ii);

        // evaluate polynomial
        Integer* val = new Integer(5);
        Integer* answer = new Integer(-370);
        Integer* result = dynamic_cast<Integer*>(p->eval(val));
        assert( (*result) == answer );
    }
}
