package core.base.basic;

import java.util.ArrayList;
import java.util.List;

public interface Lists {
    List EMPTY_LIST = new ArrayList(0);

    static <T> List<T> of() {
        return new ArrayList<>();
    }

    static <T> List<T> of(T t1) {
        List<T> list = of();
        list.add(t1);
        return list;
    }

    static <T> List<T> of(T t1, T t2) {
        List<T> list = of();
        list.add(t1);
        list.add(t2);
        return list;
    }

    static <T> List<T> of(T t1, T t2, T t3) {
        List<T> list = of();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        return list;
    }

    static <T> List<T> of(T t1, T t2, T t3, T t4) {
        List<T> list = of();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        return list;
    }

    static <T> List<T> of(T t1, T t2, T t3, T t4, T t5) {
        List<T> list = of();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        return list;
    }

    static <T> List<T> of(T t1, T t2, T t3, T t4, T t5, T t6) {
        List<T> list = of();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        return list;
    }

    static <T> List<T> of(T t1, T t2, T t3, T t4, T t5, T t6, T t7) {
        List<T> list = of();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t7);
        return list;
    }

    static <T> List<T> of(T t1, T t2, T t3, T t4, T t5, T t6, T t7, T t8) {
        List<T> list = of();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t7);
        list.add(t8);
        return list;
    }


}
