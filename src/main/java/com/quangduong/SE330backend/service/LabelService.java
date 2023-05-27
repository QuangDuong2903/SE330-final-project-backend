package com.quangduong.SE330backend.service;

import com.quangduong.SE330backend.dto.label.LabelDTO;
import com.quangduong.SE330backend.dto.label.LabelUpdateDTO;

public interface LabelService {

    LabelDTO createLabel(LabelDTO dto);
    LabelDTO updateLabel(LabelUpdateDTO dto);
    void deleteLabel(long id);
}
