public class ArrayExercises {
    /**
     * Returns the second to last item in the given array.
     * Assumes the array has at least 2 elements.
     */
    public static String secondToLastItem(String[] items) {
        // TODO: Implement this method
        return items[items.length - 2];
    }

    /**
     * Returns the difference between the minimum and maximum item in the given array
     */
    public static int minMaxDifference(int[] items) {
        // TODO: Implement this method
        int max = items[0];
        int min = items[0];
        for (int i = 1; i < items.length; i++) {
            if (items[i] > max) {
                max = items[i];
            }
            if (items[i] < min) {
                min = items[i];
            }
        }
        return max - min;
    }
}
