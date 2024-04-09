package search;

public class BinarySearch {
    // Model: (int a[0], int a[1], ... int a[n - 1], int x)
    // Pred: a[0] <= a[1] <= ... <= a[n - 1], x
    // Post: R, 0 <= R <= a.length - 1 && ((a[R] >= x && (a[R - 1] < x || R == 0)) || R == a.length - 1)
    public static int iterativeSearch(final int[] a, final int x) {
        // Pred: a[0] <= a[1] <= ... <= a[n - 1] (массив не изменяется, так что это всегда выполняется)

        int l = -1;
        // Post: l == -1

        int r = a.length - 1;
        // Post: l == -1 && r == a.length - 1

        // Pred: a.length > r' > l' >= -1 && l' == -1 && r' == a.length - 1
        while (r - l > 1) {
            // Pred: a.length > r' > l' >= -1 && (l' == -1 || a[l'] < x) && (r' == a.length - 1 || a[r'] >= x)
            int mid = (r + l) / 2;
            // Post: a.length > r' > l' >= -1 && r - l > 1 => mid > l' && mid < r'

            // Pred: a.length > r' > l' >= -1 && r' > mid > l' && (l' == -1 || a[l'] < x) && (r' == a.length - 1 || a[r'] >= x)
            if (a[mid] >= x) {
                // Pred: r' > mid > l' && a[mid] >= x
                r = mid;
                // Post: a.length > r' > l' >= -1 && r' > l' && (l' == -1 || a[l'] < x) && a[r'] >= x
            } else {
                // Pred: r' > mid > l' && a[mid] < x
                l = mid;
                // Post: a.length > r' > l' >= -1 && r' > l' && a[l'] < x && (r' == a.length - 1 || a[r'] >= x)
            }
            // Post: a.length > r' > l' >= -1 && r' > l' && (l' == -1 || a[l'] < x) && (r' == a.length - 1 || a[r'] >= x)
        }
        // Post: a.length > r' > l' >= -1 && r' - l' == 1 && (l' == -1 || a[l'] < x) && (r' == a.length - 1 || a[r'] >= x)
        return r;
    }

    public static int recursiveSearch(final int[] a, final int x) {
        // Pred: a[0] <= a[1] <= ... <= a[n - 1]
        return search(a, -1, a.length - 1, x);
    }

    // Model: (int a[0], int a[1], ... int a[n - 1], int x)
    // Pred: a[0] <= a[1] <= ... <= a[n - 1], x
    // Post: R, 0 <= R <= a.length - 1 && ((a[R] >= x && (a[R - 1] < x || R == 0)) || R == a.length - 1)
    private static int search(final int[] a, final int l, final int r, final int x) {
        // Pred: a[0] <= a[1] <= ... <= a[n - 1] (массив не изменяется, так что это всегда выполняется)

        // Pred: a.length > r' > l' >= -1 && (l' == -1 || a[l'] < x) && (r' == a.length || a[r'] >= x)
        if (r - l == 1) {
            // Pred: a.length > r' > l' >= -1 && r' - l' == 1 && (l' == -1 || a[l'] < x) && (r' == a.length || a[r'] >= x)
            return r;
        }
        // Post: a.length > r' > l' >= -1 && r' - l' > 1 && (l' == -1 || a[l'] < x) && (r' == a.length || a[r'] >= x)

        // Pred: a.length > r' > l' >= -1 && r' - l' > 1 && (l' == -1 || a[l'] < x) && (r' == a.length || a[r'] >= x)
        int mid = (r + l) / 2;
        // Post: a.length > r' > mid > l' >= -1

        // Pred: a.length > r' > mid > l' >= -1 && r' - l' > 1 && (l' == -1 || a[l'] < x) && (r' == a.length || a[r'] >= x)
        if (a[mid] >= x) {
            // Pred: a.length > mid > l' >= -1 && mid - l' >= 1 && (l' == -1 || a[l'] < x) && a[mid] >= x
            return search(a, l, mid, x);
        } else {
            // Pred: a.length > r' > mid >= -1 && r' - mid >= 1 && a[mid] < x && (r' == a.length || a[r'] >= x)
            return search(a, mid, r, x);
        }
    }
}
