package a6;

import java.util.List;

public final class TypeInfo {
    public final String kind;
    public final String name;
    public final boolean isInterface;

    public final List<String> methods;
    public final List<InterfaceInfo> interfaces;

    public TypeInfo(String kind,
                    String name,
                    boolean isInterface,
                    List<String> methods,
                    List<InterfaceInfo> interfaces) {
        this.kind = kind;
        this.name = name;
        this.isInterface = isInterface;
        this.methods = methods;
        this.interfaces = interfaces;
    }
}
