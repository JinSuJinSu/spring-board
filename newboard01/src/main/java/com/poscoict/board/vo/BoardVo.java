package com.poscoict.board.vo;

import java.util.Date;

public class BoardVo {
	private int boardNo;
	private String id;
	private String title;
	private String content;
	private int readCount;
	private int replyCount;
	private String writeDate;
	private String fileurl;
	
	
	public BoardVo() {
	}
	
	public BoardVo(int boardNo, String id, String title,
			int readCount, int replyCount, String writeDate) {
		this.boardNo = boardNo;
		this.id = id;
		this.title = title;
		this.readCount = readCount;
		this.replyCount = replyCount;
		this.writeDate = writeDate;
		
	}
	
	public BoardVo(int boardNo, String id, String title,
		int readCount, int replyCount, String writeDate, String fileurl) {
		this.boardNo = boardNo;
		this.id = id;
		this.title = title;
		this.readCount = readCount;
		this.replyCount = replyCount;
		this.writeDate = writeDate;
		this.fileurl = fileurl;	
	}
	
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public  String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	
	

}
