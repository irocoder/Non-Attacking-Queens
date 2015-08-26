/**
 * Created by Thomas on 2/3/2015.
 */
public class NonAttackingQueens
{
    static final int WIDTH = 8;
    static final int HEIGHT = 8;

    public int[] board;
    // 0's on the board represent possible queen positions
    // numbers >= 1 represent positions in which a queen may not be placed

    public NonAttackingQueens()
    {
        board = new int[WIDTH * HEIGHT];

        //For simplicity's sake, the method only works if you start at the top left corner
        if(placeQueen(0, 1))
            displayBoard();
    }

    public void displayBoard()
    {
        System.out.println("-----------------------");

        for (int j = 0; j < HEIGHT; j++)
        {
            for (int k = 0; k < WIDTH; k++)
            {
                System.out.print(board[j * WIDTH + k] + " ");
            }

            System.out.println();
        }

        System.out.println("-----------------------");
    }

    //returns true for success and false for failure in placing a queen in a suitable location
    //y is the Y position at which the program will search for a suitable X position for a queen
    //level is the current level of recursion, which helps in blocking out possible positions for
    //queens
    public boolean placeQueen(int y, int level)
    {
        for (int i = 0; i < WIDTH; i++)
        {
            if (board[y * WIDTH + i] == 0)
            {
                genBlocks(i, y, level);
                //System.out.println("LEVEL: " + level);
                //displayBoard(); // for debugging purposes

                //base case
                if (y == WIDTH - 1)
                    return true;

                if (placeQueen(y + 1, level + 1))
                    return true;
                else
                {
                    removeNums(level);
                    //displayBoard(); //for debugging purposes
                }
            }
        }

        return false;
    }

    //blocks out the positions which may be attacked by a queen located at position (x, y)
    public void genBlocks(int x, int y, int level)
    {
        blockRow(x, y, level);
        blockColumn(x, y, level);
        blockDiagonals(x, y, level);
    }

    void blockRow(int x, int y, int level)
    {
        for (int i = 0; i < WIDTH; i++)
        {
            if (i != x && board[y * WIDTH + i] == 0)
            {
                board[y * WIDTH + i] = level;
            }
        }
    }

    void blockColumn(int x, int y, int level)
    {
        for (int i = 0; i < HEIGHT; i++)
        {
            if (i != y && board[i * WIDTH + x] == 0)
            {
                board[i * WIDTH + x] = level;
            }
        }
    }

    //lots of slow code in this method, but I am eschewing speed in favor of readability
    void blockDiagonals(int x, int y, int level)
    {
        Vector2i vec = new Vector2i(x + 1, y + 1);

        for (int i = 0; i < HEIGHT; i++)
        {
            //make sure that board[boardPos(vec.getX(), vec.getY()] is 0, otherwise things get really screwy
            //when you have to backtrack and remove a certain blocked positions labelled with a certain number
            if (vec.getX() < WIDTH && vec.getY() < HEIGHT && board[boardPos(vec.getX(), vec.getY())] == 0)
            {
                board[boardPos(vec.getX(), vec.getY())] = level;
            }

            vec.increase(1, 1);
        }

        Vector2i vec1 = new Vector2i(x - 1, y - 1);

        for (int i = 0; i < HEIGHT; i++)
        {
            if (vec1.getX() >= 0 && vec1.getY() >= 0 && board[boardPos(vec1.getX(), vec1.getY())] == 0)
            {
                board[boardPos(vec1.getX(), vec1.getY())] = level;
            }

            vec1.increase(-1, -1);
        }

        Vector2i vec2 = new Vector2i(x + 1, y - 1);

        for (int i = 0; i < HEIGHT; i++)
        {
            if (vec2.getX() < HEIGHT && vec2.getY() >= 0 && board[boardPos(vec2.getX(), vec2.getY())] == 0)
            {
                board[boardPos(vec2.getX(), vec2.getY())] = level;
            }

            vec2.increase(1, -1);
        }

        Vector2i vec3 = new Vector2i(x - 1, y + 1);

        for (int i = 0; i < HEIGHT; i++)
        {
            if (vec3.getX() >= 0 && vec3.getY() < HEIGHT && board[boardPos(vec3.getX(), vec3.getY())] == 0)
            {
                board[boardPos(vec3.getX(), vec3.getY())] = level;
            }

            vec3.increase(-1, 1);
        }
    }

    //function for accessing a position on the board
    //and yes, I am aware that I could have used 2D arrays
    int boardPos(int x, int y)
    {
        return y * WIDTH + x;
    }

    //unblock positions labeled with "num"
    public void removeNums(int num)
    {
        for (int i = 0; i < WIDTH * HEIGHT; i++)
        {
            if (board[i] == num)
                board[i] = 0;
        }
    }
}