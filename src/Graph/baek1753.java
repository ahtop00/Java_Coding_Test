package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1753 최단경로 G4
 * 풀이방법: 다익스트라 알고리즘
 */
public class baek1753 {

    static int[] distance;
    static boolean[] isVisited;
    static List<ArrayList<Node>> list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        isVisited = new boolean[V + 1];

        list = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Node(v, w));
        }

        dijkstra(K);
        output(V);

    }

    public static class Node {

        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()) {
            Node curNode = pq.poll();

            if(isVisited[curNode.idx]) {
                continue;
            }
            isVisited[curNode.idx] = true;

            for (Node nxtNode : list.get(curNode.idx)) {
                if (distance[nxtNode.idx] > distance[curNode.idx] + nxtNode.cost) {
                    distance[nxtNode.idx] = distance[curNode.idx] + nxtNode.cost;
                    pq.offer(new Node(nxtNode.idx, distance[nxtNode.idx]));
                }
            }
        }

    }

    public static void output(int V) {

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(distance[i]).append("\n");
            }
        }
        System.out.print(sb);
    }
}
