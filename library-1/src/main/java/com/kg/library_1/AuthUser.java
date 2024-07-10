package com.kg.library_1;

import java.io.Serializable;

public class AuthUser implements Serializable { //redis session에서 직렬화 하여 사용 할 수 있게 인터페이스를 구현한 클래스 

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
}
