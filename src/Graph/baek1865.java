package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/* 1865 웜홀 G3
 * 문제 풀이 방법 : 벨만-포드 알고리즘 + 모든 노드를 시작점으로 확인해야됨
 * 모든 노드를 시작점으로 해야되는 이유 : 모든 노드가 연결되었다는 명확한 문장이 없음
 */
public class baek1865 {
    static List<ArrayList<Edge>> list;
    static int[] dist;
    static int N, M, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                int t = Integer.parseInt(st.nextToken());

                list.get(s).add(new Edge(e, t));
                list.get(e).add(new Edge(s, t));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                int t = Integer.parseInt(st.nextToken());

                list.get(s).add(new Edge(e, -t));
            }

            boolean cycleCheck = false;
            for (int i = 0; i < N; i++) {
                if (bellmanFord(i)) {
                    sb.append("YES\n");
                    cycleCheck = true;
                    break;
                }
            }

            if (!cycleCheck) {
                sb.append("NO\n");
            }
        }

        System.out.println(sb);
    }

    public static class Edge {
        int end;
        int cost;

        Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    public static boolean bellmanFord(int start) {
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        boolean update = false;

        for (int i = 0; i < N - 1; i++) {
            update = false;

            for (int j = 0; j < N; j++) {
                for (Edge edge : list.get(j)) {
                    if (dist[j] != Integer.MAX_VALUE && dist[edge.end] > dist[j] + edge.cost) {
                        dist[edge.end] = dist[j] + edge.cost;
                        update = true;
                    }
                }
            }

            if (!update) {
                break;
            }
        }

        //음수 사이클 확인
        if (update) {
            for (int i = 0; i < N; i++) {
                for (Edge edge : list.get(i)) {
                    if (dist[i] != Integer.MAX_VALUE && dist[edge.end] > dist[i] + edge.cost) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
