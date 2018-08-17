package com.caliburn.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.caliburn.app.demo.SharePrefManager;
import com.caliburn.app.demo.entity.StudentEntity;


/**
 * sharepref例子
 * @author chentong
 * date 2018/8/15
 */
public class MainActivity extends AppCompatActivity {

    private TextView name;
    private TextView student;
    private Button showName;
    private EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData("Caliburn");
        refreshView();
        initListener();
    }

    private void initView() {
        name = (TextView) findViewById(R.id.name);
        student = (TextView) findViewById(R.id.student);
        showName = (Button) findViewById(R.id.showName);
        inputName = (EditText) findViewById(R.id.inputName);
    }

    private void initData(String name) {
        SharePrefManager.ISharedPref().username().put(name);
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(name);
        studentEntity.setAge(28);
        studentEntity.setOk(true);
        studentEntity.setWaitTime(1000);
        SharePrefManager.getIStudentPref().student().put(studentEntity);
    }

    private void refreshView() {
        String studentName = SharePrefManager.ISharedPref().username().get();
        name.setText(studentName);
        StudentEntity studentEntity = SharePrefManager.getIStudentPref().student().get();
        student.setText(studentEntity.toString());
    }

    private void initListener() {

        showName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inputName.getText().toString().trim();
                if (TextUtils.isEmpty(input)) return;
                initData(input);
                refreshView();
            }
        });
    }

}
