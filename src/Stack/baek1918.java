package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/* 1918 후위 표기식 G2
 * 문제 풀이 방법 : 트리가 아닌 스택으로 푸는 문제
 */
public class baek1918 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        String string = br.readLine();

        char[] charArr = string.toCharArray();

        for (char c : charArr) {
            if (!checkOperator(c)) {
                sb.append(c);
            } else {
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    while(!stack.isEmpty() && !stack.peek().equals('(')) {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                } else if (c == '*' || c == '/') {
                    // *, / 도 같은 우선순위의 연산자가 맨위에 없을 때 까지 빼주어야함! (매우중요!!!)
                    while(!stack.isEmpty() && (stack.peek().equals('*') || stack.peek().equals('/'))) {
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                } else { // +, -는 계산순서를 생각해야됨
                    while (!stack.isEmpty() && !stack.peek().equals('(')) {
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                }
            }
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.print(sb);
    }

    static boolean checkOperator(char c) {
        return (c == '+') || (c == '-') || (c == '*') || (c == '/') || (c == '(') || (c == ')');
    }
}
