package main.tap;

public class VectorY {
	int yamnt = 0;

	public VectorY(int xamnt) {
		this.yamnt = xamnt;
	}

	public int getYamnt() {
		return yamnt;
	}

	public void addForce(VectorY vc) {
		this.yamnt += vc.getYamnt();
	}
}
