package net.canway.commonsdk.commonutils;

/**
 * @author 赵坤
 * @email artzok@163.com
 */
public class CastUtil {
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return obj == null ? null : (T) obj;
    }
}
