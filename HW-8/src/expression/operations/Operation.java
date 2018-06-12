package expression.operations;

public interface Operation<T> {
    T add(T x, T y);

    T subtract(T x, T y);

    T negate(T x);

    T multiply(T x, T y);

    T divide(T x, T y);

    T parse(String number);

    T valueOf(int n);

    String toString(T x);
}
