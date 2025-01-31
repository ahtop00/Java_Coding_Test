package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 9465 스티커 S1
 * 문제 풀이 방법 : DP
 */
public class baek9465 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            int[][] dp = new int[3][N + 1];
            int[][] arr = new int[2][N + 1];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            for (int i = 1; i <= N; i++) {
                dp[0][i] = Math.max(dp[0][i - 1], Math.max(dp[1][i - 1], dp[2][i -1]));
                dp[1][i] = Math.max(dp[0][i - 1], dp[2][i -1]) + arr[0][i];
                dp[2][i] = Math.max(dp[0][i - 1], dp[1][i -1]) + arr[1][i];
            }

            int result = 0;
            for (int i = 0; i < 3; i++) {
                result = Math.max(result, dp[i][N]);
            }
            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}
