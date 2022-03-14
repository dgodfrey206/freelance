#ifndef COURSE_H
#define COURSE_H

#include "DateTime.h"
#include "Instructor.h"
#include "helper.h"
#include <iostream>
#include <iomanip>

struct Course {
    std::string course_name, class_name;
    Instructor instructor;
    
    Course(std::string course, std::string classname, std::string instructor)
        : course_name(course)
        , class_name(classname)
        , instructor{instructor}
    { }
    
    friend std::ostream& operator<<(std::ostream& os, Course const& c) {
        c.display(os, "Course: ", c.course_name).display(os, "Name: ", c.class_name).display(os, "Instructor: ", c.instructor.name);
        return os;
    }
    
    Course const& display(std::ostream& os, std::string const& str, std::string const& s) const {
        os << std::setw(12) << std::left << str << std::left << s << std::endl;
        return *this;
    }
    
    std::istream& get_course(std::istream& is, bool is_cin) {
        std::cout << std::setw(28) << std::left;
        print_if(is_cin)("Enter a course name: ");
        is >> course_name;
        std::cout << std::setw(28) << std::left;
        print_if(is_cin)("Enter a class name: ");
        is >> class_name;
        std::cout << std::setw(28) << std::left;
        print_if(is_cin)("Enter the instructor's name");
        is >> instructor.name;
        return is;
    }
    
    friend std::istream& operator>>(std::istream& is, Course& c) {
        return c.get_course(is, &is == &std::cin);
    }
};

#endif
