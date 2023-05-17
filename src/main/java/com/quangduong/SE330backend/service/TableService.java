package com.quangduong.SE330backend.service;

import com.quangduong.SE330backend.dto.table.TableDTO;
import com.quangduong.SE330backend.dto.table.TableUpdateDTO;

public interface TableService {

    TableDTO createTable(TableDTO dto);
    TableDTO updateTable(TableUpdateDTO dto);
    void deleteTable(long id);
}
