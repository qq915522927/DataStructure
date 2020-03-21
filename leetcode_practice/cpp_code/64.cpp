#include <vector>
#include <iostream>

using namespace std;

class Solution2 {
private:

    vector<int> mem;

    int minPathSum(int x, int y, vector<vector<int> >& grid){
        // cout << "x: " << x << " y " << y <<endl;
        if(x ==0 && y == 0){
            return grid[0][0];
        }
        int cur_index= y * grid.back().size() + x;
        if(mem[cur_index] != -1){
            return mem[cur_index];
        }

        int last_up_x = x;
        int last_up_y = y - 1;
        int last_left_x = x - 1;
        int last_left_y = y;


        if(last_up_y >= 0 and last_left_x >= 0){

            mem[cur_index] = grid[y][x] + min(minPathSum(last_up_x, last_up_y, grid), minPathSum(last_left_x, last_left_y, grid));
            return mem[cur_index];
        }
        if(last_up_y < 0){

            mem[cur_index] = grid[y][x] + minPathSum(last_left_x, last_left_y, grid);
            return mem[cur_index];
        }
        if(last_left_x < 0){
            mem[cur_index] = grid[y][x] + minPathSum(last_up_x, last_up_y, grid);
            return mem[cur_index];

        }

        // should never reach here
        return 0;
    }
public:
    int minPathSum(vector<vector<int> >& grid) {
        mem = vector<int>(grid.size() * grid.back().size(), -1);
        return minPathSum(grid.back().size() - 1, grid.size()-1, grid);

    }
};

class Solution {
private:
int to_index(int x, int y, vector<vector<int> >&grid){
    return y * grid.back().size() + x;
}
public:
    vector<int> mem;
    int minPathSum(vector<vector<int>>& grid) {
        mem = vector<int>(grid.size() * grid.back().size(), -1);
        mem[0] = grid[0][0];
        for(int x=0; x < grid.back().size(); x++){
            for(int y=0; y < grid.size(); ++y){
                if(x==0 && y == 0){
                    continue;
                }
                int cur_index = y * grid.back().size() + x;
                int left_x = x - 1, left_y = y;
                int up_x = x, up_y = y - 1;
                if(left_x >= 0 && up_y >= 0){
                    mem[cur_index] = grid[y][x] + min(mem[to_index(left_x, left_y, grid)], mem[to_index(up_x, up_y, grid)]);
                } else{
                    if(left_x < 0){
                        mem[cur_index] = grid[y][x] + mem[to_index(up_x, up_y, grid)];
                    }  else{
                        // up_y < 0
                        mem[cur_index] = grid[y][x] + mem[to_index(left_x, left_y, grid)];

                    }

                }

            }
        }
        return mem.back();
    }
};

int main(int argc, char const *argv[])
{
    vector<vector<int> > grid = {
            {1,3,1}, 
            {1,5,1}, 
            {4,2,1} 
        };
    cout << Solution().minPathSum(grid) << endl;
    return 0;
}
