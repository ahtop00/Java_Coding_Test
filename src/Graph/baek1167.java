package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/** 1167 트리의 지름 G2*/
public class baek1167 {
    static int V;
    static List<List<Node>> matrix;
    static boolean[] visited;
    static int[] distanceFromX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        matrix = new ArrayList<>();
        visited = new boolean[V + 1];
        distanceFromX = new int[V + 1];

        for (int i = 0; i <= V; i++) {
            List<Node> list = new ArrayList<>();
            matrix.add(list);
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());

            int cnt = Integer.parseInt(st.nextToken());

            while(st.hasMoreTokens()) {
                int nxt = Integer.parseInt(st.nextToken());

                if (nxt == -1) {
                    break;
                }

                int distance = Integer.parseInt(st.nextToken());

                matrix.get(cnt).add(new Node(nxt, distance));
            }
        }

        BFS(1);

        int target = 0;
        int maxDistance = Integer.MIN_VALUE;
        for (int i = 1; i <= V; i++) {
            if (distanceFromX[i] > maxDistance) {
                target = i;
                maxDistance = distanceFromX[i];
            }
        }
        Arrays.fill(distanceFromX, 0);
        Arrays.fill(visited, false);

        BFS(target);

        maxDistance = Integer.MIN_VALUE;
        for (int i = 1; i <= V; i++) {
            if (distanceFromX[i] > maxDistance) {
                maxDistance = distanceFromX[i];
            }
        }

        System.out.println(maxDistance);
    }

    public static void BFS(int target) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(target);
        visited[target] = true;
        distanceFromX[target] = 0;

        while(!q.isEmpty()) {
            int cnt = q.poll();
            int cntDistance = distanceFromX[cnt];
            for (int i = 0; i < matrix.get(cnt).size(); i++) {
                Node nxtNode = matrix.get(cnt).get(i);
                if (!visited[nxtNode.nxt] && nxtNode.distance != 0) {
                    q.add(nxtNode.nxt);
                    distanceFromX[nxtNode.nxt] = cntDistance + nxtNode.distance;
                    visited[nxtNode.nxt] = true;
                }
            }
        }
    }

    public static class Node {
        int nxt;
        int distance;

        Node (int nxt, int distance) {
            this.nxt = nxt;
            this.distance = distance;
        }
    }
}
