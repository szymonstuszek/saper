package minesweeper;

public class Field {
	private boolean hasFlag;
	private boolean hasMine;
	private boolean hasCover;
	private int minesAroundCount;
	
	public Field() {
		this.hasFlag = false;
		this.hasMine = false;
		this.hasCover = true;
	}
	
	public boolean hasFlag() {
		return hasFlag;
	}

	public void setFlag() {
		this.hasFlag = true;
	}
	
	public void removeFlag() {
		this.hasFlag = false;
	}

	public boolean hasMine() {
		return hasMine;
	}

	public void setMine() {
		this.hasMine = true;
	}

	public boolean hasCover() {
		return hasCover;
	}

	public void removeCover() {
		this.hasCover = false;
	}

	public int getMinesAroundCount() {
		return minesAroundCount;
	}

	public void setCountOfMinesAround() {
		this.minesAroundCount += 1;
	}
}
