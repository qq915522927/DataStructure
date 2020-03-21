#include <iostream>
#include <vector>

using namespace std;

// 递归实现
class Solution2 {
private:
    vector<int> mem;
    int climb(int n){

        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        if (mem[n] == -1){
            mem[n] = climb(n - 1) + climb(n - 2);
        }


        return mem[n];

    }
public:
    int climbStairs(int n) {
        mem = vector<int>(n + 1, -1);
        return climb(n);
    }
};

class Solution {
public:
    int climbStairs(int n) {
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int a, b;
        int temp;
        a = 1;
        b = 2;

        for (int i = 3; i <= n; i++)
        {
            cout << "A: " << a << " B: " << b <<endl; 
            temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }

};

int main(int argc, char const *argv[])
{
    cout << Solution().climbStairs(40)<< endl;
    return 0;
}
