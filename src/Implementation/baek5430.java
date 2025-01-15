package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/** 5430 AC G5 */
public class baek5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            // 기본 세팅
            st = new StringTokenizer(br.readLine());
            String p = st.nextToken();

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            //선언시 ArrayDeque로 하지말고 Deque로 선언
            Deque<Integer> arr = new ArrayDeque<>();
            boolean isFront = true;
            boolean isError = false;

            String input = br.readLine();
            input = input.substring(1, input.length() - 1);

            st = new StringTokenizer(input , ",");

            while(st.hasMoreTokens()) {
                int i = Integer.parseInt(st.nextToken());
                arr.add(i);
            }
            
            // 커맨드 실행
            for (int i = 0;  i < p.length(); i++) {
                if (p.charAt(i) == 'R') {
                    isFront = !isFront;
                } else {
                    if (arr.isEmpty()) {
                        isError = true;
                        break;
                    }
                    if (isFront) {
                        arr.poll();
                    } else {
                        arr.removeLast();
                    }
                }
            }

            // 오류 및 리버스 확인
            if (isError) {
                System.out.println("error");
                continue;
            }

            //System.out을 여러번 써야되는 상황이면 StringBuilder 이용해서 출력값 생성
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (isFront) {
                while(!arr.isEmpty()) {
                    sb.append(arr.poll());
                    if (!arr.isEmpty()) {
                        sb.append(",");
                    }
                }
            } else {
                while(!arr.isEmpty()) {
                    sb.append(arr.pollLast());
                    if (!arr.isEmpty()) {
                        sb.append(",");
                    }
                }
            }
            sb.append("]");

            System.out.println(sb);
        }
    }
}
