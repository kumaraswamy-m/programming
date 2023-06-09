package com.ds.demo;

/**
 * Problem Description
 *
 *
 *
 * Say you have an array, A, for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit.
 *
 * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 *
 *
 * Problem Constraints
 * 0 <= len(A) <= 1e5
 * 1 <= A[i] <= 1e7
 *
 *
 * Input Format
 * The first and only argument is an array of integers, A.
 *
 *
 * Output Format
 * Return an integer, representing the maximum possible profit.
 *
 *
 * Example Input
 * Input 1:
 *     A = [1, 2, 3]
 * Input 2:
 *     A = [5, 2, 10]
 *
 *
 * Example Output
 * Output 1:
 *     2
 * Output 2:
 *     8
 *
 *
 * Example Explanation
 * Explanation 1:
 *     => Buy a stock on day 0.
 *     => Sell the stock on day 1. (Profit +1)
 *     => Buy a stock on day 1.
 *     => Sell the stock on day 2. (Profit +1)
 *
 * Overall Profit = 2
 *
 *
 * Explanation 2:
 *     => Buy a stock on day 1.
 *     => Sell the stock on on day 2. (Profit +8)
 *
 *
 *
 * Overall profit = 8
 */



/**
 * https://www.interviewbit.com/problems/best-time-to-buy-and-sell-stocks-ii/
 */
public class BestTimeToBuySellStock {

    // Here we just sell whenever the previous day price is less than the current day price and keep adding.
    public static int maxProfit(int[] A) {
        int profit = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i-1] )
                profit += (A[i]-A[i-1]);
        }
        return profit;
    }

    // Here the approach used is that we always buy at the bottom of the curve and sell at the peak so that the profit is maximized.
    public static int maxProfitWithMinimumTransaction(int[] A) {
        // One can buy only if one does not already hold a share.
        boolean canBuy = true;
        int cp = 0, sp = 0, profit = 0;
        for (int i = 0; i < A.length; i++) {
            // Buy by default after selling
            if (canBuy) {
                cp = A[i];
                canBuy = false;
            } else {
                // Continue finding the minimum price
                if (A[i] < cp) {
                    cp = A[i];
                } else {
                    // Find the peak
                    while (i < A.length-1 && A[i+1]>=A[i])
                        i++;
                    sp = A[i];
                    profit += (sp-cp);
                    canBuy = true;
                }
            }
        }
        return profit;
    }

    public static void main (String[] args) {
        int[] A = {5, 2, 10};
        System.out.println(maxProfit(A));
        System.out.println(maxProfitWithMinimumTransaction(A));
        int[] B = {2, 1};
        System.out.println(maxProfit(B));
        System.out.println(maxProfitWithMinimumTransaction(B));
        int[] C ={7551982, 8124939, 4023780, 7868369, 4412570, 2542961, 7380261, 1164290, 7781065, 1164599, 2563492, 5354415, 4994454, 2627136, 5933501, 668219, 1821804, 7818378, 33654, 4167689, 8652323, 5750640, 9822437, 3466466, 554089, 6168826, 335687, 2466661, 8511732, 6288553, 2905889, 7747975, 3744045, 1545003, 1008624, 8041203, 7176125, 4321092, 714053, 7200073, 166697, 7814651, 3090485, 8318668, 6600364, 3352620, 2430137, 7685821, 1442555, 828955, 6540266, 5305436, 116568, 1883410, 7975347, 9629015, 4735259, 6559041, 1832532, 5840170, 6983732, 5886179, 1496505, 7241412, 144558, 9462840, 8579314, 2488436, 9677478, 7589124, 5636642, 2440601, 1767332, 2399786, 6299635, 8534665, 1367339, 805592, 5572668, 6990026, 8465261, 4808596, 7641452, 8464860, 3170126, 7403200, 6932907, 3776122, 1313688, 3992189, 2382116, 3886952, 349816, 1596435, 7353742, 9964868, 9882224, 3818546, 3885458, 1200559, 3910256, 7949895, 463872, 6392805, 9513226, 3427933, 3470571, 6225817, 552452, 5567651, 6414423, 6701681, 4725847, 894529, 8046603, 426263, 5280891, 9197661, 9764507, 1740413, 9530261, 9163599, 7561587, 5848442, 7312422, 4794268, 5793465, 5039382, 5147388, 7346933, 4697363, 6436473, 5159752, 2207985, 8256403, 8958858, 6099618, 2172252, 3063342, 4324166, 3919237, 8985768, 2703255, 2386343, 3064166, 247762, 7271683, 1812487, 7163753, 4635382, 449426, 2561592, 3746615, 8741199, 6696192, 606265, 5374062, 3065308, 6918398, 2956279, 8949324, 2804580, 3421479, 7846658, 8895839, 8277589, 1262596, 451779, 9972218, 6378556, 4216958, 7127258, 8593578, 326883, 4737513, 6578257, 7582654, 8675499, 9038961, 7902676, 8874020, 5513073, 631930, 912719, 8394492, 1508363, 455175, 9215635, 6813970, 2021710, 5673212, 184474, 4511247, 4653238, 2218883, 9669544, 295018, 3694660, 1709444, 4019025, 5047809, 45740, 1035395, 8159408, 1557286, 1304144, 6496263, 2094202, 9945315, 1905585, 1143081, 6994125, 9609830, 1077628, 3488222, 6299366, 7187139, 3883908, 7077292, 3210807, 7328762, 7695314, 1138834, 7689433, 5083719, 202831, 8138452, 5495064, 7543763, 1597085, 5429837, 8455839, 6925605, 6600884, 3571512, 3422637, 8911245, 3700762, 2338168, 6830853, 2539094, 490627, 2294717, 497349, 8297867, 7299269, 4769134, 285033, 4335917, 9908413, 152868, 2658658, 3525848, 1884044, 4953877, 8660374, 8989154, 888731, 7217408, 2614940, 7990455, 9779818, 1441488, 9605891, 4518756, 3705442, 9331226, 404585, 9011202, 7355000, 7461968, 6512552, 2689841, 2873446, 256454, 1068037, 8786859, 2323599, 3332506, 2361155, 7476810, 5605915, 5950352, 6491737, 8696129, 4637800, 4207476, 9334774, 840248, 9159149, 5201180, 7211332, 3135016, 8524857, 4566111, 7697488, 1833291, 7227481, 8289951, 2389102, 9102789, 8585135, 1869227, 4082835, 8447466, 4985240, 4176179 };
        System.out.println(maxProfit(C));
        System.out.println(maxProfitWithMinimumTransaction(C));
        int[] D = {1, 2, 3};
        System.out.println(maxProfit(D));
        System.out.println(maxProfitWithMinimumTransaction(D));
    }
}
