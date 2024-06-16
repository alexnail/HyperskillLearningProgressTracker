import java.util.function.*;


class Operator {

    public static LongBinaryOperator binaryOperator = (x, y) -> {
        var res = x;
        while (x < y) {
            res *= ++x;
        }
        return res;
    };
}