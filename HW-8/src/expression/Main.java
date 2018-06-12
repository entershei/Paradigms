package expression;

import expression.exceptions.CalculationException;
import expression.generic.GenericTabulator;
import expression.exceptions.ParseException;

public class Main {
    public static void main(final String[] args) throws ParseException, CalculationException {
        GenericTabulator gT = new GenericTabulator();

        Object[][][] ret = new Object[100][100][10];

        try {
            ret = gT.tabulate("d", "5.1+1+5", -6, 10, -15, 19, -1, 7);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i <= 16; ++i) {
            for (int j = 0; j <= 34; ++j) {
                for (int k = 0; k <= 8; ++k) {
                    System.out.println(ret[i][j][k]);
                }
            }
        }
    }
}

