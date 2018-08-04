
// A Recursive java program to solve 
// minimum sum partition problem.
import java.io.*;
import java.util.Arrays;

class Irq_Balancing_DP
{
    // Returns the minimum value of 
    //the difference of the two sets.
    static int findMin(int arr[], int n)
    {
        // Calculate sum of all elements
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += arr[i];

        // Create an array to store 
        // results of subproblems
        boolean dp[][] = new boolean[n + 1][sum + 1];

        // Initialize first column as true.
        // 0 sum is possible with all elements.
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;
        System.out.println("After initing 2D DP array ALL False:");
        System.out.println(Arrays.deepToString(dp));

        // Initialize top row, except dp[0][0],
        // as false. With 0 elements, no other 
        // sum except 0 is possible
        for (int i = 1; i <= sum; i++)
            dp[0][i] = false;

        System.out.println("After setting top row to False :");
        System.out.println(Arrays.deepToString(dp));

        // Fill the partition table 
        // in bottom up manner
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= sum; j++)
            {
                System.out.println ("inner loop sum, j = " + j);

                // If i'th element is excluded
                dp[i][j] = dp[i - 1][j];
//                System.out.println("After " + i + "'th element excluded :");
//                System.out.println(Arrays.deepToString(dp));

                // If i'th element is included
                if (arr[i - 1] <= j)
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]];
            }
            System.out.println ("outer loop n, i = " + i);
        }
//      System.out.println ("The minimum difference between 2 sets is ");
        System.out.println("After Filling the partition table :");
        System.out.println(Arrays.deepToString(dp));
//        System.out.println(dp);

        // Initialize difference of two sums. 
        int diff = Integer.MAX_VALUE;

        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--)
        {
            if (dp[n][j] == true)
            {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }

    // Driver program
    public static void main (String[] args)
    {
        int arr[] = {3, 1, 4, 2, 2, 1};
        int n = arr.length;
        System.out.println ("The minimum difference between 2 sets is "
                + findMin(arr, n));
    }
}