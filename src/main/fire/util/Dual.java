package main.fire.util;

public class Dual<I extends Object, K extends Object> {
	I one;
	K two;

	public Dual(I one, K two) {
		this.one = one;
		this.two = two;
	}

	public I getOne() {
		return one;
	}

	public void setOne(I one) {
		this.one = one;
	}

	public K getTwo() {
		return two;
	}

	public void setTwo(K two) {
		this.two = two;
	}

}
