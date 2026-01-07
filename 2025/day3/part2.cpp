#include <math.h>

#include <fstream>
#include <iostream>
#include <string>

using namespace std;

unsigned long long getBankJoltage(string& bank) {
    unsigned long long joltage = 0;
    size_t bankLength = bank.length();
    int added = 0;
    int addedBatteries[12];
    int firstIndex = -1;
    for (int i = 0; i < 12; i++) {
        char max = bank[bankLength - 12 + i];
        int maxIndex = bankLength - 12 + i;
        for (int j = bankLength - 12 + i; j > firstIndex; j--) {
            if (bank[j] >= max) {
                max = bank[j];
                maxIndex = j;
            }
        }
        firstIndex = maxIndex;
        addedBatteries[added] = maxIndex;
        added++;
    }

    
    for (int i = 0; i < 12; i++){
        joltage += (bank[addedBatteries[i]]-'0')*pow(10, 12-i-1);
    }
    
    cout << joltage << endl;
    return joltage;
}

int main() {
    unsigned long long totalOutput = 0;
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