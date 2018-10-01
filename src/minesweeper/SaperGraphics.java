package minesweeper;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class SaperGraphics {
	private Image[] fieldContent;
	private Image flag;
	private Image wrong;
	private Image cover;
	private Image hint;
	private Image mine;
	
	public SaperGraphics() {
		initializeGraphics();
	}

	public void initializeGraphics() {
		fieldContent = new Image[9];
		URL url;
		for(int i = 0; i < 9; i++) {
			url = SaperGraphics.class.getResource("/res/" + i + ".png");
			fieldContent[i] = (new ImageIcon(url)).getImage();
		}
		url = SaperGraphics.class.getResource("/res/flag.png");
		flag = new ImageIcon(url).getImage();
		url = SaperGraphics.class.getResource("/res/wrong.png");
		wrong = new ImageIcon(url).getImage();
		url = SaperGraphics.class.getResource("/res/cover.png");
		cover = new ImageIcon(url).getImage();
		url = SaperGraphics.class.getResource("/res/hint.png");					
		hint = new ImageIcon(url).getImage();
		url = SaperGraphics.class.getResource("/res/mine.png");
		mine = new ImageIcon(url).getImage();
		
	}

	public Image[] getFieldContent() {
		return fieldContent;
	}

	public void setFieldContent(Image[] fieldContent) {
		this.fieldContent = fieldContent;
	}

	public Image getFlag() {
		return flag;
	}

	public void setFlag(Image flag) {
		this.flag = flag;
	}

	public Image getWrong() {
		return wrong;
	}

	public void setWrong(Image wrong) {
		this.wrong = wrong;
	}

	public Image getCover() {
		return cover;
	}

	public void setCover(Image cover) {
		this.cover = cover;
	}

	public Image getHint() {
		return hint;
	}

	public void setHint(Image hint) {
		this.hint = hint;
	}

	public Image getMine() {
		return mine;
	}

	public void setMine(Image mine) {
		this.mine = mine;
	}

}
