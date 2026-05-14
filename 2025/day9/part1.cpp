#include <iostream>
#include <vector>
#include <fstream>
#include <math.h>

using namespace std;

int main() {
    ifstream file("data.txt");
    vector<pair<long, long>> points;
    while (true) {
        int x, y;
        file >> x;
        file.get();
        file >> y;
        points.emplace_back(x, y);
        if (file.peek() == EOF) {
            break;
        }
    }
    long long result = 0;
    for (auto point1 : points) {
        for (auto point2 : points) {
            long long area = (abs(point1.first - point2.first + 1) * abs(point2.second - point1.second + 1));
            if (area > result) {
                result = area;
            }
        }
    }
    cout << "Biggest rectangle area: " << result << endl;
    return 0;
}