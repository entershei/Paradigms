package expression.generic;

import expression.TripleExpression;
import expression.parser.ExpressionParser;
import expression.exceptions.ParseException;
import expression.exceptions.SegmentException;
import expression.operations.*;

import java.util.TreeMap;

public class GenericTabulator implements Tabulator {
    private static final TreeMap<String, Operation<?>> modes = new TreeMap<>();

    static {
        modes.put("i", new CheckedIntegerOperation());
        modes.put("d", new DoubleOperation());
        modes.put("bi", new BigIntegerOperation());
        modes.put("u", new IntegetOperation());
        modes.put("l", new LongOperation());
        modes.put("s", new ShortOperation());
    }

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        if (x1 > x2 || y1 > y2 || z1 > z2) {
            throw new SegmentException("Wrong segment for table");
        }

        Operation<?> op = modes.get(mode);

        return table(op, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] table(Operation<T> op, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParseException {
        Object[][][] ret = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];

        TripleExpression<T> parseExpression = new ExpressionParser<>(op).parse(expression);

        for (int x = 0; x <= x2 - x1; ++x) {
            for (int y = 0; y <= y2 - y1; ++y) {
                for (int z = 0; z <= z2 - z1; ++z) {
                    try {
                        ret[x][y][z] = parseExpression.evaluate(op.valueOf(x + x1), op.valueOf(y + y1),
                                op.valueOf(z + z1));
                    } catch (Exception e) {
                        ret[x][y][z] = null;
                    }
                }
            }
        }

        return ret;
    }
}
