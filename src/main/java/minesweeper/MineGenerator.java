package minesweeper;

import java.util.Random;

public class MineGenerator {
	private int numberOfFields = Commons.NUMBER_OF_FIELDS;
	private Field[] fields;
	Random r = new Random();
	
	public MineGenerator(Board board) {
		fields = board.getFields();
	}
	
	public void placeMines() {
		int generatedNumber;
		int minesLeftToPlace = Commons.NUMBER_OF_MINES;
		
		while(minesLeftToPlace>0) {
			generatedNumber = r.nextInt(numberOfFields);
			
			if(!fields[generatedNumber].hasMine()){
			fields[generatedNumber].setMine();
			setupFieldsAroundMine(generatedNumber);
			minesLeftToPlace--;
			}
		}
	}
	
	public void addMineCount(int location) {
		if(!fields[location].hasMine()) {
			fields[location].setCountOfMinesAround();
		}
	}
	
	//the mine field is the reference location, we check the 8 fields around
	//by changing the location; current row allows to set up the fields at the
	//edges of the board
	public void setupFieldsAroundMine(int mineField) {
		int location;
		int currentRow = mineField % Commons.NUMBER_OF_ROWS;
		
		location = mineField - Commons.NUMBER_OF_ROWS - 1;
		if(location >= 0 && currentRow > 0) {
			addMineCount(location);
		}
		
		location = mineField - Commons.NUMBER_OF_ROWS;
		if(location >= 0) {
			addMineCount(location);
		}
		
		location = mineField - Commons.NUMBER_OF_ROWS + 1;
		if(location >= 0 && currentRow < (Commons.NUMBER_OF_ROWS - 1)) {
			addMineCount(location);
		}
		
		location = mineField - 1;
		if(location >= 0 && currentRow > 0) {
			addMineCount(location);
		}
		
		location = mineField + 1;
		if(location < numberOfFields && currentRow < (Commons.NUMBER_OF_ROWS - 1))	{
			addMineCount(location);
		}
		
		location = mineField + Commons.NUMBER_OF_ROWS - 1;
		if(location < numberOfFields && currentRow > 0) {
			addMineCount(location);
		}
		
		location = mineField + Commons.NUMBER_OF_ROWS;
		if(location < numberOfFields) {
			addMineCount(location);
		}
		
		location = mineField + Commons.NUMBER_OF_ROWS + 1;
		if(location < numberOfFields && currentRow < (Commons.NUMBER_OF_ROWS - 1)) {
			addMineCount(location);
		}
		
	}
}
