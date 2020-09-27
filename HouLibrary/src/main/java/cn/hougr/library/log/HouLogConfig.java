package cn.hougr.library.log;

import cn.hougr.library.log.formatter.HouStackTraceFormatter;
import cn.hougr.library.log.formatter.HouThreadFormatter;

public abstract class HouLogConfig {
    public static int MAX_LEN = 512;
    static HouThreadFormatter HI_THREAD_FORMATTER = new HouThreadFormatter();
    static HouStackTraceFormatter HI_STACK_TRACE_FORMATTER = new HouStackTraceFormatter();

    public JsonParser injectJsonParser() {
        return null;
    }

    public String getGlobalTag() {
        return "HouLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }

    public IHouLogPrinter[] printers() {
        return null;
    }

    public interface JsonParser {
        String toJson(Object src);
    }
}
