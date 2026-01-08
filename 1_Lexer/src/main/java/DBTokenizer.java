import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

public final class DBTokenizer {
    private DBTokenizer() {}

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: DBTokenizer <input-file>");
            System.exit(1);
        }

        DBLexer lexer = new DBLexer(CharStreams.fromFileName(args[0]));
        var tokens = lexer.getAllTokens();

        for (Token t : tokens) {
            if (t.getChannel() == Token.HIDDEN_CHANNEL) continue;
            System.out.printf("%s(\"%s\") ",
                    lexer.getVocabulary().getSymbolicName(t.getType()),
                    t.getText());
        }
        System.out.println();

        for (Token t : tokens) {
            System.out.printf("%s(\"%s\") ",
                    lexer.getVocabulary().getSymbolicName(t.getType()),
                    t.getText()
                            .replace("\n","\\n")
                            .replace("\r","\\r")
                            .replace("\t","\\t"));
        }
        System.out.println();
    }
}
