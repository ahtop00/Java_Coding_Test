import jdk.jshell.execution.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baek2512 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        long total = 0;
        int[] list = new int[N];
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
            total += list[i];
        }
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        Arrays.sort(list);


        if (M >= total) {
            System.out.println(list[N - 1]);
        }
        else {
            //이분 탐색 실행
            int start = 0, end = list[N - 1];
            int result = 0;

            while (start <= end) {
                long sum = 0;
                int mid = (start + end) / 2;
                for (int i = 0; i < N; i++) {
                    sum += Math.min(list[i], mid);
                }
                if (M >= sum) {
                    result = mid;
                    start = mid + 1;
                }
                else {
                    end = mid - 1;
                }
            }

            System.out.println(result);
        }
    }


}