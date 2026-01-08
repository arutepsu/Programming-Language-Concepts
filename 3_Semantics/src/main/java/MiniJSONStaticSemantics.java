import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.*;

/**
 * Static semantics: e.g. no duplicate keys inside the same object.
 */
public class MiniJSONStaticSemantics {

    public static class SemanticError {
        public final int line;
        public final int col;
        public final String message;

        public SemanticError(int line, int col, String message) {
            this.line = line;
            this.col = col;
            this.message = message;
        }

        @Override
        public String toString() {
            return "Line " + line + ":" + col + " - " + message;
        }
    }

    public static class Listener extends MiniJSONParserBaseListener {
        private final Deque<Set<String>> objectKeyStack = new ArrayDeque<>();
        private final List<SemanticError> errors = new ArrayList<>();

        public List<SemanticError> getErrors() {
            return errors;
        }

        @Override
        public void enterObject(MiniJSONParser.ObjectContext ctx) {
            objectKeyStack.push(new HashSet<String>());
        }

        @Override
        public void exitObject(MiniJSONParser.ObjectContext ctx) {
            objectKeyStack.pop();
        }

        @Override
        public void enterPair(MiniJSONParser.PairContext ctx) {
            if (objectKeyStack.isEmpty()) return;

            Token keyToken = ctx.STRING().getSymbol();
            String key = unquote(keyToken.getText());

            Set<String> keys = objectKeyStack.peek();
            if (!keys.add(key)) {
                errors.add(new SemanticError(
                        keyToken.getLine(),
                        keyToken.getCharPositionInLine(),
                        "Duplicate key in object: \"" + key + "\""
                ));
            }
        }

        private String unquote(String s) {
            if (s != null && s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
                return s.substring(1, s.length() - 1);
            }
            return s;
        }
    }

    public static List<SemanticError> check(ParseTree tree) {
        ParseTreeWalker walker = new ParseTreeWalker();
        Listener listener = new Listener();
        walker.walk(listener, tree);
        return listener.getErrors();
    }
}
