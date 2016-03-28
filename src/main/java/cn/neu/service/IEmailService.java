package cn.neu.service;

import cn.neu.dto.EmailParamsDto;
import cn.neu.exception.ServerException;

public interface IEmailService {
	public void sendEmail(EmailParamsDto e) throws ServerException;
}
