package io.majiang.service;

import io.majiang.core.MjRequest;
import io.majiang.core.MjResponse;
import io.majiang.exception.GameServceException;

public interface BaseService {
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws GameServceException
	 */
	public void invoke(MjRequest request, MjResponse response) throws GameServceException;
}
