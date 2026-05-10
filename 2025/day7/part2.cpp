#include <algorithm>
#include <fstream>
#include <iostream>
#include <vector>

using namespace std;

typedef pair<unsigned char, unsigned char> coords;

void printManifold(vector<vector<char>>& manifold) {
    for (size_t i = 0; i < manifold.size(); i++) {
        if (i < 10) {
            cout << '0';
        }
        cout << i;
        for (size_t j = 0; j < manifold[j].size(); j++) {
            cout << manifold[i][j];
        }
        cout << "\n";
    }
}

template <typename T, typename Val>
typename vector<pair<T, Val>>::iterator vectorFindCoords(
    vector<pair<T, Val>>& vect, T searched) {
    for (auto it = vect.begin(); it != vect.end(); it++) {
        if (it->first == searched) {
            return it;
        }
    }
    return vect.end();
}

template <typename T, typename Val>
bool vectorContainsCoords(vector<pair<T, Val>>& vect, T searched) {
    return vectorFindCoords(vect, searched) != vect.end();
}

int main() {
    ifstream file("D:\\programming\\advent-of-code\\2025\\day7\\data.txt");
    coords start;
    vector<vector<char>> manifold;
    for (size_t i = 0; !file.eof(); i++) {
        manifold.push_back(vector<char>());
        for (int currentChar, j = 0; currentChar = file.get(); j++) {
            if (file.eof() || currentChar == '\n') break;
            manifold[i].push_back(currentChar);
            if ((i == 0) && currentChar == 'S') {
                start = coords(i, j);
            }
        }
    }
    file.close();
    // printManifold(manifold);
    unsigned long long result = 0;

    cout << "Map loaded, Calculating... \n";
    vector<pair<coords, unsigned long long>> activeBeams;
    activeBeams.push_back({start, 1});
    while (activeBeams.size() > 0) {
        size_t N = activeBeams.size();
         for (size_t j = 0; j < N; j++) {
            if (activeBeams[j].first.first + 1 >= manifold.size()) {
                result += activeBeams[j].second;
                activeBeams.erase(find(activeBeams.begin(), activeBeams.end(), activeBeams[j]));
                j--;
                N--;
                break;
            }
            if (manifold[activeBeams[j].first.first + 1][activeBeams[j].first.second] == '^') {
                bool toDeletetion = true;
                if (vectorContainsCoords(activeBeams, coords(activeBeams[j].first.first + 1, activeBeams[j].first.second - 1))) {
                    auto leftBeamIt = vectorFindCoords(activeBeams, pair(coords(activeBeams[j].first.first + 1, activeBeams[j].first.second - 1)));
                    leftBeamIt->second += activeBeams[j].second;
                    if (vectorContainsCoords(activeBeams, coords(activeBeams[j].first.first + 1, activeBeams[j].first.second + 1))) {
                        auto leftBeamIt = vectorFindCoords(activeBeams, pair(coords(activeBeams[j].first.first + 1, activeBeams[j].first.second + 1)));
                        leftBeamIt->second += activeBeams[j].second;
                    } else {
                        activeBeams[j].first.first++;
                        activeBeams[j].first.second++;
                        toDeletetion = false;
                    }
                } else {
                    if (vectorContainsCoords(activeBeams, coords(activeBeams[j].first.first + 1, activeBeams[j].first.second + 1))) {
                        auto leftBeamIt = vectorFindCoords(activeBeams, pair(coords(activeBeams[j].first.first + 1, activeBeams[j].first.second + 1)));
                        leftBeamIt->second += activeBeams[j].second;
                    } else {
                        activeBeams.emplace_back(coords(activeBeams[j].first.first + 1, activeBeams[j].first.second + 1), activeBeams[j].second);
                    }
                    activeBeams[j].first.first++;
                    activeBeams[j].first.second--;
                    toDeletetion = false;
                }
                
                if (toDeletetion) {
                    activeBeams.erase(find(activeBeams.begin(), activeBeams.end(), activeBeams[j]));
                    j--;
                    N--;
                }
            } else {
                if (vectorContainsCoords(activeBeams, coords(activeBeams[j].first.first + 1, activeBeams[j].first.second))) {
                    auto leftBeamIt = vectorFindCoords(activeBeams, pair(coords(activeBeams[j].first.first + 1, activeBeams[j].first.second)));
                    leftBeamIt->second += activeBeams[j].second;
                    activeBeams.erase(find(activeBeams.begin(), activeBeams.end(), activeBeams[j]));
                    j--;
                    N--;
                } else {
                    activeBeams[j].first.first++;
                }
            }
            // for (auto it = activeBeams.begin(); it != activeBeams.end(); it++) {
            //     manifold[it->first.first][it->first.second] = it->second+'0';
            // }
        }
        // printManifold(manifold);
    }
    // printManifold(manifold);

    cout << "Number of quantum tachyom beam posibilities: " << result << endl;
    return 0;
}