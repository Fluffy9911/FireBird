package main.fire.game.IO;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener {
	boolean right = false, left = false;

	@SuppressWarnings("static-access")
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			// System.out.println("Pressed");
			left = true;
			try {
				Thread.sleep(15);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			left = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			// System.out.println("Pressed");
			right = true;
			try {
				Thread.sleep(15);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			right = false;

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			// System.out.println("Pressed");
			left = true;
			try {
				Thread.sleep(3);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			left = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			// System.out.println("Pressed");
			right = true;
			try {
				Thread.sleep(3);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			right = false;

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean isRight() {
		return right;
	}

	public boolean isLeft() {
		return left;
	}

}
