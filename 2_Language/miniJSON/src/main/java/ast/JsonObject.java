package ast;

import java.util.Map;

public final class JsonObject implements JsonValue {
    private final Map<String, JsonValue> members;

    public JsonObject(Map<String, JsonValue> members) {
        this.members = members;
    }

    public Map<String, JsonValue> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "JsonObject" + members;
    }
}
