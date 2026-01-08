import ast.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class BuildAst {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java BuildAst <input-file>");
            System.exit(1);
        }

        CharStream input = CharStreams.fromFileName(args[0]);
        MiniJSONLexer lexer = new MiniJSONLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniJSONParser parser = new MiniJSONParser(tokens);

        MiniJSONParser.JsonContext tree = parser.json();

        AstBuilder builder = new AstBuilder();
        JsonValue ast = builder.visit(tree);

        System.out.println(ast);
    }
}
