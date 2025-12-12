public class Vector1by2 {

    private double x;
    private double y;

    public Vector1by2(double x, double y) {

        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void add(Vector1by2 other) {

        this.x += other.x;
        this.y += other.y;
    }

    public double dot(Vector1by2 other) {

        return this.x * other.x + this.y * other.y;
    }

    public String toString() {
        
        return "(" + x + ", " + y + ")";
    }
}
