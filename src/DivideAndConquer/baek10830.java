package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 10830 행렬 제곱 G4
 * 문제 풀이 방법 : 분할정복
 */
public class baek10830 {
    static int N;
    static long B;
    static final long mod = 1000;
    static long[][] arr;
    static long[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        arr = new long[N][N];
        result = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Long.parseLong(st.nextToken());
            }
            result[i][i] = 1L;
        }

        getResult();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void getResult() {
        while (B != 0) {
            if (B % 2 == 1) {
                result = calculate(arr, result);
            }

            arr = calculate(arr, arr);
            B /= 2;
        }
    }

    static long[][] calculate(long[][] a, long[][] b) {
        long[][] temp = new long[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    temp[i][j] += (a[i][k] * b[k][j]);
                }
                temp[i][j] %= mod;
            }
        }

        return temp;
    }
}
