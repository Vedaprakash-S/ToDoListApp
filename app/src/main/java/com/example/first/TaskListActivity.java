package com.example.first;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TaskListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private ArrayList<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load tasks from SharedPreferences
        loadTasks();

        adapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(adapter);
    }

    private void loadTasks() {
        SharedPreferences prefs = getSharedPreferences("TaskPrefs", Context.MODE_PRIVATE);
        Set<String> tasks = prefs.getStringSet("tasks", new HashSet<>());

        taskList = new ArrayList<>(tasks); // Convert Set to ArrayList
    }
}
