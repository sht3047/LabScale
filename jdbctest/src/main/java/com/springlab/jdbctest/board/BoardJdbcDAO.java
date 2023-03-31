package com.springlab.jdbctest.board;

import com.springlab.jdbctest.common.JDBCUtill;
import org.h2.util.JdbcUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

///* Spring Component */
//@Component

/* Create Bean*/
@Repository("boardJdbcDAO")
public class BoardJdbcDAO implements BoardDAO{
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet resultSet = null;

    /* sql 정의 */
    private final String BOARD_INSERT = "INSERT INTO Board(SEQ, TITLE, WRITER, CONTENT) VALUES ((SELECT nvl(max(seq), 0)+1 FROM Board), ?, ?, ?);";
    private final String BOARD_UPDATE =
            "UPDATE Board set TITLE=?, CONTENT=? where SEQ=?";
    private final String BOARD_DELETE =
            "DELETE Board where SEQ=?";
    private final String BOARD_GET =
            "SELECT * FROM Board WHERE SEQ=?";
    private final String BOARD_LIST =
            "SELECT * FROM Board ORDER BY SEQ DESC";



    @Override
    public void insertBoard(BoardDO board) {
        /* console log */
        System.out.println("[Magpie]>> execute insertBoard() using JDBC");
        try {
            conn = JDBCUtill.getConnection();
            stmt = conn.prepareStatement(BOARD_INSERT);
            /* 매개변수 == "?" */
            /* index는 1부터 시작 */
            stmt.setString(1, board.getTitle());
            stmt.setString(2, board.getWriter());
            stmt.setString(3, board.getContent());
            /* stmt 실행 */
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            /* try 문 내에서 exception 여부와 관계 없이 무조건 실행 */
            /* JDBC를 무조건 닫음 */
            JDBCUtill.close(stmt, conn);
        }
    }

    @Override
    public void updateBoard(BoardDO board) {
        /* console log */
        System.out.println("[Magpie]>> execute updateBoard() using JDBC");

        try{
            conn = JDBCUtill.getConnection();

            stmt = conn.prepareStatement(BOARD_UPDATE);
            /* 매개변수 == "?" */
            stmt.setString(1, board.getTitle());
            stmt.setString(2, board.getContent());
            stmt.setInt(3, board.getSeq());
            /* stmt 실행 */
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            /* try 문 내에서 exception 여부와 관계 없이 무조건 실행 */
            /* JDBC를 무조건 닫음 */
            JDBCUtill.close(stmt, conn);
        }
    }

    @Override
    public void deleteBoard(BoardDO board) {
        /* console log */
        System.out.println("[Magpie]>> execute deleteBoard() using JDBC");

        try{
            conn = JDBCUtill.getConnection();

            stmt = conn.prepareStatement(BOARD_DELETE);
            /* 매개변수 == "?" */
            stmt.setInt(1, board.getSeq());
            /* stmt 실행 */
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            /* try 문 내에서 exception 여부와 관계 없이 무조건 실행 */
            /* JDBC를 무조건 닫음 */
            JDBCUtill.close(stmt, conn);
        }
    }

    @Override
    public BoardDO getBoard(BoardDO board) {
        if (board == null){
            System.out.println("[Magpie]>> Table data has null.");
            return board;
        }
        /* console log */
        System.out.println("[Magpie]>> execute getBoard() using JDBC");
        BoardDO boardDO = null;
        try{
            conn = JDBCUtill.getConnection();

            stmt = conn.prepareStatement(BOARD_GET);
            /* 매개변수 == "?" */
            stmt.setInt(1, board.getSeq());
//            System.out.println("[Magpie]>> board.getSeq() :: "+board.getSeq());
            /* select 문이므로 resultSet에 저장 */
//            System.out.println("[Magpie]>> stmt :: "+stmt.toString());

            resultSet = stmt.executeQuery();
//            System.out.println("[Magpie]>> resultSet : " + resultSet.toString());
            if (resultSet.next()){
                boardDO = new BoardDO();
                boardDO.setSeq(resultSet.getInt("SEQ"));
//                System.out.println("[Magpie]>> resultSet.getInt(\"SEQ\")" + resultSet.getInt("SEQ"));
                boardDO.setTitle(resultSet.getString("TITLE"));
                boardDO.setWriter(resultSet.getString("WRITER"));
                boardDO.setContent(resultSet.getString("CONTENT"));
                boardDO.setRegDate(resultSet.getDate("REGDATE"));
                boardDO.setCnt(resultSet.getInt("CNT"));
//                System.out.println("[Magpie]>> board getBoard : " + boardDO.toString());
            }else {
                board = null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            /* try 문 내에서 exception 여부와 관계 없이 무조건 실행 */
            /* JDBC를 무조건 닫음 */
            JDBCUtill.close(stmt, conn);
        }
        return board;
    }

    @Override
    public List<BoardDO> listBoard() {
        /* console log */
        System.out.println("[Magpie]>> execute listBoard() using JDBC");
        /* 결과값을 위한 List */
        List<BoardDO> list = null;
        try{
            conn = JDBCUtill.getConnection();

            stmt = conn.prepareStatement(BOARD_LIST);
            /* 파라미터가 존재하지 않음 */

            /* select 문이므로 resultSet에 저장 */
            resultSet = stmt.executeQuery();
            /* 결과 값이 있는지 확인 */
            if (resultSet.isBeforeFirst()){
                list = new ArrayList<BoardDO>();
                /* 데이터가 존재하면 반복 */
                while (resultSet.next()){
                    /* Local */
                    BoardDO board = new BoardDO();
                    board.setSeq(resultSet.getInt("SEQ"));
                    board.setTitle(resultSet.getString("TITLE"));
                    board.setWriter(resultSet.getString("WRITER"));
                    board.setContent(resultSet.getString("CONTENT"));
                    board.setRegDate(resultSet.getDate("REGDATE"));
                    board.setCnt(resultSet.getInt("CNT"));
                    list.add(board);
                }
            }else{
                System.out.println("[Magpie]>> Error:: Table data has null.");
                return list;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            /* try 문 내에서 exception 여부와 관계 없이 무조건 실행 */
            /* JDBC를 무조건 닫음 */
            JDBCUtill.close(stmt, conn);
        }
        return list;
    }
}
