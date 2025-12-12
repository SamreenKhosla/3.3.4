public class RotationMatrix2x2 {

    //Each 2x2 rotation matrix has four values: 
    // a b
    // c d

    private double a, b, c, d;

    //Constructor to initialize the matrix values
    public RotationMatrix2x2(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    //Getter methods to acess matrix values
    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }
    public double getD() { return d; }


    //Multipy a 1x2 vector by this 2x2 matrix
    // Returns a new Vector1by2 as the result with the transformed coordinates
    public static Vector1by2 multiply(Vector1by2 v, RotationMatrix2x2 m) {
        //Treat each column of the matrix as a vector
        Vector1by2 col1 = new Vector1by2(m.a, m.c);
        Vector1by2 col2 = new Vector1by2(m.b, m.d);
        
        double newX = v.dot(col1);// calculate new x coordinate
        double newY = v.dot(col2); // calculate new y coordinate
        return new Vector1by2(newX, newY); //return new vector with transformed coordinates
    }

    //create 180 degree rotation matrix
    public static RotationMatrix2x2 make180() {
        return new RotationMatrix2x2(-1, 0, 0, -1);
    }

    //create 90 degree clockwise rotation matrix
    public static RotationMatrix2x2 make90CW() {
        return new RotationMatrix2x2(0, 1, -1, 0);
    }

    //create 90 degree counter-clockwise rotation matrix
    public static RotationMatrix2x2 make90CCW() {
        return new RotationMatrix2x2(0, -1, 1, 0);
    }

    //shows the matrix as a string
    public String toString() {
        return "[" + a + "  " + b + "]\n[" + c + "  " + d + "]";
    }
}
