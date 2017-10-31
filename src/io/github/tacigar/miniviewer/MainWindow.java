package io.github.tacigar.miniviewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private int _M_imageIndex;
	private ArrayList<ImageData> _M_imageDataList;
	private ImageLabel _M_imageLabel; 
	
	public MainWindow(ArrayList<Image> __images) {
		_M_imageLabel = new ImageLabel(); 
		Dimension __screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(10, 0, 600, __screenSize.height);
		this.setTitle("Mini Viewer");
		
		JPanel __panel = new JPanel(new BorderLayout());
		__panel.add(_M_imageLabel);
		this.getContentPane().add(__panel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if (__images.size() > 0) {
			_M_imageLabel.setImage(__images.get(0));
		}
		_M_imageDataList = new ArrayList<>();
		for (Image __image : __images) {
			ImageData __imgdata = new ImageData();
			__imgdata.image = __image;
			_M_imageDataList.add(__imgdata);
		}
		_M_imageIndex = 0;
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent __event) {
			}

			@Override
			public void keyPressed(KeyEvent __event) {
				switch (__event.getKeyCode()) {
					case KeyEvent.VK_E: /* scale up */
						_M_imageLabel.scaleUp(0.1); break;
					case KeyEvent.VK_Q: /* scale down */
						_M_imageLabel.scaleDown(0.1); break;
					case KeyEvent.VK_W: /* move up */
						_M_imageLabel.moveUp(10); break;
					case KeyEvent.VK_S: /* move down */
						_M_imageLabel.moveDown(10); break;
					case KeyEvent.VK_A: /* move left */
						_M_imageLabel.moveLeft(10); break;
					case KeyEvent.VK_D: /* move right */
						_M_imageLabel.moveRight(10); break;
					case KeyEvent.VK_RIGHT: /* next image */
						if (_M_imageIndex >= _M_imageDataList.size() - 1) {
							_M_imageIndex = 0;
						} else {
							_M_imageIndex++;
						}
						_M_imageLabel.setImageData(_M_imageDataList.get(_M_imageIndex));
						break;
					case KeyEvent.VK_LEFT: /* next image */
						if (_M_imageIndex <= 0) {
							_M_imageIndex = _M_imageDataList.size() - 1;
						} else {
							_M_imageIndex--;
						}
						_M_imageLabel.setImageData(_M_imageDataList.get(_M_imageIndex));
						break;
				}
			}

			@Override
			public void keyReleased(KeyEvent __event) {
			}
		});
	}
	
	public static void main(String[] __args) {
		String __currentDirPath = System.getProperty("user.dir");
		String __targetDirPath;
		
		if (__args.length == 0) {
			__targetDirPath = __currentDirPath;
		} else {
			StringBuffer __buffer = new StringBuffer(__currentDirPath);
			__buffer.append('/');
			__buffer.append(__args[0]);
			__targetDirPath = __buffer.toString();
		}
		
		File __targetDir = new File(__targetDirPath);
		File[] __files = __targetDir.listFiles();
		if (__files == null) {
			System.err.println("No such a directory");
			System.exit(-1);
		}
		
		ArrayList<Image> __images = new ArrayList<>();
		for (File __file: __files) {
			try {
				Image __image = ImageIO.read(__file);
				__images.add(__image);
			} catch (IOException e) {
				// DO NOTHING!
			}
		}
		
		MainWindow __mainWindow = new MainWindow(__images);
		__mainWindow.setVisible(true);
	}
}
