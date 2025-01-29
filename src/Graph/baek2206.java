package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/* 2206 벽 부수고 이동하기 G3
 * 문제 풀이 방법 : bfs + 추가적인 visited 배열
 */
public class baek2206 {
    static int N, M;
    static boolean[][] matrix;
    static boolean[][][] isVisited;

    static int[] dn = {0, 0, 1, -1};
    static int[] dm = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new boolean[N][M];
        isVisited = new boolean[N][M][2]; // isVisited[Node][M][0] = 벽을 안부수고 방문 [1] 부수고 방문

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] input = st.nextToken().toCharArray();

            for (int j = 0; j < M; j++) {
                int isBlock = input[j] - '0';

                matrix[i][j] = isBlock == 0;
            }
        }

        System.out.println(bfs());
    }

    static class Node {
        int n;
        int m;
        int dist;
        int wallBreak;

        Node(int n, int m, int dist, int wallBreak) {
            this.n = n;
            this.m = m;
            this.dist = dist;
            this.wallBreak = wallBreak;
        }
    }

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 1, 0));
        isVisited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cnt = q.poll();

            if (cnt.n == N - 1 && cnt.m == M - 1) {
                return cnt.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cnt.n + dn[i];
                int ny = cnt.m + dm[i];
                int nxtDist = cnt.dist + 1;

                if (!(nx >= 0 && nx < N && ny >= 0 && ny < M)) {
                    continue;
                }

                //만약 막힌벽이 아니면 matrix값 0
                if (matrix[nx][ny]) {
                    //부신 벽이 없으면
                    if (cnt.wallBreak == 0 && !isVisited[nx][ny][0]) {
                        q.offer(new Node(nx, ny, nxtDist, 0));
                        isVisited[nx][ny][0] = true;
                    } else if (cnt.wallBreak == 1 && !isVisited[nx][ny][1]) {
                        q.offer(new Node(nx, ny, nxtDist, 1));
                        isVisited[nx][ny][1] = true;
                    }
                } else {
                    //막힌벽이면 벽을 부술 기회가 있어야됨
                    if (cnt.wallBreak == 0 && !isVisited[nx][ny][1]) {
                        q.offer(new Node(nx, ny, nxtDist, 1));
                        isVisited[nx][ny][1] = true;
                    }
                    //또 못부숨
                }

            }
        }

        return -1;
    }
}
