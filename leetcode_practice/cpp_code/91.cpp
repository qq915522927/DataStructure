#include <iostream>
#include <string>
#include <vector>
#include <map>

#define ctoi(a) ((int)a - (int)'0')

using namespace std;

class Solution2 {

private:
map<string, int> mem;

int num_decoding(string s){
    if(s.size() == 0)
        return 0;
    if(s.size() == 1){
        if(stoi(s.substr(0, 1)) != 0)
            return 1;
        else
            return 0;
    }
    if(stoi(s.substr(0, 1)) == 0)
        return 0;

    if(mem.find(s) != mem.end()){
        return mem[s];
    }

    int num;
    num = num_decoding(s.substr(1, s.size() - 1));
    int first_two = stoi(s.substr(0, 2));
    if(first_two <= 26 && first_two > 0){
        if(s.size() == 2){
            num += 1;
        } else{
            num += num_decoding(s.substr(2, s.size() - 2));
        }
    }

    mem[s] = num;

    return num;
}
public:
    int numDecodings(string s) {
        return num_decoding(s);
    }
};

class Solution {
public:
    int numDecodings(string s) {
        if(s.size() == 0)
            return 0;

        if(ctoi(s[0]) == 0){
            return 0;
        }
        vector<int> mem(s.size(), -1);
        mem[0] = 1;
        if(s.size() >= 2){
            if(ctoi(s[1]) == 0){
                if(stoi(s.substr(0, 2))> 26){
                    return 0;
                }
                mem[1] = 1;
            } else{
                if(stoi(s.substr(0, 2)) <= 26){
                    mem[1] = 2;
                } else{
                    mem[1] = 1;
                }
            }
        }
        for(int i=2; i < s.size(); ++i){

            if(ctoi(s[i]) == 0){
                int two_num = stoi(s.substr(i-1, 2));
                if( two_num <= 26 && two_num > 0){
                    mem[i] = mem[i - 2];
                } else {
                    mem[i] = 0;
                }
            } else{
                mem[i] = mem[i - 1];
                int two_num = stoi(s.substr(i-1, 2));
                if( two_num <= 26 && ctoi(s[i-1]) != 0){
                    mem[i] += mem[i-2];
                }
            }
        }
        return mem.back();
    }
};


int main(int argc, char const *argv[])
{
    cout << Solution().numDecodings("100") << endl;
    return 0;
}
