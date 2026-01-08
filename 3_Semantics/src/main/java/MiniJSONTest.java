import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class MiniJSONTest {

    // Collect syntax errors instead of printing immediately
    static class CollectingErrorListener extends BaseErrorListener {

        static class SyntaxErrorInfo {
            final int line;
            final int col;
            final String message;

            SyntaxErrorInfo(int line, int col, String message) {
                this.line = line;
                this.col = col;
                this.message = message;
            }

            @Override
            public String toString() {
                return "Line " + line + ":" + col + " - " + message;
            }
        }

        final List<SyntaxErrorInfo> errors = new ArrayList<>();

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                                Object offendingSymbol,
                                int line,
                                int charPositionInLine,
                                String msg,
                                RecognitionException e) {
            errors.add(new SyntaxErrorInfo(line, charPositionInLine, msg));
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java MiniJSONTest <input-file>");
            System.exit(1);
        }

        CharStream input = CharStreams.fromFileName(args[0]);

        MiniJSONLexer lexer = new MiniJSONLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        MiniJSONParser parser = new MiniJSONParser(tokens);

        // Syntax error handling
        CollectingErrorListener errListener = new CollectingErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errListener);

        ParseTree tree = parser.json();

        // 1) Syntax errors?
        if (!errListener.errors.isEmpty()) {
            System.out.println("SYNTAX ERRORS:");
            for (CollectingErrorListener.SyntaxErrorInfo e : errListener.errors) {
                System.out.println(e);
            }
            System.exit(2);
        }

        // 2) Static semantics errors?
        List<MiniJSONStaticSemantics.SemanticError> semErrors =
                MiniJSONStaticSemantics.check(tree);

        if (!semErrors.isEmpty()) {
            System.out.println("STATIC SEMANTICS ERRORS:");
            for (MiniJSONStaticSemantics.SemanticError e : semErrors) {
                System.out.println(e);
            }
            System.exit(3);
        }

        // 3) Dynamic semantics (evaluation)
        JValue result = new MiniJSONEval().visit(tree);

        System.out.println("OK: syntax + static semantics passed.");
        System.out.println("Dynamic semantics result (runtime value):");
        System.out.println(result);

        // System.out.println(tree.toStringTree(parser));
        // org.antlr.v4.gui.Trees.inspect(tree, parser);
    }
}
