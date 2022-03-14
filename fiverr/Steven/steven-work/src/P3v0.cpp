/*
Program Name:  COMSC 101 Programming Assignment #3
File Name:     2195-CP1-Prog02-v00.cpp	
Programmer:    S. Chang
Date Written:  06-18-2019
Date Compiled: 06-18-2019

Purpose:
This program tests the concepts of:
  Arrays
  Sorting

1. Problem:
Change Program 2 so that the first passes set of 1000 line segments and circles are saved in an array. The first percentage of line segments in circles should be for the random collection created. A second percentage should be created after sorting the line segments and circles based on their distance from the origin.

2. Analysis:

Constants:
Identifier      Type         Default   Min       Max       Description
--------------  --------     --------  --------  --------  ---------------

Variables:
Identifier      Type         Default   Min       Max       Description
--------------  --------     --------  --------  --------  ---------------
  lines           int[]                                       Array of lines
  circles         int[]                                       Array of circles

Objects:
Identifier       Class            Description
---------------  ---------------  -------------------------------------
NA

Structures:
Identifier       Description
---------------  ------------------------------------------------------
  Line              Abstract line segment data structure
  Circle            Abstract Circle data structure

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
  void generateLineAndCircle( Line* lines, Circle* circles, int i )
  double getSlope(int x0, int y0, int x1, int y1)
  int getYIntercept(Line line)
  void sortByDistance(Line* line)
  int distanceFromOrigin( Line line ) 

Output Layout: 
  Random Collection : x% of the lines were contained in the circles.
  Sorted Collection: y% of the lines were contained in the circles.


3. Design (the algorithm):
   1. Run iteration 1000 times on random lines/circles
   2. Calculate percentage of line segments in circles
   3. Sort the lines/circles by distance from origin
   4. Calculate percentage of line segments in circles.
   5. END PROGRAM

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
#include <vector>
#include <utility>
#include <algorithm>

struct Line { int p0x, p0y, p1x, p1y; Line() {} Line(int x0, int y0, int x1, int y1) : p0x(x0), p0y(y0), p1x(x1), p1y(y1) {} };
struct Circle { int mpx, mpy, radius; Circle() {} Circle(int mpx, int mpy, int radius) : mpx(mpx), mpy(mpy), radius(radius) {} };

int rand_between(int min, int max) {
  return rand() % (max + 1 - min) + min;
}

double lengthOfLine(int x1, int y1, int x2, int y2) {
  return sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
}

bool circleInLine(const int p0x, const int p0y, const int p1x, const int p1y, const int mpx, const int mpy, const int radius) {
  double mp2p0 = lengthOfLine(p0x, p0y, mpx, mpy);
  double mp2p1 = lengthOfLine(p1x, p1y, mpx, mpy);
  return mp2p0 < radius && mp2p1 < radius;
}

void generateLineAndCircle( Line* lines, Circle* circles, int i ) {
  int p0x = rand_between(-99, 99);
  int p0y = rand_between(-99, 99);
  int p1x = rand_between(-99, 99);
  int p1y = rand_between(-99, 99);
  int mpx = rand_between(-99, 99);
  int mpy = rand_between(-99, 99);
  int radius = rand_between(1, 200);

  lines[i] = Line(p0x, p0y, p1x, p1y);
  circles[i] = Circle(mpx, mpy, radius);
}

int percentLinesInCircles( Line* lines, Circle* circles ) {
  int count = 0;
  for (int i=0; i<1000; i++) {
    Circle circle = circles[i];
    Line line = lines[i];
    if (circleInLine(line.p0x, line.p0y, line.p1x, line.p1y, circle.mpx, circle.mpy, circle.radius)) {
      count++;
    }
  }
  return count/10;
}


double getSlope(int x0, int y0, int x1, int y1) {
  return (y1 - y0)/double(x1 - x0);
}

int getSlope(Line line) {
  return getSlope(line.p0x, line.p0y, line.p1x, line.p1y);
}

int getYIntercept(Line line) {
  int m = getSlope(line); // y=mx+b, b =y-mx
  return line.p0y - m * line.p0x;
}

int distanceFromOrigin( Line line ) {
  double m = 1+getSlope(line);
  
  int b = line.p0y - m*line.p0x;

  return sqrt(line.p0x * line.p0x + (line.p1x - b) * (line.p1x - b));
}

int distanceFromOrigin( Circle circle ) {
  return sqrt((circle.mpx * circle.mpx) + (circle.mpy * circle.mpy));
}

void sortByDistance(Line* line) {
  for (int i=0; i<1000; i++) {
    for (int j=i+1; j<1000; j++) {
      if (distanceFromOrigin(line[i]) > distanceFromOrigin(line[j])) {
        std::swap(line[i], line[j]);
      }
    }
  }
}

void sortByDistance(Circle* circle) {
  for (int i=0; i<1000; i++) {
    for (int j=i+1; j<1000; j++) {
      if (distanceFromOrigin(circle[i]) > distanceFromOrigin(circle[j])) {
        std::swap(circle[i], circle[j]);
      }
    }
  }
}

int main() {
  srand(unsigned(time(0)));
  Line lines[1000];
  Circle circles[1000];

  for (int i=0; i<1000; i++)
   generateLineAndCircle(lines, circles, i);

  int p = percentLinesInCircles(lines, circles);
  std::cout << "Random Collection : " << p << "% of the lines were contained in the circles.\n";
  sortByDistance(lines);
  sortByDistance(circles);
  p = percentLinesInCircles(lines, circles);
  std::cout << "Sorted Collection: " << p << "% of the lines were contained in the circles.\n";

  return EXIT_SUCCESS;
}

