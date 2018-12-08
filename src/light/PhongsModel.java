package light;

import geometry.Vector;

public class PhongsModel {

    private double Ia = 0; // natężenie światła w otoczeniu obiektu
    private double Ka = 0; // wsp. odbicia światła otoczenia
    private double Fatt = 1; // współczynnik tłumienia źródła światła z odległością
    private double Ip = 10; // natężenie światła punktowego (?)
    private double Kd = 0.25; // wsp. odbicia światła rozproszonego
    private double Ks = 0.75; // wsp. odbicia światła kierunkowego
    private double n = 1; // wsp. gładkości powierzchni

    // L - wektor pomiędzy punktem na powierzchni a źródłem światła
    // N - wektor normalny prostopadły do powierzchni
    // a - kąt pomiędzy promieniem odbitym a kierunkiem do obserwatora
    public int getValue(Vector L, Vector N, double a) {
        int light = (int) (Ia * Ka +
                Fatt * Ip * Kd * Vector.multiply(N, L) +
                Fatt * Ip * Ks * Math.pow(Math.cos(a), n));
        return  light;
    }

}
