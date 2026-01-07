#include <fstream>
#include <iostream>
#include <string>

using namespace std;

bool isIdValid(const string& id) {
    int idLength = id.length();
    bool flag = true;

    string pattern(1, id[0]);
    string buffer = "";

    int j = 0;
    for (int i = 1; i < idLength; i++) {
        if (id[i] == pattern[j]) {
            j++;
            if (j >= pattern.length()) {
                j = 0;
            }
            flag = false;
            buffer += id[i];
        } else {
            if (buffer.length() > 0)
            {
                pattern += buffer;
                i--;
            }
            else
                pattern += id[i];
            j = 0;
            buffer = "";
            flag = true;
        }
    }
    return flag || j != 0;
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
                cout << "Invalid id: " << id << "\n";
                sum += id;
            }
        }
    }
    cout << "The sum of invalid ids is: " << sum << endl;
    return 0;
}