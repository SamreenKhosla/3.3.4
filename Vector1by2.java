public class Vector1by2 {

    //x and y values of the vector
    private double x;
    private double y;

    //Constructor to initialize vector values
    public Vector1by2(double x, double y) {

        this.x = x;
        this.y = y;
    }

    //Getter and Setter methods for x and y
    public double getX() { return x; }
    public double getY() { return y; }

    //Setter methods for x and y
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    //add another vector to this one
    public void add(Vector1by2 other) {

        this.x += other.x;
        this.y += other.y;
    }

    //calculate the dot product with another vector
    public double dot(Vector1by2 other) {

        return this.x * other.x + this.y * other.y;
    }

    //shows the vector as a string
    public String toString() {
        
        return "(" + x + ", " + y + ")";
    }
}
