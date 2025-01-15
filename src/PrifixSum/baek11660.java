package PrifixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 11660 구간 합 구하기 5 S1*/
public class baek11660 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];  //[x][y]

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 누적 합 구현
        for (int j = 0; j < N; j++) {
            int prefix = 0;
            for (int i = 0; i < N; i++) {
                arr[i][j] += prefix;
                prefix = arr[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            int result = 0;

            for (int row = y1; row <= y2; row++) {
                if (x1 == 0) {
                    result += arr[x2][row];
                } else {
                    result += (arr[x2][row] - arr[x1 - 1][row]);
                }
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }
}
