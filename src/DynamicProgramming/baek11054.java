package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 11054 가장 긴 바이토닉 부분 수열 G4
 * 풀이방법: DP 테이블 두개 이용
 * https://0x-mori.tistory.com/1
 */
public class baek11054 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        int[] dpIncrease = new int[N];
        Arrays.fill(dpIncrease, 1);

        int[] dpDecrease = new int[N];
        Arrays.fill(dpDecrease, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //dp: 증가하는 수열 처리
        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && max < dpIncrease[j]) {
                    max = dpIncrease[j];
                }
            }
            dpIncrease[i] += max;
        }

        //dp: 감소하는 수열 처리
        for (int i = N - 1; i >= 0; i--) {
            int max = 0;
            for (int j = i; j < N; j++){
                if (arr[i] > arr[j] && max < dpDecrease[j]) {
                    max = dpDecrease[j];
                }
            }
            dpDecrease[i] += max;
        }

        //dp 두개를 합쳐서 maximum 값의 자기 자신 한개를 뺀 것이 정답
        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(dpIncrease[i] + dpDecrease[i], result);
        }
        result--;

        System.out.println(result);
    }
}
