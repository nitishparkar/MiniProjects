import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * A program that solves sudoku puzzles.
 *  The program uses 3 dimensional array where 3rd dimension holds 
 *  all the possible values at that position
 * 
 *  Input:
 *   enter 1 digit per line
 *   enter 0 for unknown number
 * 
 * @author Nitish Parkar
 * created ~2010
 * modified 24/10/2012
 */


class SuDoKu
{
    public static void main(String[] args) throws IOException
    {
        Solver s = new Solver();
        s.solve();
    }
}

class Solver
{
    private int[][][] matrix = new int[9][9][9];

    public void solve() throws IOException
    {
        int i, j, k;
        
        //initialize 3-d array
        // put 1 - 9 numbers in the 3rd dimension
        for (i = 0; i < 9; i++)
        {
            for (j = 0; j < 9; j++)
            {
                for (k = 0; k < 9; k++)
                {
                        matrix[i][j][k] = k + 1;
                }
            }
        }

        //accept input
        int number;
        System.out.println("Enter SuDoKu:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (i = 0; i < 9; i++)
        {
            for (j = 0; j < 9; j++)
            {
                number = Integer.parseInt(br.readLine().trim());
                if (number != 0)
                {
                    addNumberToMatrix(i, j, number);
                    //eliminatePossibilities(i, j, number);
                }
            }
        }
        
        System.out.println("\n\nPuzzle:");
        display();
        
        int limit = 10;
        //process
        while (limit-- > 0 && !solved()) {
            boolean op;
            for (i = 0; i < 9; i++) {
                for (j = 0; j < 9; j++) {
                    int add = 0, zeroCount = 0;
                    for (k = 0; k < 9; k++) {
                        if ((matrix[i][j][k]) != 0) {
                            if (singleInCell(i, j, k)) {
                                addNumberToMatrix(i, j, k+1);
                            }
                        }
                        
                        if ((matrix[i][j][k]) == 0) {
                            zeroCount++;
                        }
                        
                        add = add + matrix[i][j][k];
                        
                        if ((zeroCount == 8) && (add != 0)) {
                            eliminatePossibilities(i, j, add);
                        }
                    }
                }
            }
        }
        if (solved()) {
            System.out.println("\nSolution:");
            display();
        } else {
            System.out.println("Could not solve the puzzle, "
                    + "the best the program could do:");
            displayRaw();
        }
    }
    
    /**
     * Displays puzzle state
     * prints '?' if more than 1 values are possible at that position 
     */
    private void display()
    {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int zeroCount = 0, add = 0;
                for (int k = 0; k < 9; k++) {
                    if ((matrix[i][j][k]) == 0) {
                        zeroCount++;
                    } else {
                        add = add + matrix[i][j][k];
                    }
                }
                if (zeroCount == 8) {
                    // add = value of the only element in the 3rd dimension
                    System.out.print(add + " ");
                } else {
                    // there are more than 1 possible values
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Returns true matrix[row][column] is the only place where 
     * value k is valid/present
     */
    private boolean singleInCell(int row, int column, int k)
    {      
        int rowMin = row / 3 * 3;
        int rowMax = rowMin + 3;
        int colMin = column / 3 * 3;
        int colMax = colMin + 3;
        
        for (int a = rowMin; a < rowMax; a++) {
            for (int b = colMin; b < colMax; b++) {
                if (a == row && b == column) {
                    continue;
                }
                if (matrix[a][b][k] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void eliminatePossibilities(int row, int column, int number)
    {
        // eliminate number in columns
        for (int x = 0; x < 9; x++)
        {
            if (x == column) {
                continue;
            }
            matrix[row][x][number-1] = 0;
        }
        
        // eliminate number in rows
        for (int x = 0; x < 9; x++)
        {
            if (x == row) {
                continue;
            }
            matrix[x][column][number-1] = 0;
        }
        
        // eliminate number in the same cell
        int rowMin = row / 3 * 3;
        int rowMax = rowMin + 3;
        int colMin = column / 3 * 3;
        int colMax = colMin + 3;
        
        for (int a = rowMin; a < rowMax; a++) {
            for (int b = colMin; b < colMax; b++) {
                if (a != row && b != column) {
                    matrix[a][b][number-1] = 0;
                }
            }
        }
    }
    
    /**
     * Eliminates other possibilities at matrix[row][column] 
     * 
     */
    private void addNumberToMatrix(int row, int column, int number)
    {
            for (int k = 0; k < 9; k++)
            {
                if (k == (number - 1)) {
                    continue;
                }
                matrix[row][column][k] = 0;
            }
    }
    
    /**
     * Displays all the values in the 3rd dimension of each cell
     * used for debugging
     */
    private void displayRaw()
    {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    System.out.print(matrix[i][j][k]);
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
    
    private boolean solved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int zeroCount = 0;
                for (int k = 0; k < 9; k++) {
                    if (matrix[i][j][k] == 0) {
                        zeroCount++;
                    }
                }
                if (zeroCount < 8) {
                    return false;
                }
            }
        }
        return true;
    }
}

/* sample input
0
0
0
1
4
8
0
0
6
0
9
4
3
0
0
0
8
0
0
8
0
0
0
9
0
0
1
0
0
0
9
0
0
4
0
0
0
0
0
0
8
0
0
0
0
0
0
1
0
0
5
0
0
0
5
0
0
8
0
0
0
6
0
0
3
0
0
0
2
7
1
0
2
0
0
5
7
6
0
0
0 */