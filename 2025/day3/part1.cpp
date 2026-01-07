#include <fstream>
#include <iostream>
#include <string>

using namespace std;

int getBankJoltage(string bank) {
    char firstBattery, secondBattery;
    int bankLength = bank.length();
    char max = bank[0];
    int maxIndex = 0;
    for (int i = 1; i < bankLength; i++) {
        if (bank[i] > max) {
            max = bank[i];
            maxIndex = i;
        }
    }
    if (maxIndex == bank.length() - 1) {
        secondBattery = max;
        max = bank[0];
        maxIndex = 0;
        for (int i = 1; i < bankLength - 1; i++) {
            if (bank[i] > max) {
                max = bank[i];
                maxIndex = i;
            }
        }
        firstBattery = max;
    } else {
        firstBattery = max;
        max = bank[maxIndex + 1];
        maxIndex++;
        for (int i = maxIndex++; i < bankLength; i++) {
            if (bank[i] > max) {
                max = bank[i];
                maxIndex = i;
            }
        }
        secondBattery = max;
    }
    return (firstBattery - '0') * 10 + (secondBattery - '0');
}

int main() {
    int totalOutput = 0;
    char firstBattery, secondBattery;
    bool isSecondBatteryFound = false;

    ifstream read("data.txt");

    string bank;

    while (getline(read, bank)) {
        totalOutput += getBankJoltage(bank);
    }

    cout << "Total output joltage is " << totalOutput << endl;
    return 0;
}