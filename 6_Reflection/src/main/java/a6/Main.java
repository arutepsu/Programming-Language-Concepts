package a6;

import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public final class Main {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Usage: java a6.Main <fully.qualified.Class> [...]");
            System.exit(1);
        }

        List<Class<?>> classes = new ArrayList<>();
        for (String name : args) {
            classes.add(Class.forName(name));
        }

        ReflectionModelBuilder builder = new ReflectionModelBuilder();
        List<TypeInfo> types = new ArrayList<>();
        for (Class<?> c : classes) {
            types.add(builder.build(c));
        }

        STGroup group = new STGroupFile("aufgabe6.stg", '$', '$');
        ST page = group.getInstanceOf("page");

        if (page == null) {
            throw new IllegalStateException(
                    "Template 'page' not found. Check that aufgabe6.stg parses and defines page(types)."
            );
        }

        page.add("types", types);
        System.out.print(page.render());

    }
}
