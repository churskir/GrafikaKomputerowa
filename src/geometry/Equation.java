package geometry;

public class Equation {
    private double freeVariable;
    private double boundVariable;

    public Equation(double freeVariable, double boundVariable) {
        this.freeVariable = freeVariable;
        this.boundVariable = boundVariable;
    }

    public double getValue(double t) {
        return freeVariable + t * boundVariable;
    }

    public double getFreeVariable() {
        return freeVariable;
    }

    public double getBoundVariable() {
        return boundVariable;
    }

    public String toString() {
        return "t * " + Double.toString(boundVariable) + " + " + Double.toString(freeVariable);
    }
}
