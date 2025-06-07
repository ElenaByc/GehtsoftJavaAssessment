package evaluator;

import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

public class ExpressionEvaluator {
    // Map to define operator precedence (higher number = higher precedence)
    private static final Map<Character, Integer> PRECEDENCE = new HashMap<>();
    static {
        // Initialize operator precedences: * and / have higher precedence than + and -
        PRECEDENCE.put('+', 1);
        PRECEDENCE.put('-', 1);
        PRECEDENCE.put('*', 2);
        PRECEDENCE.put('/', 2);
    }

    /**
     * Evaluates a given arithmetic expression string and returns the calculated result.
     * The expression supports basic operations (+, -, *, /), parentheses for precedence,
     * and decimal numbers. It adheres to the standard mathematical
     * order of operations (PEMDAS/BODMAS) using a two-stack approach (Shunting-Yard algorithm concept).
     *
     * @param expression The arithmetic expression string to be evaluated.
     * @return The calculated double result of the expression.
     * @throws IllegalArgumentException if the expression is invalid (e.g., malformed,
     * contains unsupported characters, or has mismatched parentheses, insufficient operands).
     * @throws ArithmeticException      if a division by zero occurs during evaluation.
     */
    public double evaluate(String expression) {
        // Remove all spaces from the expression
        expression = expression.replaceAll("\\s+", "");

        // Check for empty string after removing spaces
        if (expression.isEmpty()) {
            // throw new IllegalArgumentException("Expression cannot be empty.");
            return 0;   // it was not specified in the project requirements,
                        // so I decided it is reasonable to return 0 in case of empty expression
        }

        // Stacks for numbers and operators. This is the core of the algorithm.
        Stack<Double> numbersStack = new Stack<>();
        Stack<Character> operatorsStack = new Stack<>();

        // Iterate through the expression character by character
        int len = expression.length();
        for (int i = 0; i < len; i++) {
            char c = expression.charAt(i);

            // If the character is a digit or a decimal point, parse the entire number
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                // Collect all digits and dots to form the complete number
                // .9 is the same as 0.9
                while (i < len && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                // Step back one, to not miss the next character in the next loop iteration
                i--;

                try {
                    numbersStack.push(Double.parseDouble(sb.toString()));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format in expression: '" + sb.toString() + "'", e);
                }
            }

            // If the character is an opening parenthesis, add it to the operatorsStack
            else if (c == '(') {
                operatorsStack.push(c);
            }
            // If the character is a closing parenthesis
            else if (c == ')') {
                // Perform operations from the operator stack until an opening parenthesis is found
                while (!operatorsStack.isEmpty() && operatorsStack.peek() != '(') {
                    applyOperation(numbersStack, operatorsStack.pop());
                }
                // If the stack is empty or the top is not an opening parenthesis, then parentheses are mismatched
                if (operatorsStack.isEmpty() || operatorsStack.pop() != '(') {
                    throw new IllegalArgumentException("Mismatched parentheses: missing opening parenthesis for a closing one.");
                }
            }
            // If the character is an operator (+, -, *, /)
            else if (PRECEDENCE.containsKey(c)) {
                // While the operator stack is not empty, and the top is an operator (not a parenthesis),
                // and the current operator has precedence less than or equal to the top of the stack,
                // apply the operation from the stack.
                while (!operatorsStack.isEmpty() && PRECEDENCE.containsKey(operatorsStack.peek()) &&
                        PRECEDENCE.get(operatorsStack.peek()) >= PRECEDENCE.get(c)) {
                    applyOperation(numbersStack, operatorsStack.pop());
                }
                // Then push the current operator onto the operator stack
                operatorsStack.push(c);
            }
            // If the character is not a digit, dot, parenthesis, or known operator, throw error
            else {
                throw new IllegalArgumentException("Invalid character in expression: '" + c + "'");
            }
        }

        // After processing the entire expression, apply all remaining operations in the operator stack
        while (!operatorsStack.isEmpty()) {
            // If opening parentheses remain, it means there's no corresponding closing one
            if (operatorsStack.peek() == '(') {
                throw new IllegalArgumentException("Mismatched parentheses: missing closing parenthesis.");
            }
            applyOperation(numbersStack, operatorsStack.pop());
        }

        // At the end, the numbers stack should contain exactly one element - the final result
        if (numbersStack.size() != 1) {
            // This can happen with an invalid expression format, e.g., "2 +"
            throw new IllegalArgumentException("Invalid expression format. Check operators and operands.");
        }
        return numbersStack.pop(); // Pop and return the result
    }


    // Helper method to apply operations
    private void applyOperation(Stack<Double> numbersStack, char operator) {
        // A binary operation (like +, -, *, /) requires at least two numbers on the stack
        if (numbersStack.size() < 2) {
            throw new IllegalArgumentException("Invalid expression: insufficient operands for operator " + operator);
        }

        // Pop the second and first numbers (order matters for subtraction/division)
        double num2 = numbersStack.pop();
        double num1 = numbersStack.pop();

        // Perform the operation and push the result back onto the numbers stack
        switch (operator) {
            case '+':
                numbersStack.push(num1 + num2);
                break;
            case '-':
                numbersStack.push(num1 - num2);
                break;
            case '*':
                numbersStack.push(num1 * num2);
                break;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                numbersStack.push(num1 / num2);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: '" + operator + "'.");
        }
    }
}
