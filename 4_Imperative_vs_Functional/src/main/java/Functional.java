import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Functional {
    private Functional() { }

    private static final int MIN_LENGTH = 20;

    public static void main(String[] args) throws IOException {
        Path input = Paths.get(args[0]);

        long start = System.nanoTime();

        int n;
        try (var stream = Files.lines(input)) {
            n = stream
                    .filter(s -> !s.trim().isEmpty())
                    .filter(s -> s.length() >= MIN_LENGTH)
                    .mapToInt(String::length)
                    .sum();
        }

        long stop = System.nanoTime();

        System.out.printf("result = %d (%d microsec)%n", n, (stop - start) / 1000);
    }
}
