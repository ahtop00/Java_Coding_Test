package Sort.TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PrerequisiteCourse {
    public static List<Integer> topologySort(List<List<Integer>> matrix, int[] inDegree, int[] need) {
        Queue<Integer> queue = new LinkedList<>();

        //초기세팅: 진입 차수가 0인 노드 큐에 삽입한다.
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                queue.add(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : matrix.get(node)) {
                inDegree[neighbor]--;
                //최대값 갱신
                need[neighbor] = Math.max(need[neighbor], need[node] + 1);

                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return Arrays.asList(Arrays.stream(need).boxed().toArray(Integer[]::new));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());


            List<List<Integer>> matrix = new ArrayList<>();
            for (int i = 0; i <= K; i++) {
                matrix.add(new ArrayList<>());
            }

            int[] inDegree = new int[K + 1];
            int[] need = new int[K + 1];
            Arrays.fill(need, 1);

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                matrix.get(a).add(b);
                inDegree[b]++;
            }

            int Q = Integer.parseInt(br.readLine());
            int[] wanted = new int[Q];
            for (int i = 0; i < Q; i++) {
                wanted[i] = Integer.parseInt(br.readLine());
            }

            topologySort(matrix, inDegree, need);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Q; i++) {
                if (need[wanted[i]] <= N) {
                    sb.append("True\n");
                }
                else {
                    sb.append("False\n");
                }
            }
            System.out.print(sb);
        }
        return;
    }
}
