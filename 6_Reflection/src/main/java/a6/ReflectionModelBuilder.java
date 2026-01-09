package a6;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class ReflectionModelBuilder {

    public TypeInfo build(Class<?> c) {
        boolean isInterface = c.isInterface();
        String kind = isInterface ? "interface" : "class";

        if (isInterface) {
            return new TypeInfo(
                    kind,
                    c.getName(),
                    true,
                    formatMethods(interfaceMethods(c)),
                    List.of()
            );
        }

        List<InterfaceInfo> interfaces = new ArrayList<>();
        for (Class<?> itf : c.getInterfaces()) {
            interfaces.add(new InterfaceInfo(
                    itf.getName(),
                    formatMethods(interfaceMethods(itf))
            ));
        }

        return new TypeInfo(
                kind,
                c.getName(),
                false,
                List.of(),
                interfaces
        );
    }

    private List<Method> interfaceMethods(Class<?> itf) {
        List<Method> result = new ArrayList<>();
        for (Method m : itf.getMethods()) {
            if (m.getDeclaringClass() != Object.class) {
                result.add(m);
            }
        }
        result.sort(Comparator.comparing(SignatureFormatter::sortKey));
        return result;
    }

    private List<String> formatMethods(List<Method> methods) {
        List<String> out = new ArrayList<>();
        for (Method m : methods) {
            out.add(SignatureFormatter.format(m));
        }
        return out;
    }
}
