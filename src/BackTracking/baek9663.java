package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 9663 N-Queen G4*/
public class baek9663 {

    static int N;
    static long total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int[] visited = new int[N];
//        int[] arr = new int[N];

        func(0, visited);
        System.out.print(total);
    }

    public static void func(int cdx, int[] visited) {
        if (cdx == N) {
            total++;
            return;
        }

        for (int i = 0; i < N; i++) {
            visited[cdx] = i;

            if(checked(cdx, visited))
                func(cdx + 1, visited);
        }

    }

    public static boolean checked(int cdx, int[] visited) {
        for (int i = 0; i < cdx; i++) {
            if (visited[cdx] == visited[i] || cdx - i == Math.abs(visited[cdx] - visited[i]))
                return false;
        }
        return true;
    }
}
