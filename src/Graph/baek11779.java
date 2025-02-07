package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 11779 최소비용 구하기 2 G3
 * 문제 풀이 방법 : 다익스트라 알고리즘
 */
public class baek11779 {
    static int N;
    static int M;
    static List<ArrayList<Node>> matrix;
    static int[] prevIdx;
    static int[] dist;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        matrix = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            matrix.add(new ArrayList<>());
        }

        prevIdx = new int[N + 1];
        dist = new int[N + 1];
        isVisited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            matrix.get(s).add(new Node(e, dist));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        prevIdx[start] = -1;
        dijkstra(start);
        printResult(end);
    }

    static class Node {
        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static void dijkstra(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(isVisited, false);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node cntNode = pq.poll();

            if (isVisited[cntNode.idx]) {
                continue;
            }
            isVisited[cntNode.idx] = true;

            for (Node nxtNode : matrix.get(cntNode.idx)) {
                if (dist[nxtNode.idx] > dist[cntNode.idx] + nxtNode.cost && !isVisited[nxtNode.idx]) {
                    dist[nxtNode.idx] = dist[cntNode.idx] + nxtNode.cost;
                    prevIdx[nxtNode.idx] = cntNode.idx;
                    pq.offer(new Node(nxtNode.idx, dist[nxtNode.idx]));
                }
            }
        }
    }

    static void printResult(int end) {
        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        sb.append(dist[end]).append("\n");

        int idx = end;
        while (idx != -1) {
            deque.offer(idx);
            idx = prevIdx[idx];
        }

        sb.append(deque.size()).append("\n");
        while (!deque.isEmpty()) {
            idx = deque.pollLast();
            sb.append(idx).append(" ");
        }

        System.out.print(sb);
    }
}
