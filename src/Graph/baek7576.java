package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*7576 토마토 G5*/
public class baek7576 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[N][M];
        boolean[][] checked = new boolean[N][M];
        int maxValue = Integer.MIN_VALUE;
        int notRipeTomato = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(n -> n.day));

        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());

                if (matrix[i][j] == 1) {
                    pq.offer(new Node(i, j, 0));
                    checked[i][j] = true;
                } else if (matrix[i][j] == 0) {
                    notRipeTomato++;
                }
            }
        }

        if (notRipeTomato == 0) {
            System.out.println(0);
        } else {
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            while (!pq.isEmpty()) {
                Node node = pq.poll();
                if (maxValue < node.day) {
                    maxValue = node.day;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + node.n;
                    int ny = dy[i] + node.m;

                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && !checked[nx][ny] && matrix[nx][ny] != -1) {
                        pq.offer(new Node(nx, ny, node.day + 1));
                        checked[nx][ny] = true;
                        notRipeTomato--;
                    }
                }
            }

            if (notRipeTomato == 0) {
                System.out.println(maxValue);
            } else {
                System.out.println(-1);
            }
        }
    }

    public static class Node {
        int n;
        int m;
        int day;

        Node(int n, int m, int day) {
            this.n = n;
            this.m = m;
            this.day = day;
        }
    }
}
