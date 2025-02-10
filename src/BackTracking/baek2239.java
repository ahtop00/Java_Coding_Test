package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 2239 스토쿠 G4
 * 문제 풀이 방법 : 백트래킹
 */
public class baek2239 {
    static int[][] matrix = new int[9][9];
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String s = br.readLine();

            int j = 0;
            for (char c : s.toCharArray()) {
                matrix[i][j] = c - '0';
                j++;
            }
        }

        backTracking(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(matrix[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void backTracking(int index) {
        if (index == 81) {
            flag = true;
            return;
        }

        int y = index / 9;
        int x = index % 9;

        if (matrix[y][x] != 0) {
            backTracking(index + 1);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (!isValid(x, y, i)) continue;

                matrix[y][x] = i;
                backTracking(index + 1);

                if (flag) return;

                matrix[y][x] = 0;
            }
        }
    }

    static boolean isValid(int x, int y, int check) {
        //1. 가로줄 확인, 세로줄 확인
        for (int j = 0; j < 9; j++) {
            if (matrix[y][j] == check) {
                return false;
            }

            if (matrix[j][x] == check) {
                return false;
            }
        }

        //2. 박스안에 해당 수가 있는지 확인
        int checkX = (x / 3) * 3;
        int checkY = (y / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[checkY + i][checkX + j] == check) {
                    return false;
                }
            }
        }

        return true;
    }
}

