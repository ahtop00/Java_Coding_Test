package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 2096 내려가기 G5
 * 문제 풀이 방법 : dp 누적합 + 슬라이딩 윈도우로 dp 크기 압축
 */
public class baek2096 {
    static int N;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        matrix = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
            matrix[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.print(maxArr() + " " + minArr());
    }

    static int maxArr() {
        dp = new int[2][3];
        int prevIdx = 0, curIdx = 1;
        int max = 0;

        for (int i = 1; i <= N; i++) {
            dp[curIdx][0] = Math.max(dp[prevIdx][0], dp[prevIdx][1]) + matrix[i][0];
            dp[curIdx][1] = Math.max(dp[prevIdx][0], Math.max(dp[prevIdx][1], dp[prevIdx][2])) + matrix[i][1];
            dp[curIdx][2] = Math.max(dp[prevIdx][1], dp[prevIdx][2]) + matrix[i][2];

            curIdx = curIdx ^ prevIdx;
            prevIdx = curIdx ^ prevIdx;
            curIdx = curIdx ^ prevIdx;
        }

        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dp[prevIdx][i]);
        }

        return max;
    }

    static int minArr() {
        dp = new int[2][3];
        int prevIdx = 0, curIdx = 1;
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            dp[curIdx][0] = Math.min(dp[prevIdx][0], dp[prevIdx][1]) + matrix[i][0];
            dp[curIdx][1] = Math.min(dp[prevIdx][0], Math.min(dp[prevIdx][1], dp[prevIdx][2])) + matrix[i][1];
            dp[curIdx][2] = Math.min(dp[prevIdx][1], dp[prevIdx][2]) + matrix[i][2];

            curIdx = curIdx ^ prevIdx;
            prevIdx = curIdx ^ prevIdx;
            curIdx = curIdx ^ prevIdx;
        }

        for (int i = 0; i < 3; i++) {
            min = Math.min(min, dp[prevIdx][i]);
        }

        return min;
    }
}
