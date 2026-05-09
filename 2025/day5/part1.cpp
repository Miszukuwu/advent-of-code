#include <fstream>
#include <iostream>
#include <list>
#include <string>

#define ull unsigned long long

using namespace std;

int main() {
    ifstream read("data.txt");

    list<pair<ull, ull>> freshRanges;
    list<ull> ingredients;
    ull start, end, ingredient;

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

    while (read >> ingredient) {
        ingredients.push_back(ingredient);
    }

    ull result = 0;

    for (ull id : ingredients) {
        for (pair<ull, ull> range : freshRanges) {
            if (id >= range.first && id <= range.second) {
                result++;
                break;
            }
        }
    }
    cout << "Available fresh ingredients ids: " << result << "\n";
    return 0;
}