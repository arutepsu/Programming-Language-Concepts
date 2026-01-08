import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Runtime values for MiniJSON dynamic semantics.
 */
public interface JValue {

    final class JString implements JValue {
        public final String value;
        public JString(String value) { this.value = value; }
        @Override public String toString() { return "\"" + escape(value) + "\""; }
    }

    final class JNumber implements JValue {
        public final BigDecimal value;
        public JNumber(BigDecimal value) { this.value = value; }
        @Override public String toString() { return value.toPlainString(); }
    }

    final class JBool implements JValue {
        public final boolean value;
        public JBool(boolean value) { this.value = value; }
        @Override public String toString() { return value ? "true" : "false"; }
    }

    final class JNull implements JValue {
        @Override public String toString() { return "null"; }
    }

    final class JArray implements JValue {
        public final List<JValue> values;
        public JArray(List<JValue> values) { this.values = values; }

        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < values.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(values.get(i));
            }
            sb.append("]");
            return sb.toString();
        }
    }

    final class JObject implements JValue {
        public final Map<String, JValue> props;
        public JObject(Map<String, JValue> props) { this.props = props; }

        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            boolean first = true;
            for (Map.Entry<String, JValue> e : props.entrySet()) {
                if (!first) sb.append(",");
                first = false;
                sb.append("\"").append(escape(e.getKey())).append("\":");
                sb.append(e.getValue());
            }
            sb.append("}");
            return sb.toString();
        }
    }

    // minimal JSON escaping for printing
    static String escape(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\': sb.append("\\\\"); break;
                case '"':  sb.append("\\\""); break;
                case '\n': sb.append("\\n"); break;
                case '\r': sb.append("\\r"); break;
                case '\t': sb.append("\\t"); break;
                default: sb.append(c);
            }
        }
        return sb.toString();
    }
}
