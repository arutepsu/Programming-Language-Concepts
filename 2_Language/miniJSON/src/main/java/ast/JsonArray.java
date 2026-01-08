package ast;

import java.util.List;

public final class JsonArray implements JsonValue {
    private final List<JsonValue> elements;

    public JsonArray(List<JsonValue> elements) {
        this.elements = elements;
    }

    public List<JsonValue> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "JsonArray" + elements;
    }
}
