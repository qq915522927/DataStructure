import java.util.Stack;
class StackPractice {
    /**
     * https://leetcode-cn.com/problems/valid-parentheses/
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c=='(' || c=='['||c =='{'){
                stack.push(c);
            } else {
                if(stack.isEmpty()){
                    return false;
                }
                Character top = stack.peek();
                if(
                        (top == '(' && c == ')') ||
                                (top == '{' && c == '}') ||
                                (top == '[' && c == ']')
                ){
                    stack.pop();
                } else {
                    return false;
                }
            }

        }
        return stack.isEmpty();

    }

    public static void main(String[] args) {
        String test = "({()})";
        String test2 = "({()}";
        StackPractice sol = new StackPractice();
        System.out.println(sol.isValid(test));
        System.out.println(sol.isValid(test2));
    }
}