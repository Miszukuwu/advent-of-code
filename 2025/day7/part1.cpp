#include <algorithm>
#include <fstream>
#include <iostream>
#include <vector>

#define coords pair<size_t, size_t>

using namespace std;

void printMap(vector<vector<char>>& map) {
    for (size_t i = 0; i < map.size(); i++) {
        if (i < 10) {
            cout << '0';
        }
        cout << i;
        for (size_t j = 0; j < map[j].size(); j++) {
            cout << map[i][j];
        }
        cout << "\n";
    }
}
template <typename T>
bool vectorContains(vector<T>& vect, T searched) {
    for (int i = 0; i < vect.size(); i++) {
        if (vect[i] == searched) {
            return true;
        }
    }
    return false;
}

int main() {
    ifstream file("D:\\programming\\advent-of-code\\2025\\day7\\data.txt");
    coords start;
    vector<vector<char>> map;
    for (size_t i = 0; !file.eof(); i++) {
        map.push_back(vector<char>());
        for (int currentChar, j = 0; currentChar = file.get(); j++) {
            if (file.eof() || currentChar == '\n') break;
            map[i].push_back(currentChar);
            if ((i == 0) && currentChar == 'S') {
                start = coords(i, j);
            }
        }
    }
    file.close();
    printMap(map);
    cout << "Map loaded, Calculating... \n";
    vector<coords> activeBeams{coords(start.first, start.second)};
    while (activeBeams.size() > 0) {
        size_t N = activeBeams.size();
        for (size_t j = 0; j < N; j++) {
            coords& currentBeam = activeBeams[j];
            if (currentBeam.first+1 >= map.size()) {
                activeBeams.erase(find(activeBeams.begin(), activeBeams.end(), currentBeam));
                break;
            }
            if (map[currentBeam.first + 1][currentBeam.second] == '^') {
                if (!vectorContains(activeBeams, coords(currentBeam.first + 1, currentBeam.second - 1))) {
                    if (!vectorContains(activeBeams, coords(currentBeam.first + 1, currentBeam.second + 1))) {
                        activeBeams.emplace_back(currentBeam.first + 1, currentBeam.second + 1);
                        map[activeBeams[j].first + 1][activeBeams[j].second + 1] = '|';
                    }
                    activeBeams[j].first++;
                    activeBeams[j].second--;
                    map[activeBeams[j].first][activeBeams[j].second] = '|';
                } else {
                    if (!vectorContains(activeBeams, coords(currentBeam.first + 1, currentBeam.second + 1))) {
                        activeBeams[j].first++;
                        activeBeams[j].second++;
                        map[activeBeams[j].first][activeBeams[j].second] = '|';
                    } else {
                        activeBeams.erase(find(activeBeams.begin(), activeBeams.end(), currentBeam));
                    }
                }
                
            } else {
                currentBeam.first++;
                map[activeBeams[j].first][activeBeams[j].second] = '|';
            }
        }
        for (size_t j = 0; j < activeBeams.size(); j++) {
            map[activeBeams[j].first][activeBeams[j].second] = '|';
        }
    }

    printMap(map);
    long long result = 0;
    for (size_t i = 0; i < map.size(); i++) {
        for (size_t j = 0; j < map[j].size(); j++) {
            if (map[i][j] == '^' && map[i-1][j] == '|')
                result++;
        }
    }
    cout << "Number of tachyom beam split: " << result << endl;
    return 0;
}
