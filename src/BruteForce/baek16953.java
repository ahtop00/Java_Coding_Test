package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 16953 A -> B S2
 * 문제 풀이 방법 : 브루트포스
 */
public class baek16953 {
    static long A;
    static long B;
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        calculateNum(1, A);

        if (min == Long.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    public static void calculateNum(int count, long cnt) {
        if (cnt == B) {
            min = Math.min(min, count);
            return;
        }

        if (cnt > B) {
            return;
        }

        calculateNum(count + 1, (cnt * 10 + 1));
        calculateNum(count + 1, cnt * 2);
    }
}

