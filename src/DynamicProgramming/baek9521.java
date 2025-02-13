package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 9521 LCS G5
 * 문제 풀이 방법 : DP
 */
public class baek9521 {
    static String N;
    static String M;

    static int sizeN;
    static int sizeM;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = br.readLine();
        M = br.readLine();

        sizeN = N.length();
        sizeM = M.length();

        dp = new int[sizeN + 1][sizeM + 1];

        for (int i = 1; i <= sizeN; i++) {
            for (int j = 1; j <= sizeM; j++) {
                if (N.charAt(i - 1) == M.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], Math.max(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }

        System.out.print(dp[sizeN][sizeM]);
    }
}
