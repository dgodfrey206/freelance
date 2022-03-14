#ifndef HOMEWORK_H
#define HOMEWORK_H

#include "DateTime.h"
#include "Course.h"
#include "helper.h"
#include <string>

struct Homework {
    Course course; // Course contains an Instructor
    DateTime dt; // due date
    std::string hwstatus;
    
    Homework(Course const& c, std::string date, std::string hwstatus) : course(c), dt(date), hwstatus(hwstatus) {}
    
    std::string convertToWord() const {
        return (hwstatus == "c") ? std::string("Completed") : (hwstatus == "l" ? "Late" : "In progress");
    }
    
    friend std::ostream& operator<<(std::ostream& os, Homework const& hw) {
        return os << hw.course << "Due: " << hw.dt << "Status: " << hw.convertToWord() << std::endl;
    }
    
    friend std::istream& operator>>(std::istream& is, Homework& hw) {
        if (!(is >> hw.course) || !(is >> hw.dt)) {
            return is;
        }
        bool is_cin = &is == &std::cin;
        if (is_cin) std::cout << std::setw(57) << std::left << "Status: [c == completed | l == late | ip == in progress: ";
        std::string& status = hw.hwstatus;
        while (is >> status && (status != "c" && status != "l" && status != "ip")) {
            if (!is_cin) {
                is.setstate(std::ios_base::failbit);
                return is;
            }
            std::cout << std::setw(11) << "Try again: ";
        }
        return is;
    }
};

#endif
