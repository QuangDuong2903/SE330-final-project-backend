package com.quangduong.SE330backend.service;

import com.quangduong.SE330backend.dto.task.TaskDTO;
import com.quangduong.SE330backend.dto.task.TaskDetailsDTO;
import com.quangduong.SE330backend.dto.task.TaskUpdateDTO;

public interface TaskService {

    TaskDetailsDTO createTask(TaskDTO dto);
    TaskDetailsDTO updateTask(TaskUpdateDTO dto);
    void deleteTask(long id);

}
