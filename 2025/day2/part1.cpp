#include <fstream>
#include <iostream>
#include <string>

using namespace std;

bool isIdValid(const string& id) {
        int halfLen = id.length() / 2;
    string firstPart = id.substr(0, halfLen);
    string secondPart = id.substr(halfLen, halfLen);

    return firstPart != secondPart;
}

int main() {
    ifstream read("data.txt");
    if (!read.is_open()) {
        cout << "read error" << endl;
        return -1;
    }

    long long firstId, lastId;
    string line;
    long long sum = 0;
    while (getline(read, line, ',')) {
        firstId = stoll(line.substr(0, line.find("-")));
        lastId = stoll(line.substr(line.find("-") + 1, line.length()));
        cout << "Range " << firstId << " " << lastId << endl;
        for (long long id = firstId; id <= lastId; id++) {
            if (!isIdValid(to_string(id))) {
                sum += id;
            }
        }
    }
    cout << "The sum of invalid ids is: " << sum << endl;
    return 0;
}