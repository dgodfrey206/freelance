#ifndef DATETIME_H
#define DATETIME_H

#include <iostream>
#include <vector>
#include <tuple>
#include <ctime>
#include "helper.h"

class DateTime {
public:
    std::tm t = {};
    DateTime(int month, int day, int year) {
        t.tm_year = year-1900;
        t.tm_mon = month-1;
        t.tm_mday = day;
    }
    
    DateTime(std::string date) {
        strptime(date.c_str(), "%D", &t);
    }
    
    friend std::istream& operator>>(std::istream& is, DateTime& dt) {
        std::cout << std::setw(25) << std::left;
        InputWithRange(is, dt.t.tm_mon, 1, 12, "Enter a month [0-11]: ");
        std::cout << std::setw(25) << std::left;
        InputWithRange(is, dt.t.tm_mday, 1, 31, "Enter a day   [0-31]: ");
        std::cout << std::setw(25) << std::left;
        Input(is, dt.t.tm_year, "Enter a year: ");
        return is;
    }
    
    friend std::ostream& operator<<(std::ostream& os, DateTime const& dt) {
        return os << std::put_time(&dt.t, "%D") << std::endl;
    }
};

#endif
