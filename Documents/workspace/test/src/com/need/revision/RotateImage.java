package com.need.revision;
/*
 * https://leetcode.com/problems/rotate-image/
 * https://leetcode.com/problems/rotate-image/discuss/18872/A-common-method-to-rotate-the-image
 * here give a common method to solve the image rotation problems.

*
 * clockwise rotate
 * first reverse up to down, then swap the symmetry 
 * 1 2 3     7 8 9     7 4 1
 * 4 5 6  => 4 5 6  => 8 5 2
 * 7 8 9     1 2 3     9 6 3
*
void rotate(vector<vector<int> > &matrix) {
    reverse(matrix.begin(), matrix.end());
    for (int i = 0; i < matrix.size(); ++i) {
        for (int j = i + 1; j < matrix[i].size(); ++j)
            swap(matrix[i][j], matrix[j][i]);
    }
}

*
 * anticlockwise rotate
 * first reverse left to right, then swap the symmetry
 * 1 2 3     3 2 1     3 6 9
 * 4 5 6  => 6 5 4  => 2 5 8
 * 7 8 9     9 8 7     1 4 7
*
void anti_rotate(vector<vector<int> > &matrix) {
    for (auto vi : matrix) reverse(vi.begin(), vi.end());
    for (int i = 0; i < matrix.size(); ++i) {
        for (int j = i + 1; j < matrix[i].size(); ++j)
            swap(matrix[i][j], matrix[j][i]);
    }
}
 * 
 * The idea was firstly transpose the matrix and then flip it symmetrically. For instance,

1  2  3             
4  5  6
7  8  9
after transpose, it will be swap(matrix[i][j], matrix[j][i])

1  4  7
2  5  8
3  6  9
Then flip the matrix horizontally. (swap(matrix[i][j], matrix[i][matrix.length-1-j])

7  4  1
8  5  2
9  6  3
 * */
class RotateImage {
    public void rotate(int[][] matrix) {
    int len = matrix.length;    
    for (int i = 0; i < len / 2 + len % 2; i++) {
      for (int j = i; j < len - 1 - i; j++) {
        int top = matrix[i][j];
        int right = matrix[j][len - 1 - i];
        int bottom = matrix[len - 1 - i][len - 1 - j];
        int left = matrix[len - 1 - j][i];

        // rotate
        matrix[i][j] = left;
        matrix[j][len - 1 - i] = top;
        matrix[len - 1 - i][len - 1 - j] = right;
        matrix[len - 1 - j][i] = bottom;
      }
    }
        
        /*
        for(int i=0; i<matrix.length; i++){
            for(int j=i+1; j<matrix[i].length; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length/2; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length-1-j];
                matrix[i][matrix[0].length-1-j] = tmp;
            }
        }
        */
    }
}
