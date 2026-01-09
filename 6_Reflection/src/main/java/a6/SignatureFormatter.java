package a6;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class SignatureFormatter {
    private SignatureFormatter() {}

    public static String format(Method m) {
        String ret = m.getReturnType().getName();
        String params = Arrays.stream(m.getParameterTypes())
                .map(Class::getName)
                .collect(Collectors.joining(", "));
        return ret + " " + m.getName() + "(" + params + ")";
    }

    public static String sortKey(Method m) {
        String params = Arrays.stream(m.getParameterTypes())
                .map(Class::getName)
                .collect(Collectors.joining(","));
        return m.getName() + "(" + params + ")->" + m.getReturnType().getName();
    }
}
