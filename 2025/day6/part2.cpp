#include <iostream>
#include <fstream>
#include <vector>
#include <math.h>

using namespace std;

int main() {
    ifstream read("data.txt");
    vector<vector<char>> number_lines;
    vector<char> operations;
    int character;
    int current_line = 0;
    number_lines.push_back(vector<char>());
    while (read.good()) {
        character = read.get();
        number_lines[current_line].push_back(character);
        if (read.peek() == 10) {
            read.get();
            if (read.peek() == '*' || read.peek() == '+')
                break;
            current_line++;
            number_lines.push_back(vector<char>());
        }
    }
    char operation;
    while (read.good()) {
        operation = read.get();
        operations.push_back(operation);
    }
    unsigned long long result = 0;
    int column = number_lines[0].size()-1;
    for (int operation_index = operations.size()-2; operation_index >= 0; operation_index--) {
        operation = operations[operation_index];
        if (operation == ' ') {
            continue;
        }
        unsigned long long answer = 0;
        for (; column >= operation_index; column--) {
            vector<unsigned long long> v_number;
            int number = 0;
            for (int row = 0; row < number_lines.size(); row++) {
                if (number_lines[row][column] == ' '){
                    continue;
                }
                v_number.push_back(number_lines[row][column] - '0');
            }
            for (int i = 0; i < v_number.size(); i++) {
                number += pow(10, v_number.size() - i - 1) * v_number[i];
            }
            if (operation == '+') {
                answer += number;
            } else {
                if (answer == 0) {
                    answer = number;
                } else {
                    answer *= number;
                }
            }
        }
        result += answer;
        column--;
    }
    cout << "Result of all answers " << result << endl;
    return 0;
}