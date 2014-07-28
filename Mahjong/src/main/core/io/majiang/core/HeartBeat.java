package io.majiang.core;

import java.io.Serializable;

public final class HeartBeat implements Serializable {
	private static final long serialVersionUID = 1974602133338656765L;

	public static final byte[] BYTES = new byte[0];

	private static HeartBeat instance = new HeartBeat();

	public static HeartBeat getSingleton() {
		return instance;
	}
}