package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
*   14500 테트로미노 G4
*   문제 풀이 방식: 브루트포스 구현
*   유의사항: T블럭을 제외하고 dfs + 백트레킹으로도 접근 가능하다..
*       정사각형이 많아진다면 이 방법이 훨 유리할듯
*/
public class baek14500 {
    static int[][] arr;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result = Math.max(IBlock(i, j), result);
                result = Math.max(TLBlock(i, j), result);
                result = Math.max(SBlock(i, j), result);
                result = Math.max(OBlock(i, j), result);
            }
        }

        System.out.println(result);
    }

    static int IBlock(int n, int m) {

        int max = -1;

        if (n + 3 < N) {
            int temp = 0;
            for (int i = 0; i < 4; i++) {
                temp += arr[n + i][m];
            }
            max = Math.max(max, temp);
        }

        if (m + 3 < M) {
            int temp = 0;
            for (int i = 0; i < 4; i++) {
                temp += arr[n][m + i];
            }
            max = Math.max(max, temp);
        }

        return max;
    }

    static int SBlock(int n, int m) {

        int max = -1;

        //세로 s
        if (n + 2 < N) {
            int temp = arr[n][m] + arr[n + 1][m];

            if (m - 1 >= 0) {
                max = Math.max(max, temp + arr[n + 1][m - 1] + arr[n + 2][m - 1]);
            }

            if (m + 1 < M) {
                max = Math.max(max, temp + arr[n + 1][m + 1] + arr[n + 2][m + 1]);
            }
        }

        if (m + 2 < M) {
            int temp = arr[n][m] + arr[n][m + 1];

            if (n - 1 >= 0) {
                max = Math.max(max, temp + arr[n - 1][m + 1] + arr[n - 1][m + 2]);
            }

            if (n + 1 < N) {
                max = Math.max(max, temp + arr[n + 1][m + 1] + arr[n + 1][m + 2]);
            }
        }

        return max;
    }

    static int OBlock(int n, int m) {

        int max = -1;

        if (n + 1 < N && m + 1 < M) {
            max = arr[n][m] + arr[n + 1][m] + arr[n][m + 1] + arr[n + 1][m + 1];
        }

        return max;
    }

    static int TLBlock(int n, int m) {

        int max = -1;

        //3개 블럭 세로
        if (n + 2 < N) {
            int temp = 0;

            for (int i = 0; i < 3; i++) {
                temp += arr[n + i][m];
            }

            if (m - 1 >= 0) {
                max = Math.max(max, temp + arr[n + 1][m - 1]); // T블럭

                max = Math.max(max, temp + arr[n][m - 1]); // L블럭
                max = Math.max(max, temp + arr[n + 2][m - 1]);
            }

            if (m + 1 < M) {
                max = Math.max(max, temp + arr[n + 1][m + 1]);

                max = Math.max(max, temp + arr[n][m + 1]);
                max = Math.max(max, temp + arr[n + 2][m + 1]);
            }
        }

        //3개 블럭 가로
        if (m + 2 < M) {
            int temp = 0;

            for (int i = 0; i < 3; i++) {
                temp += arr[n][m + i];
            }

            if (n - 1 >= 0) {
                max = Math.max(max, temp + arr[n - 1][m + 1]);

                max = Math.max(max, temp + arr[n - 1][m]);
                max = Math.max(max, temp + arr[n - 1][m + 2]);
            }

            if (n + 1 < N) {
                max = Math.max(max, temp + arr[n + 1][m + 1]);

                max = Math.max(max, temp + arr[n + 1][m]);
                max = Math.max(max, temp + arr[n + 1][m + 2]);
            }
        }

        return max;
    }
}
