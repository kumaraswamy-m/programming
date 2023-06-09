/**
 * Problem Description
 *
 *
 *
 * There is a row of seats represented by string A. Assume that it contains N seats adjacent to each other.
 * There is a group of people who are already seated in that row randomly. i.e. some are sitting together & some are scattered.
 *
 * An occupied seat is marked with a character 'x' and an unoccupied seat is marked with a dot ('.')
 *
 * Now your target is to make the whole group sit together i.e. next to each other, without having any vacant seat between them in such a way that the total number of hops or jumps to move them should be minimum.
 *
 * In one jump a person can move to the adjacent seat (if available).
 *
 * NOTE:  1. Return your answer modulo 107 + 3.
 *
 *
 *
 * Problem Constraints
 * 1 <= N <= 1000000
 *
 * A[i] = 'x' or '.'
 *
 *
 *
 * Input Format
 * The first and only argument is a string A of size N.
 *
 *
 *
 * Output Format
 * Return an integer denoting the minimum number of jumps required.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = "....x..xx...x.."
 * Input 2:
 *
 *  A = "....xxx"
 *
 *
 * Example Output
 * Output 1:
 *
 *  5
 * Output 2:
 *
 *  0
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  Here is the row having 15 seats represented by the String (0, 1, 2, 3, ......... , 14)
 *                  . . . . x . . x x . . . x . .
 *  Now to make them sit together one of approaches is -
 *                  . . . . . . x x x x . . . . .
 *  Steps To achieve this:
 *  1) Move the person sitting at 4th index to 6th index: Number of jumps by him =   (6 - 4) = 2
 *  2) Bring the person sitting at 12th index to 9th index: Number of jumps by him = (12 - 9) = 3
 *  So, total number of jumps made: 2 + 3 = 5 which is the minimum possible.
 *
 *
 * If we other ways to make them sit together but the number of jumps will exceed 5 and that will not be minimum.
 *
 *
 * Explanation 2:
 *
 *  They are already together. So, the cost is zero.
 */


/**
 *  https://www.interviewbit.com/problems/seats/
 */


/**
 *  Approach Discussed:
 *     We group all the people sitting together. Then we form a 2*2 matrix which would give the cost of moving One group to other.
 *     Then sum all the column and minimum of this sums would give the minimum number of jumps required to move all the people together.
 */


import java.util.ArrayList;
import java.util.List;

/**
 *  Greedy Algorithm Approach:
 *       Always moving all the people towards center most person will be the solution.
 *       Such Transportation questions moving towards median is least costly.
 */
public class MinimumJumpSeats {


    /**
     * Greedy Algorithm Approach
     * Time complexity - O(n)
     * Space complexity - O(n)
     * @param A
     * @return
     */
    public static int minimumJumps (String A) {
        // Question was given to return the ans to the modulo of this
        int mod = 10000003;
        int minimumJumps = 0;

        // We form the array which gives the position of ever person sitting in the row
        List<Integer> peoplePositionArr = new ArrayList<>();
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i) == 'x')
                peoplePositionArr.add(i);
        }
        if (!peoplePositionArr.isEmpty()) {
            // Center most position towards which everyone has to move
            int medianPosition = peoplePositionArr.get(peoplePositionArr.size() / 2);
            for (int i = 0; i < peoplePositionArr.size(); i++) {
                int currentPosition = peoplePositionArr.get(i);

                // Final position of person after jumps and hops
                int newPosition = medianPosition - (peoplePositionArr.size() / 2 - i);
                minimumJumps = (minimumJumps + Math.abs(newPosition - currentPosition))%mod;
            }
        }
        return minimumJumps;
    }

    /**
     * Another Good intuitive Approach is  If two people are not sitting together, we have to fill that gap somehow.
     * In this approach whenever a gap is encountered we fill that gap by shifting either all the people on the left towards the gap
     * or all the people on the right towards the gap.
     * Since it would be less costly to move the people from the side wherever the number of people are less so following approach is used:
     * for each vacant seat (.):
     *   minimumJumps += min(people left to it, people right to it) // To fill the gap
     *
     *
     *   Time Complexity -  O(n)
     *   Space Complexity - O(1)
     * @param A
     * @return
     */
    public static int minimumJumps2 (String A) {
        int mod = 10000003;
        int totalNumOfPerson = 0, peopleOnLeft = 0;
        int minimumJumps = 0;
        // Count the total number of people sitting
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i) == 'x')
                totalNumOfPerson++;
        }
        for (int i = 0; i < A.length(); i++) {
            // Keeps the count of people on left
            if(A.charAt(i) == 'x')
                peopleOnLeft++;
            
            // Here we add to the number of people to be shifted.
            else
                minimumJumps = (minimumJumps + Math.min(peopleOnLeft, totalNumOfPerson-peopleOnLeft))%mod;
        }
        return minimumJumps;
    }

    public static void main (String[] args) {
        //String A = "....x..xx...x..";
        //String A = ".....";
        String A = "xxxxx.....xx..xx..xx..xx";
        System.out.println(minimumJumps(A));
        System.out.println(minimumJumps2(A));
    }

}
