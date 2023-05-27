package com.quangduong.SE330backend.service;

import com.quangduong.SE330backend.dto.table.TableDTO;
import com.quangduong.SE330backend.dto.table.TableDetailsDTO;
import com.quangduong.SE330backend.dto.table.TableUpdateDTO;

public interface TableService {

    TableDetailsDTO createTable(TableDTO dto);
    TableDetailsDTO updateTable(TableUpdateDTO dto);
    void deleteTable(long id);
}
