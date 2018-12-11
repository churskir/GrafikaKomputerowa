package light;

import geometry.Vector;

public class PhongsModel {

    private double Ia = 0; // natężenie światła w otoczeniu obiektu
    private double Ka = 0; // wsp. odbicia światła otoczenia
    private double Fatt = 1; // współczynnik tłumienia źródła światła z odległością
    private double Ip = 0.5; // natężenie światła punktowego (?)
    private double Kd = 0.25; // wsp. odbicia światła rozproszonego
    private double Ks = 1; // wsp. odbicia światła kierunkowego
    private double n = 0.1; // wsp. gładkości powierzchni

    // L - wektor pomiędzy punktem na powierzchni a źródłem światła
    // N - wektor normalny prostopadły do powierzchni
    // a - kąt pomiędzy promieniem odbitym a kierunkiem do obserwatora
//    public int getValue(Vector L, Vector N, Vector V, Vector R, double a, boolean x) {
//        if (x) System.out.println(Fatt * Ip * Kd * Vector.multiply(N, L));
//        if (x) System.out.println(Fatt * Ip * Ks * Math.pow(Math.cos(a), n));
//        if (x) System.out.println("---");
//        int light = (int) (Ia * Ka +
//                Fatt * Ip * Kd * Vector.multiply(N, L) +
//                Fatt * Ip * Ks * Math.pow(Vector.multiply(V, R), n));
//        return  light;
//    }


    public int getValue(Vector L, Vector N, double a, boolean x) {
        if (x) System.out.println(Fatt * Ip * Kd * Vector.multiply(N, L));
        if (x) System.out.println(Fatt * Ip * Ks * Math.pow(Math.cos(a), n));
        if (x) System.out.println("---");
        int light = (int) (Ia * Ka +
                Fatt * Ip * Kd * Vector.multiply(N, L) +
                Fatt * Ip * Ks * Math.pow(Math.cos(a), n));
        return  light;
    }
}
