package expression;

//x^2-2x+1
public class Main {
    public static void main(final String[] args) {
        Double x = Double.parseDouble(args[0]);

        System.out.println(new Add(new Subtract(new Multiply(new Variable("x"), new Variable("x")),
                new Multiply(new Const(2), new Variable("x"))),
                new Const(1)
        ).evaluate(x));

        //System.out.println(new Const(10.1).evaluate(3.0));
    }
}
