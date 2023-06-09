package com.ds.demo;

/**
 * Given three sorted arrays A, B  and Cof not necessarily same sizes.
 *
 * Calculate the minimum absolute difference between the maximum and minimum number from the triplet a, b, c such that a, b, c belongs arrays A, B, C respectively.
 *
 * i.e. minimize | max(a,b,c) - min(a,b,c) |.
 *
 * Example :
 *
 * Input:
 * A : [ 1, 4, 5, 8, 10 ]
 * B : [ 6, 9, 15 ]
 * C : [ 2, 3, 6, 6 ]
 * Output:
 * 1
 *
 * Explanation: We get the minimum difference for a=5, b=6, c=6 as | max(a,b,c) - min(a,b,c) | = |6-5| = 1.
 */


/**
 * https://www.interviewbit.com/problems/minimize-the-absolute-difference/
 */

public class MinimizeAbsoluteDifference {

    /**
     * Here the approach is to minimize the absolute difference (max - min) we need to maximize the min.
     * We are using three pointers i, j, k - which is pointer in each array.
     * @param A
     * @param B
     * @param C
     * @return
     */
    public static int minAbsoluteDifference(int[] A, int[] B, int[] C) {
        int i = 0, j = 0, k =0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, absoluteDiff = Integer.MAX_VALUE;
        // Here we should note that we can stop if anytime the value of i, j, k becomes equal to the length of array.
        // We need not travel all the array entirely. Because we will increment value fo i , j or k for the array which has minimum value.
        while (i < A.length && j < B.length && k < C.length) {
            max = Math.max(Math.max(A[i], B[j]), C[k]);
            min = Math.min(Math.min(A[i], B[j]), C[k]);
            absoluteDiff = Math.min(absoluteDiff, Math.abs(max - min));
            // Here the approach is to minimize the absolute difference (max - min) we need to maximize the min.
            // Since the array is sorted we will go ahead in the array to which the minimum value belongs to so that we can increase the value from only that array.
            if (min == A[i])
                i++;
            else if (min == B[j])
                j++;
            else
                k++;
        }
        return absoluteDiff;
    }

    public static void main (String[] args) {
        int[] A = {1, 4, 5, 8};
        int[] B = {6, 9};
        int[] C = {2, 3, 6};
        System.out.println(minAbsoluteDifference(A, B, C));
        int[] D = {4};
        int[] E = {2, 5};
        int[] F = {4};
        System.out.println(minAbsoluteDifference(D, E, F));
    }
}
