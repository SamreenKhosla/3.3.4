public class RotationMatrix2x2 {

    private double a, b, c, d;

    public RotationMatrix2x2(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }
    public double getD() { return d; }

    public static Vector1by2 multiply(Vector1by2 v, RotationMatrix2x2 m) {
        Vector1by2 col1 = new Vector1by2(m.a, m.c);
        Vector1by2 col2 = new Vector1by2(m.b, m.d);
        double newX = v.dot(col1);
        double newY = v.dot(col2);
        return new Vector1by2(newX, newY);
    }

    public static RotationMatrix2x2 make180() {
        return new RotationMatrix2x2(-1, 0, 0, -1);
    }

    public static RotationMatrix2x2 make90CW() {
        return new RotationMatrix2x2(0, 1, -1, 0);
    }

    public static RotationMatrix2x2 make90CCW() {
        return new RotationMatrix2x2(0, -1, 1, 0);
    }

    public String toString() {
        return "[" + a + "  " + b + "]\n[" + c + "  " + d + "]";
    }
}
