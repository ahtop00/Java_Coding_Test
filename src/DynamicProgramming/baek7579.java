package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 7579 앱 G3
 * 문제 풀이 방법: 백트래킹 X -> 가능은 하나, 너무나도 비효율적인 방법 2^N (20비효율, 30 이상부터 터짐...)
 * dp -> 배낭문제의 변형
 * 1차원 배열 dp로도 배낭을 만들 수 있다?!
 * 원래의 배낭문제 dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]] + memory[i]);
 * 해당 점화식은 이전 상태의 저장이 필요했기에 이렇게 두었다.
 * 하지만 해당 경우엔 굳이 "이전" 상태의 저장이 필요치 않음
 */
public class baek7579 {
    static int N;
    static int M;
    static int T = 0;
    static int[] dp;
    static int[] memory;
    static int[] cost;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memory = new int[N + 1];
        cost = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            T += cost[i];
        }

        dp = new int[T + 1];

        DP();
        System.out.print(result);
    }

    static void DP() {
        for (int i = 1; i <= N; i++) {
            for (int j = T; j >= cost[i]; j--) { //j >= cost[i]를 통해 ArrayIndexOutOfBoundsException 방지
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]);
                if (dp[j] >= M) {
                    result = Math.min(result, j);
                }
            }
        }
    }
}
