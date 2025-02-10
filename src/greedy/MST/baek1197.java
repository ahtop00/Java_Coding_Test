package greedy.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 1197 최소 스패닝 트리
 * 문제 풀이 방법 : Prim 알고리즘 (밀집 그래프 상황)
 */
public class baek1197 {
    static int V;
    static int E;
    static ArrayList<ArrayList<Node>> matrix = new ArrayList<>();
    static boolean[] isVisited;
    static long total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= V; i++) {
            matrix.add(new ArrayList<>());
        }

        isVisited = new boolean[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            matrix.get(s).add(new Node(e, cost));
            matrix.get(e).add(new Node(s, cost)); //필수
        }

        prim();
        System.out.print(total);
    }

    static void prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(node -> node.cost));
        pq.offer(new Node(1, 0));

        int edge = 0;
        while (edge <= V - 1 && !pq.isEmpty()) {
            Node cntNode = pq.poll();

            if (isVisited[cntNode.num]) {
                continue;
            }
            isVisited[cntNode.num] = true;
            total += cntNode.cost;

            for (Node nxtNode : matrix.get(cntNode.num)) {
                if (!isVisited[nxtNode.num]) {
                    pq.offer(nxtNode);
                }
            }

            edge++;
        }
    }

    static class Node {
        long cost;
        int num;

        Node(int num, long cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
