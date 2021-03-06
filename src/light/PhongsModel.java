package light;

import geometry.Vector;

public class PhongsModel {

    private double Ia = 0.5; // natężenie światła w otoczeniu obiektu
    private double Ka = 1; // wsp. odbicia światła otoczenia
    private double Fatt = 1; // współczynnik tłumienia źródła światła z odległością
    private double Ip = 0.5; // natężenie światła punktowego (?)
    private double Kd = 0.75; // wsp. odbicia światła rozproszonego
    private double Ks = 0.3; // wsp. odbicia światła kierunkowego
    private double n = 5; // wsp. gładkości powierzchni

    // L - wektor pomiędzy punktem na powierzchni a źródłem światła
    // N - wektor normalny prostopadły do powierzchni
    // a - kąt pomiędzy promieniem odbitym a kierunkiem do obserwatora
    public double getValue(Vector L, Vector N, double a) {
        return (Ia * Ka +
                Fatt * Ip * Kd * Vector.multiply(N, L) +
                Fatt * Ip * Ks * Math.pow(Math.cos(a), n));
    }

}
