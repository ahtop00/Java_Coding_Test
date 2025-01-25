package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 11053 가장 긴 증가하는 부분 수열 S2
 * 문제풀이: dp
 * 시간 복잡도: dp이여도 O(N^2)
 */
public class baek11053 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[i] += max;
        }

        //중간에 최대값 존재 가능
        //반례: 60 10 20 70 80 90 100 30 40 50 -> 100에서 최대값.
        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }
}
