import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Iterator;

public final class Procedural {
    private Procedural() { }

    private static final int MIN_LENGTH = 20;

    public static void main(String[] args) throws IOException {
        var input = Paths.get(args[0]);
        var lines = new LinkedList<String>();

        long start = System.nanoTime();

        readLines(Files.newBufferedReader(input), lines);
        removeEmptyLines(lines);
        removeShortLines(lines);
        int n = totalLineLengths(lines);

        long stop = System.nanoTime();

        System.out.printf("result = %d (%d microsec)%n", n, (stop - start) / 1000);
    }

    static void readLines(BufferedReader reader, LinkedList<String> lines) throws IOException {
        try (reader) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
    }

    static void removeEmptyLines(LinkedList<String> lines) {
        for (Iterator<String> it = lines.iterator(); it.hasNext();) {
            String s = it.next();
            if (s.trim().isEmpty()) {
                it.remove();
            }
        }
    }

    static void removeShortLines(LinkedList<String> lines) {
        for (Iterator<String> it = lines.iterator(); it.hasNext();) {
            String s = it.next();
            if (s.length() < MIN_LENGTH) {
                it.remove();
            }
        }
    }

    static int totalLineLengths(LinkedList<String> lines) {
        int sum = 0;
        for (String s : lines) {
            sum += s.length();
        }
        return sum;
    }
}
