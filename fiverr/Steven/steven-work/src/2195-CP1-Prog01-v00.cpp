/*
Program Name:  COMSC 101 Programming Assignment #2
File Name:     2195-CP1-Prog02-v00.cpp	
Programmer:    S. Chang
Date Written:  06-18-2019
Date Compiled: 06-18-2019

Purpose:
This program tests the concepts of:
  The if...else decision statement
  The for and while loop
  Function Declaration, Definition and Calling
  Counters

SDM:

1. Problem:
Using program 1 as a template, repeatedly assign the line segments and circle random values (same ranges as program 1) 1000 times.

Count how many of the 1000 randomly assigned lines are completely in the corresponding circle. Calculate the percentage of lines that were in circles.

Have the program recreate and retest the 1000 lines and circles until the percentage from subsequent sets is greater than the percentage from the first set.

Use functions to return random Integers in a range, line segment length and a Boolean test if a line segment in a circle.

The program should display only the percentage message shown below in the Output Layout section.

2. Analysis:

Constants:
Identifier      Type         Default   Min       Max       Description
--------------  --------     --------  --------  --------  ---------------

Variables:
Identifier      Type         Default   Min       Max       Description
--------------  --------     --------  --------  --------  ---------------
  p0x             int                   -99        99         x-coordinate
  p0y             int                   -99        99         y-coordinate
  p1x             int                   -99        99         x-coordinate
  p1y             int                   -99        99         y-coordinate
  mpx             int                   -99        99         x-coordinate
  mpy             int                   -99        99         y-coordinate
  radius          int                    1         200        radius of circle

Objects:
Identifier       Class            Description
---------------  ---------------  -------------------------------------
NA

Structures:
Identifier       Description
---------------  ------------------------------------------------------
NA

Classes:
Identifier       Description
---------------  ------------------------------------------------------
NA

Data Structures:
Identifier       Description
---------------  ------------------------------------------------------
NA

Equations: 
  sqrt(pow(p0x - mpx, 2.0) + pow(p0y - mpy, 2.0))

User Defined Function:
  rand_between(int min, int max)
  double lengthOfLine(int x1, int y1, int x2, int y2)
  bool circleInLine(const int p0x, const int p0y, const int p1x, const int p1y, const int mpx, const int mpy, const int radius)

Output Layout: 
  Pass N: x% of the lines were contained in the circles.

3. Design (the algorithm):
   1. Run an iteration that generates percentage of lines within circle using the formulas
   2. Continue iterations until we find a percentage greater than the first.
   3. END PROGRAM

4. Code Implementation: (See code below this block comment.)

5. Testing (done before coding!):

Bench Test Data:
Output Expected:
Output Received:
*/

#include <cmath>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <string>

int rand_between(int min, int max) {
  return std::rand() % (max + 1 - min) + min;
}

double lengthOfLine(int x1, int y1, int x2, int y2) {
  return sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
}

bool circleInLine(const int p0x, const int p0y, const int p1x, const int p1y, const int mpx, const int mpy, const int radius) {
  double mp2p0 = lengthOfLine(p0x, p0y, mpx, mpy);
  double mp2p1 = lengthOfLine(p1x, p1y, mpx, mpy);
  return mp2p0 < radius && mp2p1 < radius;
}

void generate( int& win ) {
  int p0x = rand_between(-99, 99);
  int p0y = rand_between(-99, 99);
  int p1x = rand_between(-99, 99);
  int p1y = rand_between(-99, 99);
  int mpx = rand_between(-99, 99);
  int mpy = rand_between(-99, 99);
  int radius = rand_between(1, 200);

  if (circleInLine(p0x, p0y, p1x, p1y, mpx, mpy, radius)) {
    win++;
  }
}

int main() {
  std::srand(unsigned(std::time(0)));
  int lastPercent = -1;
  for (int i=1, count = 0; count < 2; i++) {
    int pointsInsideCircle = 0;
    for (int i=0; i<1000; i++) {
     generate(pointsInsideCircle);
    }
    int p = pointsInsideCircle/10.0;
    if (p > lastPercent) {
      lastPercent = p;
      count++;
    }
    std::cout << "Pass " << i << ": " << p << "% of the lines were contained in the circles.\n";
  }
  return EXIT_SUCCESS;
}

