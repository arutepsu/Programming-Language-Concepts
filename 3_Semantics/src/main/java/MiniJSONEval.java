import java.math.BigDecimal;
import java.util.*;

/**
 * Dynamic semantics: evaluates MiniJSON parse tree to runtime JValue.
 */
public class MiniJSONEval extends MiniJSONParserBaseVisitor<JValue> {

    @Override
    public JValue visitJson(MiniJSONParser.JsonContext ctx) {
        return visit(ctx.value());
    }

    @Override
    public JValue visitValue(MiniJSONParser.ValueContext ctx) {
        if (ctx.STRING() != null) {
            return new JValue.JString(unquote(ctx.STRING().getText()));
        }
        if (ctx.NUMBER() != null) {
            // NUMBER is syntactically checked by lexer; parsing to BigDecimal is the runtime meaning
            return new JValue.JNumber(new BigDecimal(ctx.NUMBER().getText()));
        }
        if (ctx.TRUE() != null) return new JValue.JBool(true);
        if (ctx.FALSE() != null) return new JValue.JBool(false);
        if (ctx.NULL() != null) return new JValue.JNull();
        if (ctx.object() != null) return visit(ctx.object());
        if (ctx.array() != null) return visit(ctx.array());

        throw new IllegalStateException("Unknown value node: " + ctx.getText());
    }

    @Override
    public JValue visitArray(MiniJSONParser.ArrayContext ctx) {
        List<JValue> vals = new ArrayList<>();
        for (MiniJSONParser.ValueContext v : ctx.value()) {
            vals.add(visit(v));
        }
        return new JValue.JArray(vals);
    }

    @Override
    public JValue visitObject(MiniJSONParser.ObjectContext ctx) {
        Map<String, JValue> map = new LinkedHashMap<>();

        for (MiniJSONParser.PairContext p : ctx.pair()) {
            String key = unquote(p.STRING().getText());
            JValue val = visit(p.value());

            // Dynamic semantics choice:
            // if static semantics is enabled, duplicate keys should not appear here.
            map.put(key, val);
        }

        return new JValue.JObject(map);
    }

    private String unquote(String s) {
        if (s != null && s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}
