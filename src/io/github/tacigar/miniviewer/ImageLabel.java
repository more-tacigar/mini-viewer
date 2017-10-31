package io.github.tacigar.miniviewer;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ImageLabel extends JLabel {
	private int _M_x;
	private int _M_y;
	private Image _M_image;
	private double _M_scale;
	
	public ImageLabel() {
		_M_image = null;
		_M_scale = 1.0;
	}
	
	@Override
	protected void paintComponent(Graphics __g) {
		super.paintComponent(__g);
		if (_M_image != null) {
			double __w = _M_image.getWidth(null);
			double __h = _M_image.getHeight(null);

			__g.drawImage(_M_image, _M_x, _M_y, (int)(__w * _M_scale), (int)(__h * _M_scale), this);
		}
	}
	
	public double getScale() {
		return _M_scale;
	}
	
	public int getX() {
		return _M_x;
	}
	
	public int getY() {
		return _M_y;
	}
	
	public void scaleUp(double __ds) {
		_M_scale += __ds;
		this.repaint();
	}
	
	public void scaleDown(double __ds) {
		_M_scale -= __ds;
		this.repaint();
	}
	
	public void moveUp(int __dy) {
		_M_y -= __dy;
		this.repaint();
	}
	
	public void moveDown(int __dy) {
		_M_y += __dy;
		this.repaint();
	}
	
	public void moveLeft(int __dx) {
		_M_x -= __dx;
		this.repaint();
	}
	
	public void moveRight(int __dx) {
		_M_x += __dx;
		this.repaint();
	}
	
	public void setImage(Image __image) {
		_M_image = __image;
		this.repaint();
	}
	
	public void setImageData(ImageData __imageData) {
		_M_image = __imageData.image;
		_M_x = __imageData.x;
		_M_y = __imageData.y;
		_M_scale = __imageData.scale;
		this.repaint();
	}
}
