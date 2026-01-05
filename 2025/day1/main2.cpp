#include <fstream>
#include <iostream>

using namespace std;

class Safe {
   private:
    int currentPosition = 50;

   public:
    int rotate(char direction, unsigned int amount) {
        if (direction != 'L' && direction != 'R') {
            throw invalid_argument("Direction must be L or R");
        }
        unsigned int zerosPassed = amount / 100;
        bool wasZero = currentPosition == 0;
        // Remove full rotations
        amount %= 100;
        if (direction == 'L') {
            currentPosition -= amount;
            if (currentPosition < 0) {
                currentPosition = 100 + currentPosition;
                if (wasZero == false && currentPosition!=0) zerosPassed++;
            }
        } else if (direction == 'R') {
            currentPosition += amount;
            if (currentPosition > 99) {
                currentPosition -= 100;
                if (wasZero == false && currentPosition!=0) zerosPassed++;
            }
        }
        if (currentPosition==0) zerosPassed++;
        return zerosPassed;
    }
    int getCurrentPosition() { return currentPosition; }
};

int main() {
    ifstream read("data.txt");
    if (!read.is_open()) {
        cout << "File read error" << endl;
        return -1;
    }
    Safe safe;
    char direction;
    unsigned int amount;
    unsigned int password = 0;
    while (read >> direction >> amount) {
        cout << "Rotating: " << direction << " " << amount << endl;
        password += safe.rotate(direction, amount);
        cout << "Current rotation: " << safe.getCurrentPosition()
             << " and password: " << password << endl;
    }
    cout << "===== JACKPOT!! =====" << endl;
    cout << "Password is: " << password << endl;
    return 0;
}