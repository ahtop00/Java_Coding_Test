package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 *  7662 이중 우선 순위 큐 G4
 *  문제 풀이 방식: 두개의 힙을 이용하여 PriorityDeque 구현
 *      Lazy Deletion 기법을 사용하여 효율적으로 삭제 연산을 처리하며, 중복 숫자도 정상적으로 처리
 */
public class baek7662 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int Q = Integer.parseInt(br.readLine());

            PriorityQueue<Long> minHeap = new PriorityQueue<>(); // 최소 힙
            PriorityQueue<Long> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // 최대 힙
            Map<Long, Integer> countMap = new HashMap<>(); // 각 숫자의 삽입/삭제 상태를 기록

            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();

                if (command.equals("I")) {
                    long num = Long.parseLong(st.nextToken());
                    minHeap.offer(num);
                    maxHeap.offer(num);
                    countMap.put(num, countMap.getOrDefault(num, 0) + 1); // 삽입 횟수 증가
                } else {
                    int check = Integer.parseInt(st.nextToken());

                    if (check == 1) {
                        removeValid(maxHeap, countMap);
                        if (!maxHeap.isEmpty()) {
                            long max = maxHeap.poll();
                            countMap.put(max, countMap.get(max) - 1);
                        }
                    } else {
                        // 최소값 삭제
                        removeValid(minHeap, countMap);
                        if (!minHeap.isEmpty()) {
                            long min = minHeap.poll();
                            countMap.put(min, countMap.get(min) - 1);
                        }
                    }
                }
            }

            // 결과 출력
            removeValid(maxHeap, countMap); // 최대 힙 정리
            removeValid(minHeap, countMap); // 최소 힙 정리

            if (maxHeap.isEmpty() || minHeap.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(maxHeap.peek() + " " + minHeap.peek());
            }
        }
    }

    // 유효하지 않은 요소를 제거하는 메서드
    private static void removeValid(PriorityQueue<Long> heap, Map<Long, Integer> countMap) {
        while (!heap.isEmpty() && countMap.getOrDefault(heap.peek(), 0) == 0) {
            heap.poll(); // 유효하지 않은 요소 제거
        }
    }
}
