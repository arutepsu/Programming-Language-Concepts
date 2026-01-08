package ast;

import java.math.BigDecimal;

public final class JsonNumber implements JsonValue {
    private final BigDecimal value;

    public JsonNumber(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
