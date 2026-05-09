#include <fstream>
#include <iostream>
#include <list>
#include <string>
#include <set>

#define ull unsigned long long

using namespace std;

int main() {
    ifstream read("data.txt");

    list<pair<ull, ull>> freshRanges;
    set<ull> ingredients;
    ull start, end;

    string line;

    while (getline(read, line)) {
        if (line.empty()) {
            break;
        }

        start = stoull(line.substr(0, line.find('-')));
        end = stoull(line.substr(line.find('-') + 1, line.length()));
        pair<ull, ull> freshRange(start, end);
        freshRanges.push_back(freshRange);
    }


    for (pair<ull, ull> range : freshRanges) {
        for (ull i = range.first-1; i < range.second; i++){
            ingredients.insert(i);
        }
    }
    cout << "Available fresh ingredients ids: " << ingredients.size() << "\n";
    return 0;
}