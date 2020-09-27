package cn.hougr.library.log;

import androidx.annotation.NonNull;

public interface IHouLogPrinter {
    void print(@NonNull HouLogConfig config, int level, String tag, @NonNull String printString);
}
