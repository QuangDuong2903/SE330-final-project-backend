package com.quangduong.SE330backend.service;

import com.quangduong.SE330backend.dto.task.TaskDTO;

public interface TaskService {

    TaskDTO createTask(TaskDTO dto);
    void deleteTask(long id);

}
