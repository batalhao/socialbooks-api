package br.com.batalhao.socialbooks.utils;

import javax.servlet.http.HttpServletResponse;

public interface Utils {

	public static void setResponse(HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
	}

}
