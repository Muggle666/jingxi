package com.jingxi.VO;

import java.util.Date;

import com.jingxi.model.TbItemCat;

public class TbItemParamVO {
	private Long id;
	private Long itemCatId;
	private Date created;
	private Date updated;
	private String name;

	public Long getId() {
		return id;
	}

	public Long getItemCatId() {
		return itemCatId;
	}

	public void setItemCatId(Long itemCatId) {
		this.itemCatId = itemCatId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
