#include <iostream>
#include <vector>
#include "Homework.h"
#include "DateTime.h"
using namespace std;

void DisplaySoonToBeDue(std::vector<Homework>& homework, std::tm* t) {
    t->tm_mday += 3;
    std::cout << "Courses that are due within 3 days:\n";
    for (auto i=0ul, k = 1ul; i<homework.size(); i++) {
        int diff = difftime(std::mktime(t), std::mktime(&homework[i].dt.t));
        if (diff > 0) {
            diff = diff/86400;
            if (diff <= 3) {
                std::cout << k++ << std::endl;
                std::cout << homework[i] << std::endl;
            }
        }
    }
}

int main()
{
    std::vector<Homework> hws = {
        Homework{ Course{"ET639", "Economics", "Professor Saxon"}, "05/05/19", "l"},
        Homework{ Course{"CS100", "Algorithms", "Professor Mason"}, "05/06/19", "ip" },
        Homework{ Course{"JF345", "Zoology", "Professor Green"}, "05/07/19", "c" },
        Homework{ Course{"CS233", "Data Structures", "Professor Noom"}, "05/08/19", "l"}
    };
    
    std::tm t = {};
    int y = 2019, m = 5, d = 5;
    t.tm_year = y-1900;
    t.tm_mon  = m-1;
    t.tm_mday = d;
    std::mktime(&t);
    DisplaySoonToBeDue(hws, &t);
}
