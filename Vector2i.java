/**
 * Created by Thomas on 2/4/2015.
 */

//quick little vector class for diagonal operations
//some methods are unused
public class Vector2i
{
    private int x, y;

    public Vector2i()
    {
       setXY(0, 0);
    }

    public Vector2i(int x, int y)
    {
        setXY(x, y);
    }

    public Vector2i setX(int x)
    {
        this.x = x;
        return this;
    }

    public Vector2i setY(int y)
    {
        this.y = y;
        return this;
    }

    public Vector2i setXY(int x, int y)
    {
        this.x = x;
        this.y = y;

        return this;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    //increases the vector's components by amounts x and y
    public Vector2i increase(int x, int y)
    {
        this.x += x;
        this.y += y;

        return this;
    }
}
