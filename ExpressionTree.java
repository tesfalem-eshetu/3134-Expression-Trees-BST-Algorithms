import java.util.Stack;

public class ExpressionTree implements ExpressionTreeInterface {

    // Inner class to represent nodes in the expression tree
    private static class ExpNode {
        char operator;
        int operand;
        ExpNode left;
        ExpNode right;
    }

    ExpNode root; // The root node of the expression tree
    // A stack to help build the expression tree
    Stack<ExpNode> stack = new Stack<>(); 

    // Constructor that takes an expression string and converts 
    //it into an expression tree
    public ExpressionTree(String expression) {
        // Split the expression into tokens (operators and operands)
        String[] tokens = expression.split("\\s+");

        // Iterate through the tokens
        for (String token : tokens) {
            if (isOperator(token)) { // If the token is an operator
                ExpNode operatorNode = new ExpNode();
// Create a new operator node with the current token as its operator
                operatorNode.operator = token.charAt(0); 
                // Pop the right child node from the stack
                operatorNode.right = stack.pop();
                // Pop the left child node from the stack 
                operatorNode.left = stack.pop(); 
                // Push the new operator node back onto the stack
                stack.push(operatorNode); 
                // If the token is a number
            } else if (isNumber(token)) { 
                int value = Integer.parseInt(token);
                ExpNode numberNode = new ExpNode();
// Create a new operand node with the current token as its operand
                numberNode.operand = value; 
                // Push the new operand node onto the stack
                stack.push(numberNode); 
            }
        }

        root = stack.pop(); 
// The last node in the stack is the root node of the expression tree
    }

    // Recursive method to evaluate the expression tree
    public int eval(){
        return eval(root);
    }
    private int eval(ExpNode t){
        if(t.left == null || t.right == null){ 
            // If the current node is a leaf node (operand)
            return t.operand; // Return its value
        }
// Evaluate the left and right subtrees and apply the 
//operator at the current node to the results
        int leftEval = eval(t.left);
        int rightEval = eval(t.right);
        return applyOperator(t, leftEval, rightEval);
    }

// Recursive method to generate the postfix 
//notation of the expression tree
    public String postfix() {
        StringBuilder sb = new StringBuilder();
        postfix(root, sb);
        return sb.toString();
    }
    private void postfix(ExpNode t, StringBuilder sb) {
        if (t == null) {
            return;
        }
        postfix(t.left, sb);
        postfix(t.right, sb);
        if (t.operator != '\0') { 
            // If the current node is an operator
            sb.append(t.operator).append(' ');
             // Append its value to the string builder
        } else { // If the current node is an operand
            sb.append(t.operand).append(' '); 
            // Append its value to the string builder
        }
    }

    public String prefix() {
    // Create a StringBuilder to store the prefix expression
    StringBuilder sb = new StringBuilder();
    // Call the private prefix() method to traverse the 
    //expression tree and build the prefix expression
    prefix(root, sb);
    // Convert the StringBuilder to a String and return it
    return sb.toString();
}

private void prefix(ExpNode t, StringBuilder sb) {
    // If the current node is null, there is nothing to 
    //add to the expression
    if (t == null) {
        return;
    }
    // If the current node is an operator node, append 
    //its operator to the StringBuilder
    if (t.operator != '\0') {
        sb.append(t.operator).append(' ');
    }
    // If the current node is an operand node, append its
    // value to the StringBuilder
    else {
        sb.append(t.operand).append(' ');
    }
    // Recursively call prefix() on the left and right 
    //children of the current node
    prefix(t.left, sb);
    prefix(t.right, sb);
}

public String infix() {
    // Create a StringBuilder to store the infix expression
    StringBuilder sb = new StringBuilder();
    // Call the private infix() method to traverse the expression tree 
    //and build the infix expression
    infix(root, sb);
    // Convert the StringBuilder to a String, trim any extra whitespace,
    // and return it
    return sb.toString().trim();
}

private void infix(ExpNode t, StringBuilder sb) {
// If the current node is null, there is nothing to add to the expression
    if (t == null) {
        return;
    }
    // If the current node has two children, wrap its value in parentheses
    if (t.left != null && t.right != null) {
        sb.append("(");
    }
    // Recursively call infix() on the left child of the current node
    infix(t.left, sb);
    // If the current node is an operator node, append its 
    //operator to the StringBuilder
    if (t.operator != '\0') {
        sb.append(t.operator).append(' ');
    }
    // If the current node is an operand node, append its value 
    //to the StringBuilder
    else {
        sb.append(t.operand).append(' ');
    }
    // Recursively call infix() on the right child of the current node
    infix(t.right, sb);
    // If the current node has two children, close the parentheses
    if (t.left != null && t.right != null) {
        sb.append(")");
    }
}

public static int applyOperator(ExpNode t, int num1, int num2) {
    // Get the operator of the given node
    char operatorSign = t.operator;
    // Apply the operator to the given operands and return the result
    switch (operatorSign) {
        case '+':
            return num1 + num2;
        case '-':
            return num1 - num2;
        case '*':
            return num1 * num2;
        case '/':
            return num1 / num2;
        // If the operator is not one of the four valid options, 
        //throw an exception
        default:
    throw new IllegalArgumentException("Unknown operator: " + operatorSign);
    }
}

public static boolean isOperator(String token) {
    // Return true if the given token is one of the four valid operators
    return token.equals("+") || token.equals("-") || 
           token.equals("*") || token.equals("/");
}
  public static boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
