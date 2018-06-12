package search;

public class ModBinarySearch {
    //Pre P1: forall i < j = 0 .. arr.length - 1: arr[i] >= arr[j]
    private static int iterativeBinarySearch(int x, int[] arr) {
        //P1
        int l = -1, r = arr.length;
        //P1 &&
        //l == -1 && r == arr.length &&
        //P2: forall i=(0..arr.length-1): a'[i] = a[i], x' == x

        //Inv I: P1 && P2 &&
        //l, r from (-1..arr.length) => l < r, l <= l`,  r' <= r
        //forall i (1..n): (i < l`) => (arr[i] > x), (i >= r`) => (arr[i] <= x)
        //(l > 0) => (arr[l] > x), (r < arr.length) => (arr[r] <= x)
        while (r - l > 1) {
            //I && (r - l > 1)
            int m = (l + r) / 2;
            //I && (r - l > 1) && (l <= m <= r)

            //I && (r - l > 1) && (l <= m <= r)
            if (arr[m] > x) {
                //I && (r - l > 1) && (l <= m <= r) && arr[m] > x
                l = m;
                //I && (r - l > 1) && (l <= m <= r) && arr[m] > x && l' = m
            } else {
                //I && (r - l > 1) && (l <= m <= r) && arr[m] <= x
                r = m;
                //I && (r - l > 1) && (l <= m <= r) && arr[m] <= x  && r' = m
            }
            //I
        }
        //Post: P1 && P2 &&
        //i from (0..r' - 1): arr[i] > x
        //i from (r'..arr.length - 1): arr[i] <= x
        //r' == l' + 1 && 0 <= && r' && r' < arr.length

        //P1 && P2 &&
        //Post: R == r' && 0 <= R && R <= arr.length &&
        //i from (0..R - 1): x < arr[i]
        //i from (R..arr.length - 1): arr[i] <= x
        return r;
    }


    //Pre Pall: forall i < j = 0 .. arr.length - 1: arr[i] >= arr[j]
    //forall i=(0..arr.length-1): arr'[i] = arr[i], x' == x
    //-1 <= l && l < r && r < arr.length
    //forall i=(0..l): x < arr[i]
    //forall i=(r..arr.length - 1): arr[i] <= x
    private static int recursiveBinarySearch(int x, int[] arr, int l, int r) {
        //Pall
        if (r - l == 1) {
            //Pall && r - l == 1 &&
            return r;
        }

        //Pall && r - l > 1
        int m = (l + r) / 2;
        //Pall && r - l > 1
        // m == (l + r) / 2 && max(0, l) <= m && m <= min(arr.length - 1, r)

        //Pall && r - l > 1
        // m' == (l + r) / 2 && max(0, l) <= m && m <= min(arr.length - 1, r)
        if (x < arr[m]) {
            //Pall && r - l > 1
            // m == (l + r) / 2 && max(0, l) <= m && m <= min(arr.length - 1, r)
            //x < arr[m] &&
            //forall i=(0..m): x < arr[i]
            return recursiveBinarySearch(x, arr, m, r);
        } else {
            //Pall && r - l > 1
            // m == (l + r) / 2 && max(0, l) <= m && m <= min(arr.length - 1, r)
            //forall i=(m..arr.length-1): arr[i] <= x
            return recursiveBinarySearch(x, arr, l, m);
        }
    }
    //Post:
    // R: 0 <= R && R <= arr.length &&
    //i from (0..R - 1): x < arr[i]
    //i from (R..arr.length - 1): arr[i] <= x
    //forall i=(0..arr.length-1): arr'[i] = arr[i], x' == x


    public static void main(String[] args) {
        if (args.length <= 0) {
            System.err.println("enter number and array");
            return;
        }

        int x = Integer.parseInt(args[0]);

        int[] arr = new int[args.length - 1];

        for (int i = 1; i < args.length; ++i) {
            arr[i - 1] = Integer.parseInt(args[i]);
        }
        //Post: forall i=(0 .. arr.length - 1): arr[i] == args[i + 1]

        //Pre P1: forall i < j = 0 .. arr.length - 1: arr[i] >= arr[j]
        int ansIterative = iterativeBinarySearch(x, arr);
        //Post:arr[ansIterative] <= x && (ansIterative > 0 && arr[ansIterative - 1] <= x || ansIterative == 0)
        // || (ansIterative == 0 && (arr.length == 0 || arr[0] > x))

        //P1
        int ansRecursive = recursiveBinarySearch(x, arr, -1, arr.length);
        //Post:arr[ansRecursive] <= x && (ansRecursive > 0 && arr[ansRecursive - 1] <= x || ansRecursive == 0)
        // || (ansRecursive == 0 && (arr.length == 0 || arr[0] > x))

        System.out.println(ansIterative);
        //System.out.println(ansRecursive);
    }
}
