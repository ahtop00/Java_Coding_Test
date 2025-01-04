package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*12865 평범한 배낭 G5*/
public class baek12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K + 1][N + 1];
        int[] value = new int[N + 1];
        int[] weight = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (i >= weight[j]) {
                    dp[i][j] = Math.max(dp[i][j - 1], value[j] + dp[i - weight[j]][j - 1]);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[K][N]);
    }
}
