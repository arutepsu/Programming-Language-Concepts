import ast.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AstBuilder extends MiniJSONParserBaseVisitor<JsonValue> {

    @Override
    public JsonValue visitJson(MiniJSONParser.JsonContext ctx) {
        return visit(ctx.value());
    }

    @Override
    public JsonValue visitObject(MiniJSONParser.ObjectContext ctx) {
        Map<String, JsonValue> members = new LinkedHashMap<>();
        for (MiniJSONParser.PairContext p : ctx.pair()) {
            String keyWithQuotes = p.STRING().getText();
            String key = stripQuotes(keyWithQuotes);
            JsonValue value = visit(p.value());
            members.put(key, value);
        }
        return new JsonObject(members);
    }

    @Override
    public JsonValue visitArray(MiniJSONParser.ArrayContext ctx) {
        List<JsonValue> elements = new ArrayList<>();
        for (MiniJSONParser.ValueContext vctx : ctx.value()) {
            elements.add(visit(vctx));
        }
        return new JsonArray(elements);
    }

    @Override
    public JsonValue visitValue(MiniJSONParser.ValueContext ctx) {
        if (ctx.STRING() != null) {
            String text = ctx.STRING().getText();
            return new JsonString(stripQuotes(text));
        }
        if (ctx.NUMBER() != null) {
            return new JsonNumber(new BigDecimal(ctx.NUMBER().getText()));
        }
        if (ctx.object() != null) {
            return visitObject(ctx.object());
        }
        if (ctx.array() != null) {
            return visitArray(ctx.array());
        }
        if (ctx.TRUE() != null) {
            return new JsonBoolean(true);
        }
        if (ctx.FALSE() != null) {
            return new JsonBoolean(false);
        }
        if (ctx.NULL() != null) {
            return JsonNull.INSTANCE;
        }
        throw new IllegalStateException("Unknown value: " + ctx.getText());
    }

    private String stripQuotes(String s) {
        if (s != null && s.length() >= 2 && s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"') {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}
