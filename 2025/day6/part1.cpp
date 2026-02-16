#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
    ifstream read("data.txt");
    vector<vector<int>> number_lines;
    vector<char> operations;
    int number;
    int current_line = 0;
    number_lines.push_back(vector<int>());
    while (read >> number) {
        number_lines[current_line].push_back(number);
        while (read.peek() == 32) {
            read.get();
        }
        if (read.peek() == 10) {
            read.get();
            if (read.peek() == '*' || read.peek() == '+')
                break;
            current_line++;
            number_lines.push_back(vector<int>());
            
        }
    }
    char operation;
    while (read >> operation) {
        operations.push_back(operation);
    }
    long long result = 0;
    for (int i = 0; i < operations.size(); i++) {
        long long answer = number_lines[0][i];
        for (int n = 1; n < number_lines.size(); n++) {
            if (operations[i] == '+') {
                answer += number_lines[n][i];
            } else {
                answer *= number_lines[n][i];
            }
        }
        result += answer;
    }
    cout << "Result of all answers " << result << endl;
    return 0;
}