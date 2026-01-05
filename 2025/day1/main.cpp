#include <iostream>
#include <fstream>

using namespace std;

class Safe {
    private:
        int currentPosition = 50;
    public:
        void rotate(char direction, unsigned int amount){
            if (direction!='L' && direction!='R') {
                throw invalid_argument("Direction must be L or R");
            }
            // Full rotations dont do anything
            amount %= 100;
            if (direction == 'L') {
                currentPosition -= amount;
                if (currentPosition < 0){
                    currentPosition = 100 + currentPosition;
                }
                return;
            } 
            if (direction == 'R') {
                currentPosition += amount;
                if (currentPosition > 99) {
                    currentPosition -= 100;
                }
                return;
            }
        }
        int getCurrentPosition() {
            return currentPosition;
        }
};

int main() {
    ifstream read("data.txt");
    if (!read.is_open()){
        cout << "File read error"<<endl;
        return -1;
    }
    Safe safe;
    char direction;
    unsigned int amount;
    unsigned int password = 0;
    while (read >> direction >> amount) {
        cout << "Rotating: "<<direction << " " << amount << endl;
        safe.rotate(direction, amount);
        cout << "Current position: "<< safe.getCurrentPosition() << endl;
        if (safe.getCurrentPosition()==0) {
            password++;
        }
    }
    cout << "===== JACKPOT!! =====" << endl;
    cout << "Password is: " << password << endl;
    return 0;
}