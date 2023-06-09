/**
 * Given an array arr[] of N non-negative integers representing the height of blocks. If width of each block is 1, compute how much water can be trapped between the blocks during the rainy season.
 *
 * Example 1:
 *
 * Input:
 * N = 6
 * arr[] = {3,0,0,2,0,4}
 * Output:
 * 10
 * Explanation:
 *
 * Example 2:
 *
 * Input:
 * N = 4
 * arr[] = {7,4,0,9}
 * Output:
 * 10
 * Explanation:
 * Water trapped by above
 * block of height 4 is 3 units and above
 * block of height 0 is 7 units. So, the
 * total unit of water trapped is 10 units.
 * Example 3:
 *
 * Input:
 * N = 3
 * arr[] = {6,9,9}
 * Output:
 * 0
 * Explanation:
 * No water will be trapped.
 *
 * Your Task:
 * You don't need to read input or print anything. The task is to complete the function trappingWater() which takes arr[] and N as input parameters and returns the total amount of water that can be trapped.
 *
 *
 * Expected Time Complexity: O(N)
 * Expected Auxiliary Space: O(N)
 *
 *
 * Constraints:
 * 3 < N < 106
 * 0 < Ai < 108
 *
 */

/**
 * https://practice.geeksforgeeks.org/problems/trapping-rain-water-1587115621/1?page=1&sortBy=submissions
 */

/**
 * We can take help of 2 auxillary arrays- One array would contain the taller building to the left of the current building.
 * Other Array would contain the taller building to the right of the current building.
 * Eg:-  Building array (Given):           [3, 0, 0, 2, 0, 4]
 *       Taller Building to the left:      [3, 3, 3, 3, 3, 4]
 *       Taller Building to the right:     [4, 4, 4, 4, 4, 4]
 * We can now get the volume at each block with the formula: Min (left[i], right[i]) - Array[i]
 *        Using Formula:                   [0, 3, 3, 1, 3, 0]
 *        Total Volume (Sum):                     10
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class RainWaterTrapping {

    public static int volumeOfTrappedWater(int[] height) {
        int volume = 0;
        int[] leftArr = new int[height.length];
        int[] rightArr = new int[height.length];

        // Auxilary Array - Taller building to the left
        leftArr[0] = height[0];
        for(int i = 1; i < height.length; i++)
            leftArr[i] = Math.max(leftArr[i-1], height[i]);

        //Auxilary Array - Taller Building to the right
        rightArr[height.length-1] = height[height.length- 1];
        for(int i = height.length-2; i >= 0; i--)
            rightArr[i] = Math.max(rightArr[i+1], height[i]);

        // Compute the Area
        for(int i = 0; i < height.length; i++)
            volume += (Math.min(leftArr[i], rightArr[i]) - height[i]);
        return volume;
    }


    public static void main (String[] args) {
        int[] height1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(volumeOfTrappedWater(height1));
        int[] height2 = {3, 0, 0, 2, 0, 4};
        System.out.println(volumeOfTrappedWater(height2));
    }
}
