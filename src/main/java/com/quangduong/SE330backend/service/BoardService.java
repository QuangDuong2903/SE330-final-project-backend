package com.quangduong.SE330backend.service;

import com.quangduong.SE330backend.dto.board.BoardDTO;
import com.quangduong.SE330backend.dto.board.BoardDetailsDTO;
import com.quangduong.SE330backend.dto.board.BoardUpdateDTO;

public interface BoardService {

    BoardDetailsDTO getBoardDetails(long id);
    BoardDTO createBoard(BoardDTO dto);
    BoardDetailsDTO updateBoard(BoardUpdateDTO dto);
    void deleteBoardById(long id);

}
