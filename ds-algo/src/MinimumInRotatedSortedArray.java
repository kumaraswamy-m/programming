/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * Example 3:
 *
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * All the integers of nums are unique.
 * nums is sorted and rotated between 1 and n times.
 */

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */

/**
 *  We use the Binary Search Approach. For the first instance we would take three pointers first, mid and last (Ideally first < mid < last).
 *  If first > mid then we make last = mid and get the new mid (between first and mid).[Minimum element must be in left half]
 *  If mid > last then we make first = mid and get the new mid (between last and mid). [Minimum element must be in right half]
 *
 */
public class MinimumInRotatedSortedArray {

    /**
     *  We take a first pointer, a last pointer, and a mid.
     *  So if the first side is sorted, then the first element is the smallest in that part. So we update our minVal and check on the second half.
     *  If the second half is sorted then mid is going to be the smallest value for that part, we update the result as min(mid,minVal).
     * @param nums
     * @return
     */
    public static int findMin (int[] nums) {
        int first = 0, last = nums.length-1;
        int minValue = Integer.MAX_VALUE;
        while (first <= last) {
            // if the entire array is sorted
            if (nums[first] < nums[last]) {
                minValue = Math.min(minValue, nums[first]);
                break;
            }

            int mid = (first + last)/2;

            // if first half of the array is sorted - then the first element in that half is minimum element and
            // we look in the second half.
            if (nums[first] <= nums[mid]) {
                minValue = Math.min(minValue, nums[first]);
                first = mid+1;
            } else {
                // if second half of the array is sorted - then the mid element (first element in that half) is minimum element and
                // we look in the first half.
                minValue = Math.min(minValue, nums[mid]);
                last = mid - 1;
            }
        }
        return minValue;
    }

    public static void main (String[] args) {
        //int[] nums = {4, 5, 6, 7, 0, 1, 2};
        //int[] nums = {11,13,15,17};
        int[] nums = {2, 1};
        System.out.println(findMin(nums));
    }
}
