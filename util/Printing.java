package pokemon_project.util;

/**  
 * Utility class to print stuff more easily.
 * 
 * * @author DredoMiliCete
 */

public class Printing {
    /**
     * Prints a formatted error message.
     * <p>
     * Output format: {@code [ ERROR : error_message ]}
     * </p>
     * 
     * @param error_message the error message to be displayed
     */

    public static void error(String error_message) { System.out.printf("[ ERROR : %s ]\n", error_message); }

    /**
     * Skips over to the next line.
     */
    public static void enter() { System.out.println(); }
}