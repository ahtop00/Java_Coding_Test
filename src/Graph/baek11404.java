package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 11404 플로이드
 * 문제 풀이 방법 : 이름값하는 문제
 */
public class baek11404 {
    static int N;
    static int M;
    static int[][] dp;
    static boolean[][] isWay;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp = new int[N][N];
        isWay = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            if (!isWay[s][e]) {
                dp[s][e] = cost;
                isWay[s][e] = true;
            } else {
                dp[s][e] = Math.min(cost, dp[s][e]);
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dp[i][k] != 0 && dp[k][j] != 0) {
                        if (isWay[i][j]) {
                            dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                        } else {
                            dp[i][j] = dp[i][k] + dp[k][j];
                            isWay[i][j] = true;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isWay[i][j] && i != j) {
                    sb.append(dp[i][j]).append(" ");
                } else {
                    sb.append(0).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
