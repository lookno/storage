package cn.neu.service;

import cn.neu.dto.TokenParamsDto;
import cn.neu.exception.ServerException;

public interface ITokenService {
	public void insertToken(TokenParamsDto params) throws ServerException;

	public int ifTokenValid(TokenParamsDto params) throws ServerException;

	public void inValid(TokenParamsDto params) throws ServerException;
}
