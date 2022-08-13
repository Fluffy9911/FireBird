package main.fire.rendering;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.StringTokenizer;

/**
 * allows for colorful and multi-line text rendering
 * 
 * @author big_r
 *
 */
public class SpecialTextRenderer {
	Color c;
	String string;
	Renderer r;

	public SpecialTextRenderer(String string, Renderer r) {
		this.string = string;
		this.r = r;
		if (c == null)
			c = Color.BLACK;
	}

	public SpecialTextRenderer(Color c, String string, Renderer r) {
		this.c = c;
		this.string = string;
		this.r = r;
		if (c == null)
			c = Color.BLACK;
	}

	/**
	 * updates renderer to contain the new specified text.
	 * 
	 * @param text - the text to update the renderer to
	 */
	public void updateText(String text) {
		this.string = text;
	}

	/**
	 * replaces every \n in a string with a new line like logging or other text
	 * things
	 * 
	 * @param x
	 * @param y
	 */
	public void renderText(int x, int y) {
		new RenderingObject(r) {
			public void drawMultiLineText(String text, Graphics g, int x, int y, Color c) {
				g.setColor(c);
				FontMetrics metrics = g.getFontMetrics();

				StringTokenizer str = new StringTokenizer(text, "\n");
				int height = y;

				while (str.hasMoreTokens()) {
					String token = str.nextToken();

					g.drawString(token, x, height + metrics.getAscent());
					height += (metrics.getAscent() + metrics.getLeading());
				}
			}

			@Override
			public void render(Graphics g) {

				this.drawMultiLineText(string, g, x, y, c);
			}

		};
	}

	/**
	 * does the same if you would have created a SpecialTextRenderer except it also
	 * calls the render method
	 * 
	 * @param specialTextRenderer
	 * @param x
	 * @param y
	 * @return
	 */
	public static SpecialTextRenderer createAndRender(SpecialTextRenderer specialTextRenderer, int x, int y) {
		specialTextRenderer.renderText(x, y);
		return specialTextRenderer;
	}

}
