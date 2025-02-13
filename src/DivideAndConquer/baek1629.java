package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 1629 곱셈 S1
 * 문제 풀이 방법 : 분할정복, 모듈러 공식 활용
 */
public class baek1629 {
    static long a;
    static long b;
    static long c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        System.out.print(dq(a, b));
    }

    static long dq(long base, long exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base % c;
        }

        long half = dq(base, exponent / 2) % c;
        long result = half * half % c;

        if (exponent % 2 == 0) {
            return result;
        } else {
            return (result * base) % c;
        }
    }
}
