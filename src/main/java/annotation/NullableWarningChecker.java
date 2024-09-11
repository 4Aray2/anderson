package annotation;

import java.lang.reflect.Field;

public class NullableWarningChecker {

    public static void checkForNulls(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(NullableWarning.class)) {
                field.setAccessible(true);
                check(field, obj);
            }
        }
    }

    private static void check(Field field, Object obj) {
        try {
            if (field.get(obj) == null) {
                System.err.printf("Variable [%s] is null in [%s]\n", field.getName(), obj.getClass().getSimpleName());
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}