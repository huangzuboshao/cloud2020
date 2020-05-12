package com.liao.springcloud.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/5 9:29
 */
public class ETest {
    public static void main(String[] args) {

        // 频繁往外读取内容的，适合用上界Extends。
        // 经常往里插入的，适合用下界Super。
        // extends 可用于返回类型限定，不能用于参数类型限定（换句话说：? extends xxx 只能用于方法返回类型限定，
        // jdk能够确定此类的最小继承边界为xxx，只要是这个类的父类都能接收，但是传入参数无法确定具体类型，只能接受null的传入）。
        // super 可用于参数类型限定，不能用于返回类型限定（换句话说：? supper xxx 只能用于方法传参，因为jdk能够确定传入为xxx的子类，
        // 返回只能用Object类接收）。
        // ? 既不能用于方法参数传入，也不能用于方法返回。

        // <? super Father>
        // 不能 get
        List<? super Father> list = new ArrayList<>();
        list.add(new Son());
        list.get(0);

        // <? extends Father>
        // 不能 add
        List<? extends Father> extendsList = new ArrayList<>();
    }
}
