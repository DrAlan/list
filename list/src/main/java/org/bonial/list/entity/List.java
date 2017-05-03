package org.bonial.list.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class List {
	private String id;
	private String name;
	private String version;
	// @JsonManagedReference
	private java.util.List<Item> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public java.util.List<Item> getItems() {
		return items;
	}

	public void setItems(java.util.List<Item> items) {
		this.items = items;
	}
}
