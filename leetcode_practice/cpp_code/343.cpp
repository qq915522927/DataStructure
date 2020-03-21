#include <vector>
#include <iostream>

using namespace std;

class Solution2 {
private:
    int max3(int a, int b, int c){
        return max(a, max(b, c));
    }
    vector<int> mem;
    // 定义为 至少 拆分成两个数的最大product
    int integer_breask(int n){
        if(n == 1){
            return 1;
        }
        if(mem[n] != 0){
            return mem[n];
        }
        int the_max = 0;
        for (int i = 1; i < n; i++)
        {
            the_max = max3(i * integer_breask(n - i), i * (n - i), the_max);
        }
        mem[n] = the_max;
        return the_max;

    }
public:
    int integerBreak(int n) {
        mem = vector<int>(n+1, 0);
        return integer_breask(n);
    }
};

class Solution {
private:
 vector<int> mem;
 int max3(int a, int b, int c){
     return max(a, max(b, c));
 }
public:
    int integerBreak(int n) {
        mem = vector<int>(n+1, 0);
        mem[1] = 1;
        for(int i=2; i<=n; ++i){
            int the_max = 0;
            for(int j=1; j<i; ++j){
                the_max = max3(j * mem[i-j], j * (i-j), the_max);
            }
            mem[i] = the_max;
        }
        return mem.back();
    }
};

int main(int argc, char const *argv[])
{
    cout << Solution().integerBreak(10) << endl;
    return 0;
}
