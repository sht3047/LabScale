package com.springlab.jdbctest.board;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.List;

public class BoardServiceClient {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(com.springlab.jdbctest.common.AppContextConfig.class);

        BoardService service = (BoardService) context.getBean("boardService");

        BoardDO board = new BoardDO();
        board.setTitle("테스트 게시글");
        board.setWriter("홍길동");
        board.setContent("테스트 게시글입니다...");
        service.insertBoard(board);

        List<BoardDO> list = service.listBoard();
        System.out.println("[Magpie]>> 게시글 목록 : ");
        for(BoardDO bd : list){
            System.out.printf("%d: %s \n", bd.getSeq(), bd.toString());

        }

//        System.out.println(board.toString());

        board.setSeq(1);
        board = service.getBoard(board);
        board.setCnt(board.getCnt()+1);
        service.updateBoard(board);

        board.setSeq(board.getSeq());
        service.deleteBoard(board);


        list = service.listBoard();
        for(BoardDO bd : list){
            System.out.printf("%d: %s \n", bd.getSeq(), bd.toString());
        }

        context.close();

    }
}
