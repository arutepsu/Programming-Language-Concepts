import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public final class GenerateTestFiles {
    private GenerateTestFiles() {}

    public static void main(String[] args) throws IOException {
        Path outDir = Paths.get("inputs");
        Files.createDirectories(outDir);

        long[] sizes = {
                100 * 1024L,      // 100 KB
                1 * 1024L * 1024, // 1 MB
                5 * 1024L * 1024, // 5 MB
                10 * 1024L * 1024,// 10 MB
                50 * 1024L * 1024 // 50 MB
        };

        for (long size : sizes) {
            Path file = outDir.resolve(prettyName(size) + ".txt");
            generateFile(file, size, 12345L);
            System.out.println("Generated: " + file + " (" + size + " bytes)");
        }
    }

    private static void generateFile(Path file, long targetBytes, long seed) throws IOException {
        Random rnd = new Random(seed);
        long written = 0;

        try (BufferedWriter w = Files.newBufferedWriter(file, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            while (written < targetBytes) {
                String line = randomLine(rnd);
                w.write(line);
                w.newLine();
                written += line.getBytes(StandardCharsets.UTF_8).length + 1;
            }
        }
    }

    private static String randomLine(Random rnd) {
        int pick = rnd.nextInt(100);

        if (pick < 10) {
            return rnd.nextBoolean() ? "" : "   \t  ";
        } else if (pick < 55) {
            int len = 1 + rnd.nextInt(19);
            return randomAscii(rnd, len);
        } else {
            int len = 20 + rnd.nextInt(200);
            return randomAscii(rnd, len);
        }
    }

    private static String randomAscii(Random rnd, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = (char) (32 + rnd.nextInt(95));
            sb.append(c);
        }
        return sb.toString();
    }

    private static String prettyName(long bytes) {
        if (bytes >= 1024L * 1024 * 1024) return (bytes / (1024L * 1024 * 1024)) + "GB";
        if (bytes >= 1024L * 1024) return (bytes / (1024L * 1024)) + "MB";
        return (bytes / 1024L) + "KB";
    }
}
