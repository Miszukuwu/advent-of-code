#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main() {
    long result = 0;
    vector<string> grid;
    ifstream read("data.txt");
    string line;
    while (getline(read, line)) {
        grid.push_back(line);
    }
    int N = grid.size();
    int M = grid[0].length();
    int removedPaper;
    do {
        removedPaper = false;
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {
                if (grid[row][column] != '@') continue;

                int amountOfRolls = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2 && amountOfRolls < 4; j++) {
                        if (row + i < 0 || row + i > N - 1 || column + j < 0 ||
                            column + j > M - 1 || (i == 0 && j == 0))
                            continue;
                        if (grid[row + i][column + j] == '@') {
                            amountOfRolls++;
                        }
                    }
                }
                if (amountOfRolls < 4) {
                    result++;
                    grid[row][column] = 'x';
                    removedPaper = true;
                }
            }
        }
    } while (removedPaper);
    cout << "Accessable rolls of paper: " << result << endl;
    return 0;
}
