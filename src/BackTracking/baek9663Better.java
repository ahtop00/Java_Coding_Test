package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 9663 N-Queen G4
 * 풀이방법: 배열 3개 col, diag1, diag2를 통해 알고리즘 최적화
 * */
public class baek9663Better {

    static int N;
    static long total = 0;
    static boolean[] col;
    static boolean[] diag1;
    static boolean[] diag2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        col = new boolean[N];
        diag1 = new boolean[2* N];
        diag2 = new boolean[2* N];

        func(0);
        System.out.print(total);
    }

    public static void func(int y) {
        if (y == N) {
            total++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isNotSafe(i, y)) continue;
            col[i] = diag1[i + y] = diag2[i - y + (N - 1)] = true;
            func(y + 1);
            col[i] = diag1[i + y] = diag2[i - y + (N - 1)] = false;
        }
    }

    public static boolean isNotSafe(int r, int c) {
        return col[r] || diag1[r + c] || diag2[r - c + (N- 1)];
    }
}
