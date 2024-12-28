package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//1205 S4: 등수 구하기
public class baek1205 {
    public static void main(String[] args) throws IOException {
        int N, score, P;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        score = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int[] list = new int[N];
        if (N != 0) {
            st = new StringTokenizer(br.readLine());
            // 값 pq에 넣기 (내림차순 정렬임)
            for (int i = 0; i < N; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }
        }

        //pq있고 P가 점수의 개수
        int rank = 1;
        boolean inRank = (N == 0);

        if (!inRank) {
            rank = upperBoundDescending(list, score, N) + 1;

            if (N < P) {
                inRank = true;
            } else {
                if (rank <= P && score > list[N - 1]) {
                    inRank = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (inRank) {
            sb.append(rank);
        } else {
            sb.append(-1);
        }

        System.out.println(sb.toString());
    }

    public static int upperBoundDescending(int[] list, int target, int N) {
        int left = 0, right = N;
        while(left < right) {
            int mid = (left + right) / 2;

            if (list[mid] > target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}