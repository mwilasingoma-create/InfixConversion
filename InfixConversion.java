import java.util.Stack;
import java.util.Scanner;

public class InfixConversion {

    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                result += ch;
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() &&
                        precedence(stack.peek()) >= precedence(ch)) {
                    result += stack.pop();
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    static String infixToPrefix(String exp) {

        exp = reverse(exp);

        char[] chars = exp.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(')
                chars[i] = ')';
            else if (chars[i] == ')')
                chars[i] = '(';
        }

        exp = new String(chars);

        String postfix = infixToPostfix(exp);

        return reverse(postfix);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter infix expression: ");
        String infix = input.nextLine();

        System.out.println("Postfix Expression: " + infixToPostfix(infix));
        System.out.println("Prefix Expression: " + infixToPrefix(infix));
    }
}