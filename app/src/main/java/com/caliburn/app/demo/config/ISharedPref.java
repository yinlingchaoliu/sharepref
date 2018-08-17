package com.caliburn.app.demo.config;

import com.caliburn.sharepref.annotation.DEFAULT;
import com.caliburn.sharepref.annotation.KEY;
import com.caliburn.sharepref.annotation.SPNAME;
import com.caliburn.sharepref.core.Call;

/**
 * 初始化spname
 * 一个spname 映射 一个table
 */
@SPNAME("sharepref_table")
public interface ISharedPref {

    @KEY("username")
    Call<String> username();

    @KEY("isOk")
    @DEFAULT("false")
    Call<Boolean> isOk();

    @KEY("age")
    @DEFAULT("0")
    Call<Integer> age();

    @KEY("waitTime")
    @DEFAULT("0")
    Call<Long> waitTime();

}