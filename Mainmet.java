public class Mainmet {
    public static void main(String[] args) {
        ExpressionTree trial = new ExpressionTree("3 4 5 * -");
        System.out.println(trial.eval());
        System.out.println("post fix is " + trial.postfix());
        System.out.println("prefix is " + trial.prefix());
        System.out.println("in fix is " + trial.infix());
    }
}