package main.fire.util;

import java.util.ArrayList;
import java.util.List;

public class SimpleList {

	List<Object> list;

	public SimpleList() {
		this.list = new ArrayList<Object>();
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

}
