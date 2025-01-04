package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*1932 정수 삼각형 S1*/
public class baek1932 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n][n];

        int count = 0;
        for (int i = 0; i < n; i++) {
            count++;
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < count; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = 1;
        for (int i = 1; i < n; i++) {
            count++;
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    dp[i][j] += dp[i - 1][j];
                } else {
                    dp[i][j] += Math.max(dp[i - 1][j], dp[i -1][j - 1]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (max < dp[n -1][i]) {
                max = dp[n -1][i];
            }
        }

        System.out.println(max);
    }
}
