package pokemon_project.util;

public class Util {
    public static double randomInBetween(double a, double b) {
        return a + (Math.random() * (Math.nextUp(b) - a));
    }
}