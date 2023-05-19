package com.quangduong.SE330backend.service;

import com.quangduong.SE330backend.dto.task.TaskDTO;
import com.quangduong.SE330backend.dto.task.TaskUpdateDTO;

public interface TaskService {

    TaskDTO createTask(TaskDTO dto);
    TaskDTO updateTask(TaskUpdateDTO dto);
    void deleteTask(long id);

}
