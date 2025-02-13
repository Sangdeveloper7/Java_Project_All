#include <iostream>
#include <vector>
#include <limits>

using namespace std;

const int SIZE = 15;
vector<vector<char> > board(SIZE, vector<char>(SIZE, '.'));

void printBoard() {
    system("clear");  // 터미널 화면을 클리어 (리눅스용)
    cout << "   ";
    for (int i = 0; i < SIZE; i++) cout << i % 10 << " ";
    cout << endl;
    for (int i = 0; i < SIZE; i++) {
        cout << (i % 10) << " ";
        for (int j = 0; j < SIZE; j++) {
            cout << board[i][j] << " ";
        }
        cout << endl;
    }
}

// 게임이 끝나는지 확인하는 함수 돌을 놓았을 때 오목이 되는 경우 체크 
bool checkWin(int x, int y, char player) {
    int dx[] = {1, 0, 1, 1};
    int dy[] = {0, 1, 1, -1};
    
    for (int d = 0; d < 4; d++) {
        int count = 1;
        for (int i = 1; i < 5; i++) {
            int nx = x + dx[d] * i;
            int ny = y + dy[d] * i;
            if (nx < 0 || ny < 0 || nx >= SIZE || ny >= SIZE || board[nx][ny] != player) break;
            count++;
        }
        for (int i = 1; i < 5; i++) {
            int nx = x - dx[d] * i;
            int ny = y - dy[d] * i;
            if (nx < 0 || ny < 0 || nx >= SIZE || ny >= SIZE || board[nx][ny] != player) break;
            count++;
        }
        if (count >= 5) return true;
    }
    return false;
}

int main() {
    char players[2] = {'X', 'O'};
    int turn = 0;
    
    while (true) {
        printBoard();
        int x, y;
        cout << "Player " << players[turn] << "'s turn. Enter row and column: ";
        while (!(cin >> x >> y) || x < 0 || x >= SIZE || y < 0 || y >= SIZE || board[x][y] != '.') {
            cout << "Invalid input. Try again: ";
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }
        
        board[x][y] = players[turn];
        
        if (checkWin(x, y, players[turn])) {
            printBoard();
            cout << "Player " << players[turn] << " wins!" << endl;
            break;
        }
        
        turn = 1 - turn;
    }
    return 0;
}
