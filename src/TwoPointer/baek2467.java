package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 2467 용액 G5
 * 문제 풀이 방법 : 투포인터
 */
public class baek2467 {
    static int total = Integer.MAX_VALUE;
    static int high;
    static int low;
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = N - 1;
        while (left < right) {
            int temp = arr[left] + arr[right];

            if (temp == 0) {
                low = arr[left];
                high = arr[right];
                break;
            }

            if (Math.abs(temp) < Math.abs(total)) {
                low = arr[left];
                high = arr[right];
                total = temp;
            }

            if (temp >= 0) {
                right--;
            } else {
                left++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(low).append(" ").append(high);
        System.out.print(sb);
    }
}
