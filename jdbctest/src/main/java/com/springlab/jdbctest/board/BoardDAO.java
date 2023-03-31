package com.springlab.jdbctest.board;

import java.util.List;

public interface BoardDAO {
    void insertBoard(BoardDO board);
    void updateBoard(BoardDO board);
    void deleteBoard(BoardDO board);
    BoardDO getBoard(BoardDO board);
    List<BoardDO> listBoard();
}
