package main.fire.rendering;

import java.util.ArrayList;
import java.util.List;

public class RenderProccesor {
	public List<Size> info;

	public static RenderProccesor create() {
		return new RenderProccesor(new ArrayList<Size>());

	}

	public RenderProccesor(List<Size> info) {
		this.info = info;
	}

}
