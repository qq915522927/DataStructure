#include <vector>
#include <iostream>
using namespace std;

class Solution2 {
private:

    vector<vector<int> > data;

    vector<vector<int> > mem;
    vector<vector<int> > mem_is_set;
    int get_step_by_row_and_index(int row, int index){
        if(mem_is_set[row][index]){
            return mem[row][index];
        }
        if(row == 0){
            return data[0][0];
        }

        int min_step;
        if(index == data[row].size() - 1)
            min_step = get_step_by_row_and_index(row -1, index - 1);
        else if(index == 0)
            min_step = get_step_by_row_and_index(row -1, index);
        else
           min_step = min(get_step_by_row_and_index(row - 1, index), get_step_by_row_and_index(row -1, index - 1));

        mem[row][index] = min_step + data[row][index];
        mem_is_set[row][index] = 1;
        return mem[row][index];

    }
public:
    int minimumTotal(vector<vector<int> >& triangle) {
        data = triangle;

        for (int i = 0; i < data.size(); i++)
        {
            vector<int> row(i+1, 0);
            mem.push_back(row);

            vector<int> flags(i+1, 0);
            mem_is_set.push_back(flags);
        }

        int min;
        bool min_init = false;
        for (int i = 0; i < triangle.back().size(); i++)
        {
            cout << i << endl;
            int res = get_step_by_row_and_index(triangle.size() - 1, i);
            if(!min_init){
                min = res;
                min_init = true;
                continue;
            }
            if(res < min){
                min = res;
            }
        }
        return min;
    }
};

// 动态规划， 递推， 记忆化搜索的实现
class Solution {
private:

    vector<int> mem;
    int first_index_at_row(int row){
        return (1 + row - 1) * (row - 1) / 2;
    }
    int last_row_left_neibo(int row, int index){
        return first_index_at_row(row - 1) + index - 1;

    }
    int last_row_right_neibo(int row, int index){
        return first_index_at_row(row - 1) + index;

    }
public:
    int minimumTotal(vector<vector<int> >& triangle) {
        int n_row = triangle.size();
        mem = vector<int>((1 + n_row)* n_row / 2 , 0);

        mem[0] = triangle[0][0];
        for(int i=2; i <= n_row; ++i){
            vector<int> & cur_row_vec = triangle[i-1];
            for(int j=0;j < cur_row_vec.size(); ++j){
                int min_product;
                if(j == 0){
                    min_product = cur_row_vec[j] + mem[first_index_at_row(i - 1)];
                } else if (j == (triangle[i -1].size() - 1)){
                    min_product = cur_row_vec[j] + mem[first_index_at_row(i) - 1]; // the last index of last row
                } else {
                    min_product = cur_row_vec[j] + min(mem[last_row_left_neibo(i, j)], mem[last_row_right_neibo(i, j)]);
                }
                mem[first_index_at_row(i) + j] = min_product;
            }
        }
        int minimun_product = mem.back();
        for(int i=first_index_at_row(n_row); i < mem.size(); ++i){
            minimun_product = min(minimun_product, mem[i]);
        }
        return minimun_product;
    }
};


int main(int argc, char const *argv[])
{
    vector<vector<int> > data;

    vector<int> v1;
    vector<int> v2;
    vector<int> v3;
    vector<int> v4;

    data.push_back(v1);
    data.push_back(v2);
    data.push_back(v3);
    data.push_back(v4);

    data[0].push_back(2);

    data[1].push_back(3);
    data[1].push_back(4);

    data[2].push_back(6);
    data[2].push_back(5);
    data[2].push_back(7);

    data[3].push_back(4);
    data[3].push_back(1);
    data[3].push_back(8);
    data[3].push_back(3);


    cout << Solution().minimumTotal(data) <<endl;
    return 0;
}
