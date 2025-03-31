package com.example.first;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    private EditText taskInput;
    private Button addTaskBtn, viewListBtn;
    private ListView taskList;
    private TaskAdapter taskAdapter;
    private ArrayList<TaskModel> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        // Initialize UI
        taskInput = findViewById(R.id.taskInput);
        addTaskBtn = findViewById(R.id.addTaskBtn);
        viewListBtn = findViewById(R.id.viewListBtn);
        taskList = findViewById(R.id.taskList);

        // Initialize task list
        tasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, tasks);
        taskList.setAdapter(taskAdapter);

        // Add new task
        addTaskBtn.setOnClickListener(v -> {
            String taskText = taskInput.getText().toString().trim();
            if (!taskText.isEmpty()) {
                tasks.add(new TaskModel(taskText, false));
                taskAdapter.notifyDataSetChanged();
                taskInput.setText("");  // Clear input field
            }
        });

        // View list button click
        viewListBtn.setOnClickListener(v -> {
            if (tasks.isEmpty()) {
                Toast.makeText(TaskActivity.this, "No tasks yet!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TaskActivity.this, "Showing Task List", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
