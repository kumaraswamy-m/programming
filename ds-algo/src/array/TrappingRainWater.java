package array;

public class TrappingRainWater
{

    public static void main (String[] a)
    {
        TrappingRainWater rainWater = new TrappingRainWater();
        int[] height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        int totalWater = rainWater.trap(height);
        System.out.println("Rain water trapped is " + totalWater + " units");
    }

    public int trap (int[] height)
    {
        int[] dp = new int[height.length];

        int maxHeight = 0;

        for (int i = 0; i < height.length; i++) {

            if (height[i] > maxHeight) {
                maxHeight = height[i];
                dp[i] = -1;
            }
            else {
                dp[i] = maxHeight;
            }
            System.out.println(maxHeight + " " + dp[i]);
        }

        int totalWater = 0;
        maxHeight = 0;
        for (int i = height.length - 1; i > -1; i--) {
            if (height[i] > maxHeight) {
                maxHeight = height[i];
            }
            else if (dp[i] != -1) {
                totalWater += (Math.min(dp[i],
                                        maxHeight) - height[i]);
            }
        }

        return totalWater;
    }
}
