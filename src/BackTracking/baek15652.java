package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 15652 N과 M (4) S3
 * 문제 풀이 방법 : 백트래킹
 */
public class baek15652 {
    static int N;
    static int M;
    //    static boolean[] isVisited;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
//        isVisited = new boolean[N + 1];

        dfs(0, 0);
        System.out.print(sb);
    }

    static void dfs(int depth, int back) {
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }

            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            // 이전 값보다 작으면 적용 x
            if (back <= i) {
//                isVisited[i] = true;
                arr[depth] = i;
                dfs(depth + 1, i);
//                isVisited[i] = false;
            }
        }
    }
}
