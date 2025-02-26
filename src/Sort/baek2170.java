package Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/* 2170 선긋기 G5
 * 문제 풀이 방법 : 라인 스위핑 알고리즘
 */
public class baek2170 {
    static int N;
    static List<Line> lines;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        lines = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            long s = Long.parseLong(st.nextToken());
            long e = Long.parseLong(st.nextToken());
            lines.add(new Line(s, e));
        }

        lines.sort(Comparator.comparingLong((Line line) -> line.start)
                .thenComparingLong(line -> line.end));

        maxLengthCheck();
        System.out.print(result);
    }

    static class Line {
        long start;
        long end;

        Line(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }

    static void maxLengthCheck() {
        long startIdx = lines.get(0).start;
        long endIdx = lines.get(0).end;

        for (int i = 1; i < lines.size(); i++) {
            Line line = lines.get(i);

            if (line.start > endIdx) {
                result += (endIdx - startIdx);
                startIdx = line.start;
                endIdx = line.end;
            } else {
                endIdx = Math.max(endIdx, line.end);
            }
        }

        // 마지막으로 남은 선 길이 더하기
        result += (endIdx - startIdx);
    }
}
