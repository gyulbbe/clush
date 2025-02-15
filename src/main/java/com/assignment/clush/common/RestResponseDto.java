package com.assignment.clush.common;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestResponseDto<T> {
	
	private int status;
	private String message;
	private T data;
	
	public RestResponseDto(int status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	/**
	 * 요청처리를 성공했을 때 응답을 생성해서 반환한다.
	 * @param <T> 데이터의 타입
	 * @param data 응답으로 보내는 데이터
	 * @return REST 표준 응답객체
	 */
	public static <T> RestResponseDto<T> success(T data) {
		return new RestResponseDto<>(
				HttpServletResponse.SC_OK,
				"success",
				data
		);
	}

	/**
	 * 요청 처리를 성공했을 때, 메시지와 함께 데이터를 반환한다.
	 * @param <T> 데이터의 타입
	 * @param data 응답으로 보내는 데이터
	 * @return REST 표준 응답객체
	 */
	public static <T> RestResponseDto<T> success(T data, String message) {
		return new RestResponseDto<>(
				HttpServletResponse.SC_OK,
				message,
				data
		);
	}
	
	/**
	 * 요청처리를 실패했을 때 응답을 생성해서 반환한다.
	 * @param <T> 데이터 타입, 데이터가 없기 때문에 void로 설정한다.
	 * @param message 오류 메시지
	 * @return REST 표준 응답객체
	 */
	public static <T> RestResponseDto<T> fail(String message) {
		return new RestResponseDto<>(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
				message,
				null
		);
	}
	
	/**
	 * 요청처리를 실패했을 때 응답을 생성해서 반환한다.
	 * @param <T> 데이터 타입, 데이터가 없기 때문에 void로 설정한다.
	 * @param status 상태 번호
	 * @param message 오류 메시지
	 * @return REST 표준 응답객체
	 */
	public static <T> RestResponseDto<T> fail(int status, String message) {
		return new RestResponseDto<>(
				status,
				message,
				null
		);
	}
}
