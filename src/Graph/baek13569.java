package Graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Struct;
import java.util.*;

/*13569 숨바꼭질3 G5*/
public class baek13569 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        /*최단거리 탐색 알고리즘(그래프기반)*/
        int[] visited = new int[100001];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node n = new Node(N, 0);
        pq.offer(n);

        int min = Integer.MAX_VALUE;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            visited[current.x] = 1;
            if (current.x == K) {
                min = Math.min(min, current.time);
            }

            if (current.x * 2 <= 100000 && visited[current.x * 2] == 0) {
                pq.offer(new Node(current.x * 2, current.time));
            }
            if (current.x + 1 <= 100000 && visited[current.x + 1] == 0) {
                pq.offer(new Node(current.x + 1, current.time + 1));
            }
            if (current.x - 1 >= 0 && visited[current.x - 1] == 0) {
                pq.offer(new Node(current.x - 1, current.time + 1));
            }
        }
        System.out.println(min);
    }
    public static class Node implements Comparable<Node> {
        int x;
        int time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }
}

