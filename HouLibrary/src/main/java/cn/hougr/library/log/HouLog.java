package cn.hougr.library.log;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Tips:
 * 1. 打印堆栈信息
 * 2. File输出
 * 3. 模拟控制台
 */
public class HouLog {
    private static final String HI_LOG_PACKAGE;

    static {
        String className = HouLog.class.getName();
        HI_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

    public static void v(Object... contents) {
        log(HouLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(HouLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(HouLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(HouLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(HouLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(HouLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(HouLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(HouLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(HouLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(HouLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(HouLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(HouLogType.A, tag, contents);
    }

    public static void log(@HouLogType.TYPE int type, Object... contents) {
        log(type, HouLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@HouLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(HouLogManager.getInstance().getConfig(), type, tag, contents);
    }

    public static void log(@NonNull HouLogConfig config, @HouLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!config.enable()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (config.includeThread()) {
            String threadInfo = HouLogConfig.HI_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }
        if (config.stackTraceDepth() > 0) {
            String stackTrace = HouLogConfig.HI_STACK_TRACE_FORMATTER.format(
                    HouStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(), HI_LOG_PACKAGE, config.stackTraceDepth()));
            sb.append(stackTrace).append("\n");
        }
        String body = parseBody(contents, config);
        if (body != null) {//替换转义字符\
            body = body.replace("\\\"", "\"");
        }
        sb.append(body);
        List<IHouLogPrinter> printers =
                config.printers() != null ? Arrays.asList(config.printers()) : HouLogManager.getInstance().getPrinters();
        if (printers == null) {
            return;
        }
        //打印log
        for (IHouLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
    }


    private static String parseBody(@NonNull Object[] contents, @NonNull HouLogConfig config) {
        if (config.injectJsonParser() != null) {
            //只有一个数据且为String的情况下不再进行序列化
            if (contents.length == 1 && contents[0] instanceof String) {
                return (String) contents[0];
            }
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
