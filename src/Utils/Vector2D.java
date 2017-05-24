package Utils;

/**
 * Created by jevanger on 5/12/2017.
 */
public class Vector2D {

    // X position
    public float X;

    // Y position
    public float Y;

    public Vector2D(float x, float y)
    {
        X = x;
        Y = y;
    }

    // gets the magnitude of the vector
    public float length()
    {
        return (float) Math.sqrt(X*X + Y*Y);
    }

    public Vector2D add(Vector2D other)
    {
        Vector2D v2 = new Vector2D(X + other.X, Y + other.Y);

        return v2;
    }

    public Vector2D sub( Vector2D other )
    {
        Vector2D v2 = new Vector2D(X - other.X, Y - other.Y);

        return v2;
    }

    public Vector2D scale(float scaleFactor)
    {
        Vector2D v2 = new Vector2D(X * scaleFactor, Y * scaleFactor);

        return v2;
    }

    public Vector2D normalize()
    {
        Vector2D v2 = new Vector2D(0.0f, 0.0f);

        float length = length();

        if (length != 0) {
            v2.X = X / length;
            v2.Y = Y / length;
        }

        return v2;
    }

    public boolean equals(Vector2D other)
    {
        return this.X == other.X && this.Y == other.Y;
    }

    public double dotProduct (Vector2D other)
    {
        return X * other.X + Y * other.Y;
    }

    public String toString()
    {
        return "X: " + X + " Y: " + Y;
    }
}
