package io.majiang.filter;

import io.majiang.core.MjRequest;
import io.majiang.core.MjResponse;
import io.majiang.exception.FilterException;

public interface Filter {
	public void init() throws FilterException;
	public void doFilter(MjRequest req, MjResponse res) throws FilterException;
	public void destory() throws FilterException;
}
