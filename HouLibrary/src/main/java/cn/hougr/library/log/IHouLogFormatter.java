package cn.hougr.library.log;

public interface IHouLogFormatter<T> {

    String format(T data);
}