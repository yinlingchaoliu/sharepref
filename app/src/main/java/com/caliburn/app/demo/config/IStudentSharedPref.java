package com.caliburn.app.demo.config;

import com.caliburn.app.demo.entity.StudentEntity;
import com.caliburn.sharepref.annotation.KEY;
import com.caliburn.sharepref.annotation.SPNAME;
import com.caliburn.sharepref.core.Call;

/**
 * 初始化spname
 * 一个spname 映射 一个table
 */
@SPNAME("student_sharepref")
public interface IStudentSharedPref {

    @KEY("student")
    Call<StudentEntity> student();

}