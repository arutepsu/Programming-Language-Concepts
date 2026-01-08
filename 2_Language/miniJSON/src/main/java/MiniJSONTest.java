import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class MiniJSONTest {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java MiniJSONTest <input-file>");
            System.exit(1);
        }

        CharStream input = CharStreams.fromFileName(args[0]);

        MiniJSONLexer lexer = new MiniJSONLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        MiniJSONParser parser = new MiniJSONParser(tokens);

        ParseTree tree = parser.json();

        System.out.println(tree.toStringTree(parser));

        org.antlr.v4.gui.Trees.inspect(tree, parser);
    }
}
