package com.springlab.jdbctest.board;

import java.util.Date;

public class BoardDO {
    private int seq;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private int cnt;

    public BoardDO() {

    }

    public BoardDO(int seq, String title, String writer, String content, Date regDate, int cnt) {
        this.seq = seq;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.regDate = regDate;
        this.cnt = cnt;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "BoardDO{" +
                "seq=" + seq +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                ", cnt=" + cnt +
                '}';
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}