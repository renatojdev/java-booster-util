
package io.rjdev.booster.util.num;

import java.util.Random;

public class IntUtil {

    /**
     * Returns a randomly generated integer with the specified length.
     *
     * @author Renato P E Jr
     * @param length the length of the randomly generated integer
     * @return the randomly generated integer
     */
    public static int randomNumber(byte lenght) {
      Random random = new Random();
        int limitedSize = Math.min(9, Math.max(1, lenght));
        String maxString = String.format("%0" + limitedSize + "d", 0).replace("0", "9");
        return random.nextInt(Integer.parseInt(maxString) - 1000*lenght) + 1;
      }

}
