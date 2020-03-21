#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

class Solution2 {
private:
    vector<int> mem;
    bool is_pefect_squares(int n){
        double res = sqrt(n);
        return res - floor(res) < 0.000001;
    }
    int num_squares(int n){
        if(mem[n] != -1){
            return mem[n];
        }
        if(is_pefect_squares(n)){
            mem[n] = 1;
            return 1;
        }
        int the_min = -1;
        for(int i=1; i*i < n; ++i){
            int another = n - i * i;
            if(the_min == -1){
                the_min = 1 + num_squares(another);
            } else{
                the_min = min(1 + num_squares(another), the_min);
            }
        }
        mem[n] = the_min;
        return the_min;
    }
public:
    int numSquares(int n) {
        mem = vector<int>(n+1, -1);
        return num_squares(n);
    }
};

class Solution {
public:
    vector<int> mem;
    bool is_pefect_squares(int n){
        double res = sqrt(n);
        return res - floor(res) < 0.000001;
    }
    int numSquares(int n) {
        if(is_pefect_squares(n))
            return 1;

        mem = vector<int> (n+1, -1);
        mem[1] = 1;
        for(int i=1; i <=n; ++i){
            if(is_pefect_squares(i)){
                mem[i] = 1;
                continue;
            }

            int the_min = -1;
            for(int j=1; j*j <= i; ++j){
                if(j*j == i){
                    the_min = 1;
                    continue;
                }
                int another = i - j*j;
                if(the_min == -1){
                    the_min = 1 + mem[another];
                } else{
                    the_min = min(the_min, 1 + mem[another]);
                }
            }
            mem[i] = the_min;
        }
        return mem.back();
    }
};

int main(int argc, char const *argv[])
{
    cout << Solution().numSquares(13) <<endl;
    return 0;
}
