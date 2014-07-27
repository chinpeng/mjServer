package io.majiang.session;

public interface Session {
	public void  expire();

	public String getIdInternal();
}
