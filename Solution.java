import java.util.*;

// Connect Four is a two-player connection board game.
// The players choose a color and then take turns dropping colored tokens into a seven-column, six-row vertically suspended grid.
// The pieces fall straight down, occupying the lowest available space within the column.
// The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own tokens. - Wikipedia

// The input is a list of columns played successively by the two players.
// The output is to display the Connect Four grid associated when the game finishes.

// If a winning state is reached or if a wrong move is played, the game should stop even if there are still columns in the list.
// In that case, only the tokens that have been played should be displayed in the grid.

// The goal is to write code that is well-structured, rather than a solution that is too complex.
// Optimisation may be discussed during follow-up questions.

// ### Examples:
// Input (column full):
// [2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]

// Output:
// | | |X| | | | |
// | | |0| | | | |
// | | |X| | | | |
// | | |0| | | | |
// | | |X| | | | |
// | | |0| | | | |

// In this example, we start playing with the 0 token.
// The 7th item of the input is invalid because the column is already full.

// -----------------------------

// Input (victory in column):
// [0, 1, 0, 1, 0, 1, 2, 1, 0, 2, 4, 3]

// Output:
// | | | | | | | |
// | | | | | | | |
// | |X| | | | | |
// |0|X| | | | | |
// |0|X| | | | | |
// |0|X|0| | | | |

// In this example, we start playing with the 0 token.
// The 8th item of the input is a winning move, so all following moves are discarded.

// -----------------------------

// Input (victory in row):
// [1, 1, 2, 2, 3, 0, 4, 3]

// Output:
// | | | | | | | |
// | | | | | | | |
// | | | | | | | |
// | | | | | | | |
// | |X|X| | | | |
// |X|0|0|0|0| | |

// -----------------------------

// Input (victory in diagonal 1):
// [0,1,1,2,2,3,2,3,4,3,3,4,5,6]

// Output:

// | | | | | | | |
// | | | | | | | |
// | | | |0| | | |
// | | |0|X| | | |
// | |0|0|X| | | |
// |0|X|X|X|0| | |

// -----------------------------

// Input (victory in diagonal 2)
// [1,1,1,1,2,2,3,4,1,2,2,3]

// Output:

// | | | | | | | |
// | |0| | | | | |
// | |X|0| | | | |
// | |0|X| | | | |
// | |X|X|X| | | |
// | |0|0|0|X| | |


public class Solution {

    // 7 columsn, 6 rows
    private int[][] playGame(List<Integer> input) {
        if(input == null || input.size() == 0) return new int[6][7];
        int[][] board = new int[6][7];

        int[] index = new int[7]; // index
        int player = 1;

        for(int i : input) {
            if(index[i] == 6) return board;

            board[5 - index[i]][i] = player;
            index[i]++;
            if (checkWin(board, 5 - index[i] + 1, i)) return board;

            player = -player;
        }

        return board;
    }

    private boolean checkWin(int[][] board, int r, int c) {
        int player = board[r][c];
        int count;

        // Horizontal Check
        count = 1;
        for (int i = c - 1; i >= 0 && board[r][i] == player; i--) count++;
        for (int i = c + 1; i < 7 && board[r][i] == player; i++) count++;
        if (count >= 4) return true;

        // Vertical Check
        count = 1;
        for (int i = r + 1; i < 6 && board[i][c] == player; i++) count++;
        if (count >= 4) return true;

        // Diagonal (Top-left to Bottom-right)
        count = 1;
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0 && board[i][j] == player; i--, j--) count++;
        for (int i = r + 1, j = c + 1; i < 6 && j < 7 && board[i][j] == player; i++, j++) count++;
        if (count >= 4) return true;

        // Anti-diagonal (Bottom-left to Top-right)
        count = 1;
        for (int i = r + 1, j = c - 1; i < 6 && j >= 0 && board[i][j] == player; i++, j--) count++;
        for (int i = r - 1, j = c + 1; i >= 0 && j < 7 && board[i][j] == player; i--, j++) count++;
        if (count >= 4) return true;

        return false;
    }

    private void printBoard(int[][] res) {
        for(int i = 0; i < res.length; i++) {
            for(int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2);
        List<Integer> list2 = Arrays.asList(1,1,1,1,2,2,3,4,1,2,2,3);
        List<Integer> list3 = Arrays.asList(0,1,1,2,2,3,2,3,4,3,3,4,5,6);
        List<Integer> list4 = Arrays.asList(1, 1, 2, 2, 3, 0, 4, 3);
        Solution s = new Solution();

        s.printBoard(s.playGame(list1));
        s.printBoard(s.playGame(list2));
        s.printBoard(s.playGame(list3));
        s.printBoard(s.playGame(list4));
/*
Output :

0 0 -1 0 0 0 0 
0 0 1 0 0 0 0 
0 0 -1 0 0 0 0 
0 0 1 0 0 0 0 
0 0 -1 0 0 0 0 
0 0 1 0 0 0 0 
----------------------
0 0 0 0 0 0 0 
0 1 0 0 0 0 0 
0 -1 1 0 0 0 0 
0 1 -1 0 0 0 0 
0 -1 -1 -1 0 0 0 
0 1 1 1 -1 0 0 
----------------------
0 0 0 0 0 0 0 
0 0 0 0 0 0 0 
0 0 0 1 0 0 0 
0 0 1 -1 0 0 0 
0 1 1 -1 0 0 0 
1 -1 -1 -1 1 0 0 
----------------------
0 0 0 0 0 0 0 
0 0 0 0 0 0 0 
0 0 0 0 0 0 0 
0 0 0 0 0 0 0 
0 -1 -1 0 0 0 0 
-1 1 1 1 1 0 0 
----------------------

Process finished with exit code 0

*/
    }
}
