package io.majiang.entity;

public class User {
	/**
	 * 状态是房间外
	 */
	public static final int OUTSIDE_THE_ROOM= 1;
	/**
	 * 状态是在房间内但没开始游戏
	 */
	public static final int IN_THE_ROOM = 1;
	/**
	 * 状态是正在玩游戏
	 */
	public static final int PLAYING_GAME = 2;
			
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
