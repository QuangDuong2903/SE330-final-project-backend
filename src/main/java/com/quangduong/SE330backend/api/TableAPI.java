package com.quangduong.SE330backend.api;

import com.quangduong.SE330backend.dto.table.TableDTO;
import com.quangduong.SE330backend.dto.table.TableDetailsDTO;
import com.quangduong.SE330backend.dto.table.TableUpdateDTO;
import com.quangduong.SE330backend.mapper.TableMapper;
import com.quangduong.SE330backend.service.TableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tables")
public class TableAPI {

    @Autowired
    private TableService tableService;

    @Autowired
    private TableMapper tableMapper;

    @PostMapping
    public ResponseEntity<TableDetailsDTO> createTable(@RequestBody @Valid TableDTO dto) {
        return new ResponseEntity<>(tableService.createTable(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TableDetailsDTO> updateTable(@PathVariable("id") long id, @RequestBody TableUpdateDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(tableService.updateTable(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TableDTO> deleteTable(@PathVariable("id") long id) {
        tableService.deleteTable(id);
        return ResponseEntity.ok().build();
    }

}
