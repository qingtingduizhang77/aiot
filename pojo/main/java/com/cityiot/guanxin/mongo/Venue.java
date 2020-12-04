package com.cityiot.guanxin.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document(collection = "newyork")
public class Venue {

	@Id
	private String id;
	private String name;
	private double[] location;

	@PersistenceConstructor
	Venue(String name, double[] location) {
		super();
		this.name = name;
		this.location = location;
	}

	public Venue(String name, double x, double y) {
		super();
		this.name = name;
		this.location = new double[] { x, y };
	}

	public String getName() {
		return name;
	}

	public double[] getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "Venue [id=" + id + ", name=" + name + ", location=" + Arrays.toString(location) + "]";
	}
}