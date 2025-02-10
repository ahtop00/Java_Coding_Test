package greedy.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 1197 최소 스패닝 트리 G4
 * 문제 풀이 방법 : Kruskal 알고리즘
 */
public class baek1197withKruskal {
    static int V;
    static int E;
    static int[] parent;
    static long total = 0;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(edge -> edge.cost));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            pq.offer(new Edge(s, e, cost));
        }

        Kruskal();
        System.out.print(total);
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int rootA = parent[a];
        int rootB = parent[b];

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

    static void Kruskal() {
        int edgeCount = 0;

        while (!pq.isEmpty() && edgeCount <= V - 1) {
            Edge edge = pq.poll();
            int u = edge.u, v = edge.v;

            if (find(u) != find(v)) {
                union(u, v);
                edgeCount++;
                total += edge.cost;
            }
        }
    }

    static class Edge {
        int u,v;
        long cost;

        Edge(int u, int v, long cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }
}
