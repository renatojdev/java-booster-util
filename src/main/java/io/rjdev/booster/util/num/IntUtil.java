
package io.rjdev.booster.util.num;

public class IntUtil {

    /**
     * Returns a randomly generated integer with the specified length.
     *
     * @author Renato P E Jr
     * @param length the length of the randomly generated integer
     * @return the randomly generated integer
     */
    public static int randomNumber(byte lenght) {
        int limitedSize = Math.min(9, Math.max(1, lenght));
        String maxString = String.format("%0" + limitedSize + "d", 0).replace("0", "9");
        return Integer.parseInt(maxString);
      }

}
