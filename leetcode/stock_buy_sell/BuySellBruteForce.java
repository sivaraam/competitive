public class BuySellBruteForce {
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int pricesCount = prices.length;

        for (int buyIndex = 0; buyIndex < pricesCount - 1; buyIndex++) {
            int buyPrice = prices[buyIndex];

            for (int sellIndex = buyIndex + 1; sellIndex < pricesCount; sellIndex++) {
                int sellPrice = prices[sellIndex];

                if (sellPrice > buyPrice) {
                    int profit = sellPrice - buyPrice;
                    if (maxProfit < profit) {
                        maxProfit = profit;
                    }
                }
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = new int[] { 7,1,5,3,6,4 };
        System.out.println(maxProfit(prices));

        prices = new int[] { 7,6,4,3,1 };
        System.out.println(maxProfit(prices));

        prices = new int[] { -1, 10, 9, 7, 40, 58, -18, 200, -1 };
        System.out.println(maxProfit(prices));
    }
}
