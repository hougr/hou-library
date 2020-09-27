package cn.hougr.library.log.formatter;

import cn.hougr.library.log.IHouLogFormatter;

public class HouThreadFormatter implements IHouLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread:" + data.getName();
    }
}
