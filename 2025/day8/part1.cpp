#include <math.h>

#include <algorithm>
#include <fstream>
#include <iostream>
#include <map>
#include <sstream>
#include <vector>

using namespace std;

struct Junction {
    int x, y, z;

    vector<Junction*>* circuit = NULL;

    Junction(int const& x, int const& y, int const& z) : x(x), y(y), z(z) {}

    bool operator==(Junction const& p2) {
        return (this->x == p2.x) && (this->y == p2.y) && (this->z == p2.z);
    }

    string toString() const {
        ostringstream oss;
        oss << this->x << "," << this->y << "," << this->z;
        return oss.str();
    }
};



double Get3dDistance(Junction const& p1, Junction const& p2) {
    return sqrt(pow(p2.x - p1.x, 2) + pow(p2.y - p1.y, 2) + pow(p2.z - p1.z, 2));
}

const int N = 10;

int main() {
    ifstream file("data.txt");
    if (!file.good()) {
        return -1;
    }

    vector<Junction> allJunctions;
    while (true) {
        int x, y, z;
        file >> x;
        file.get();
        file >> y;
        file.get();
        file >> z;
        allJunctions.emplace_back(x, y, z);
        if (file.eof()) break;
    }
    cout << allJunctions.size() << " junction boxes" << endl;

    map<double, pair<Junction&, Junction&>> connections;
    for (auto it1 = allJunctions.begin(); it1 != allJunctions.end(); it1++) {
        double flag = true;
        for (auto it2 = allJunctions.begin(); it2 != allJunctions.end(); it2++) {
            if (&(*it1) == &(*it2)) {
                continue;
            }
            double distance = Get3dDistance(*it1, *it2);
            connections.insert({ distance, {*it1, *it2} });
        }
    }
    cout << connections.size() << " connections" << endl;
    for (auto it = next(connections.begin(), 0); it != next(connections.begin(), 10); it++) {
        cout << it->first << " " << it->second.first.toString() << " " << it->second.second.toString() << endl;
    }

    vector<vector<Junction*>*> circuits;
    for (auto it = connections.begin(); it != next(connections.begin(), N); it++) {
        if (it->second.first.circuit == NULL && it->second.second.circuit == NULL) {
            vector<Junction*>* circuit = new vector<Junction*>;
            circuit->push_back(&(it->second.first));
            circuit->push_back(&(it->second.second));
            it->second.first.circuit = circuit;
            it->second.second.circuit = circuit;
            circuits.push_back(circuit);
        }
        else if (it->second.first.circuit == NULL) {
            it->second.second.circuit->push_back(&(it->second.first));
            it->second.first.circuit = it->second.second.circuit;
        }
        else if (it->second.second.circuit == NULL) {
            it->second.first.circuit->push_back(&(it->second.second));
            it->second.second.circuit = it->second.first.circuit;
        }
        else if (it->second.first.circuit != it->second.second.circuit) {
            it->second.first.circuit->insert(it->second.first.circuit->end(), it->second.second.circuit->begin(), it->second.second.circuit->end());
            circuits.erase(find(circuits.begin(), circuits.end(), it->second.second.circuit));
            for (Junction* junctionToChange : *it->second.first.circuit) {
                junctionToChange->circuit = it->second.first.circuit;
            }
        }
    }
    cout << circuits.size() << " circuits" << endl;

    sort(circuits.begin(), circuits.end(), [](vector<Junction*>* a, vector<Junction*>* b) {
        return a->size() > b->size();
        });
    long long result = circuits[0]->size();

    for (auto it = next(circuits.begin(), 1); it != next(circuits.begin(), 3); it++) {
        result *= (*it)->size();
    }
    cout << "Minimum cable length for 3 longest circuits: " << result << endl;
    return 0;
}