package a6;

import java.util.List;

public final class InterfaceInfo {
    public final String name;
    public final List<String> methods;

    public InterfaceInfo(String name, List<String> methods) {
        this.name = name;
        this.methods = methods;
    }
}
