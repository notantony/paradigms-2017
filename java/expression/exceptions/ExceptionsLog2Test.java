package expression.exceptions;

import java.util.Arrays;

import static expression.Util.op;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ExceptionsLog2Test extends ExceptionsTest {
    public static final Reason NEG_LOG = new Reason("Logarithm of negative value");

    protected ExceptionsLog2Test() {
        unary.add(op("log2", this::log2));
        unary.add(op("pow2", this::pow2));

        System.out.println(tests.size());
        tests.addAll(Arrays.asList(
                op("log2 10", (x, y, z) -> 3),
                op("log2 -4", (x, y, z) -> error(NEG_LOG)),
                op("log2-5", (x, y, z) -> error(NEG_LOG)),
                op("pow2 4", (x, y, z) -> 16),
                op("pow2 8", (x, y, z) -> 256),
                op("pow2 x * y * z", (x, y, z) -> pow2(x) * y * z),
                op("pow2(x * y * z)", (x, y, z) -> pow2(x * y * z))
        ));
        parsingTest.addAll(Arrays.asList(
                op("hello", "hello"),
                op("log2()", "log2()"),
                op("log2(1, 2)", "log2(1, 2)"),
                op("lgg 1", "lgg 1"),
                op("log2 *", "log2 *"),
                op("log2x", "log2x")
        ));
    }

    private long pow2(final long a) {
        return 0 <= a && a <= 31 ? (long) Math.pow(2, a) : error(OVERFLOW);
    }

    private long log2(final long a) {
        return a <= 0 ? error(NEG_LOG) : (long) (Math.log(a) / Math.log(2));
    }

    public static void main(final String[] args) {
        new ExceptionsLog2Test().run();
    }
}
