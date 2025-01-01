package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek12919 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String S = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String T = st.nextToken();

        if (check(S, T)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    public static boolean check(String s, String t) {
        if (s.equals(t)) {
            return true;
        }

        if (s.length() > t.length()) {
            return false;
        }

        if (t.charAt(t.length() - 1) == 'A') {
            if (check(s, t.substring(0, t.length() - 1))) {
                return true;
            }
        }

        if (t.charAt(0) == 'B') {
            StringBuffer sb = new StringBuffer(t.substring(1));
            if (check(s, sb.reverse().toString())) {
                return true;
            }
        }

        return false;
    }
}
