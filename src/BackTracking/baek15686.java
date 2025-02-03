package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 15686 치킨 배달 G5
 * 문제 풀이 방법 : 백트래킹
 */
public class baek15686 {
    static int N;
    static int M;
    static int result = Integer.MAX_VALUE;

    static List<Node> chickHouse;
    static List<Node> house;
    static int arrSize = 0;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chickHouse = new ArrayList<>();
        house = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    chickHouse.add(new Node(i , j));
                } else if (num == 1) {
                    house.add(new Node(i, j));
                }
            }
        }

        arrSize = chickHouse.size();
        isVisited = new boolean[chickHouse.size()];

        backTracking(0, 0);
        System.out.println(result);

    }

    public static void backTracking(int count, int backIdx) {
        if (count == M) {
            // 거리 확인
            int[] arrIdx = new int[M];
            int k = 0;
            for (int j = 0; j < arrSize; j++) {
                if (isVisited[j]) {
                    arrIdx[k] = j;
                    k++;
                }
            }

            int total = getTotal(arrIdx);

            result = Math.min(total, result);
            return;
        }

        for (int i = 0; i < arrSize; i++) {
            if (!isVisited[i] && backIdx <= i) {
                isVisited[i] = true;
                backTracking(count + 1, i);
                isVisited[i] = false;
            }
        }
    }

    public static int getTotal(int[] arrIdx) {
        int total = 0;
        for (Node node : house) {
            int dist = Integer.MAX_VALUE;
            for (int idx : arrIdx) {
                int temp = Math.min(dist, Math.abs(node.n - chickHouse.get(idx).n)
                        + Math.abs(node.m - chickHouse.get(idx).m));

                dist = Math.min(temp, dist);
            }
            total += dist;

            //total이 result보다 크면 이미 의미없음
            if (result <= total) {
                return total;
            }
        }
        return total;
    }

    public static class Node {
        int n;
        int m;

        Node(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
