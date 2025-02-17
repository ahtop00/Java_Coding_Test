package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 11444 피보나치 수 6 G2
 * 문제 풀이 방법 : 분할정복, 선형대수를 통한 점화식 세우기...!
 */
public class baek11444 {
    static final long mod = 1000000007;
    static long[][] arr = {{1, 1}, {1, 0}};
    static long[][] result = {{1, 0}, {0, 1}};
    static long n ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Long.parseLong(br.readLine());

        dq();

        System.out.print(result[1][0]);
    }

    static void dq() {
        while (n != 0) {
            if (n % 2 == 1) {
                result = calculate(arr, result);
            }

            arr = calculate(arr, arr);
            n /= 2;
        }
    }

    static long[][] calculate(long[][] a, long[][] b) {
        long[][] temp = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    temp[i][j] += (a[i][k] * b[k][j]);
                }
                temp[i][j] %= mod;
            }
        }

        return temp;
    }
}
