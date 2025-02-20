package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 9252 LCS 2 G4
 * 문제 풀이 방법 : DP
 */
public class baek9252 {
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

        char[] arrN = N.toCharArray();
        char[] arrM = M.toCharArray();

        dp = new int[sizeN + 1][sizeM + 1];

        for (int i = 1; i <= sizeN; i++) {
            for (int j = 1; j <= sizeM; j++) {
                if (arrN[i - 1] == arrM[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        StringBuilder lcs = new StringBuilder();
        int i = sizeN, j = sizeM;
        while (i > 0 && j > 0) {
            if (arrN[i - 1] == arrM[j - 1]) {
                lcs.append(N.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(dp[sizeN][sizeM]).append("\n").append(lcs.reverse());

        System.out.print(result);
    }
}
