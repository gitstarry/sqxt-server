package com.cqut.why.authoringsystem.authoringsystem.entity.dto;

import lombok.Data;

@Data
public class PermissionEntity {

	private Integer id;

	private String tag;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
