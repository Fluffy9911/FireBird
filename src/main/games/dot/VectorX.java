package main.games.dot;

public class VectorX {
	int xamnt = 0;

	public VectorX(int xamnt) {
		this.xamnt = xamnt;
	}

	public int getXamnt() {
		return xamnt;
	}

	public void addForce(VectorX vc) {
		this.xamnt += vc.getXamnt();
	}

	@Override
	public String toString() {
		return "VectorX [speed" + getXamnt() + "]";
	}

}
