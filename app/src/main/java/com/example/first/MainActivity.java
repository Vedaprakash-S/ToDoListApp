package com.example.first;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private EditText taskInput;
    private Button addTaskBtn, viewListBtn;
    private ArrayList<String> taskList;
    private ArrayList<Boolean> taskStatus;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.taskInput);
        addTaskBtn = findViewById(R.id.addTaskBtn);
        viewListBtn = findViewById(R.id.viewListBtn);

        sharedPreferences = getSharedPreferences("TaskPrefs", MODE_PRIVATE);
        taskList = loadTasks();
        taskStatus = loadTaskStatus();

        // Add task when button is clicked
        addTaskBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TaskActivity.class);
            startActivity(intent);

        });

        // Open View Tasks Page
        viewListBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
            intent.putStringArrayListExtra("taskList", taskList);
            intent.putExtra("taskStatus", taskStatus);
            startActivity(intent);
        });
    }

    private void saveTasks() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> taskSet = new HashSet<>(taskList);
        editor.putStringSet("tasks", taskSet);

        Set<String> completedSet = new HashSet<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskStatus.get(i)) {
                completedSet.add(taskList.get(i));
            }
        }
        editor.putStringSet("completedTasks", completedSet);
        editor.apply();
    }

    private ArrayList<String> loadTasks() {
        Set<String> taskSet = sharedPreferences.getStringSet("tasks", new HashSet<>());
        return new ArrayList<>(taskSet);
    }

    private ArrayList<Boolean> loadTaskStatus() {
        Set<String> completedSet = sharedPreferences.getStringSet("completedTasks", new HashSet<>());
        ArrayList<Boolean> statusList = new ArrayList<>();
        for (String task : taskList) {
            statusList.add(completedSet.contains(task));
        }
        return statusList;
    }
}
