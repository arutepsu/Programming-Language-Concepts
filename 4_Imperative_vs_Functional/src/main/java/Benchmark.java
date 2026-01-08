import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public final class Benchmark {
    private Benchmark() {}

    private static final int WARMUP = 5;
    private static final int RUNS = 10;

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: Benchmark <input-file>");
            System.exit(1);
        }

        Path input = Paths.get(args[0]);

        // warmup (JIT, caches)
        for (int i = 0; i < WARMUP; i++) {
            runProc(input);
            runFunc(input);
        }

        long[] proc = new long[RUNS];
        long[] func = new long[RUNS];

        for (int i = 0; i < RUNS; i++) {
            proc[i] = timeMicros(() -> runProc(input));
            func[i] = timeMicros(() -> runFunc(input));
        }

        Arrays.sort(proc);
        Arrays.sort(func);

        System.out.println("Procedural (microsec): min=" + proc[0] + " median=" + proc[RUNS/2]);
        System.out.println("Functional (microsec): min=" + func[0] + " median=" + func[RUNS/2]);
    }

    private static void runProc(Path input) {
        try {
            Procedural.main(new String[]{input.toString()});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void runFunc(Path input) {
        try {
            Functional.main(new String[]{input.toString()});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static long timeMicros(Runnable r) {
        long start = System.nanoTime();
        r.run();
        long stop = System.nanoTime();
        return (stop - start) / 1000;
    }
}
