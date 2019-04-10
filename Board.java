//	Name: Kuo, ChiaTse
//	StudentID: 54889895
//	Lab section: T01

public class Board {
	public static final int BOARD_HEIGHT = 20;
	public static final int BOARD_WIDTH = 10;
	public int numFullLinesRemovedvar = 0;
	private BlockColor[][] cells = new BlockColor[BOARD_HEIGHT][BOARD_WIDTH];
	private Block activeBlock;
	
	public Board() {
		newBlock();
		cells = new BlockColor[BOARD_HEIGHT][BOARD_WIDTH];
	}
	public Block activeBlock() {
		return this.activeBlock;
	}
	public BlockColor blockAt(int x, int y) {
		if(y<0) {
			return BlockColor.NO_COLOR;
		}
		return cells[y][x];
	}
	public void clear() {
		for(int i=0; i<BOARD_HEIGHT; i++) {
			for(int j=0; j<BOARD_WIDTH; j++) {
				cells[i][j] = BlockColor.NO_COLOR;
			}
		}
	}
	public void blockLanded() {
		for(int i=0; i<4; i++) {
			int y = activeBlock.getcells()[i].getRow();
			int x = activeBlock.getcells()[i].getCol();
			try {
				cells[y][x] = activeBlock.getcells()[i].getColor();
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		activeBlock = null;
	}
	public boolean canMove() {
		for(int i=0; i<activeBlock.getcells().length; i++) {
			if(cells[activeBlock.getcells()[i].getRow()][activeBlock.getcells()[i].getCol()]!=BlockColor.NO_COLOR) {
				return false;
			}
		}
		return true;
	}
	public boolean rotate() {
		if(activeBlock.getShape()=='O') {
			return false;
		}else {
			activeBlock.rotate();
			for(int i=0; i<4; i++) {
				if(activeBlock.getcells()[i].getCol() < 0 || 
					activeBlock.getcells()[i].getCol() >= BOARD_WIDTH || 
					activeBlock.getcells()[i].getRow() < 0 ||
					activeBlock.getcells()[i].getRow() >= BOARD_HEIGHT ||
					blockAt(activeBlock.getcells()[i].getCol(), activeBlock.getcells()[i].getRow()) != BlockColor.NO_COLOR) {
					for(int j=0; j<3; j++) {
						activeBlock.rotate();
					}
					return false;
				}
			}
		}
		return true;
	}
	public boolean oneLineDown() {
		try {
			activeBlock.movedown();
		} catch (OutOfBoardException e) {
			activeBlock.moveback();
			//blockLanded();
			//canMove();
			
			return false;
		}
		if(canMove()) {
			return true;
		}else {
			activeBlock.moveback();
			return false;
		}
	}
	public boolean moveLeft(){
		try {
			activeBlock.moveleft();
			if(canMove()) return true;
			activeBlock.moveright();
		}catch (OutOfBoardException e) {
			try {
				activeBlock.moveright();
				return false;
			} catch (OutOfBoardException e1) {
				e1.printStackTrace();
			}
		}
		return true;
	}
	public boolean moveRight(){
		try {
			activeBlock.moveright();
			if(canMove()) return true;
			activeBlock.moveleft();
			
		} catch (OutOfBoardException e) {
			try {
				activeBlock.moveleft();
				return false;
			} catch (OutOfBoardException e1) {
				e1.printStackTrace();
			}
		}
		return true;
	}
	public boolean newBlock() {
		int min = 0;
		int max = 6;
		int range = max-min+1;
		int rand = (int)(Math.random() * range) + min;
		switch(rand) {
			case 0:
				IBlock iBlock = new IBlock();
				activeBlock = iBlock;
				break;
			case 1:
				JBlock jBlock = new JBlock();
				activeBlock = jBlock;
				break;
			case 2:
				LBlock lBlock = new LBlock();
				activeBlock = lBlock;
				break;
			case 3:
				OBlock oBlock = new OBlock();
				activeBlock = oBlock;
				break;
			case 4:
				SBlock sBlock = new SBlock();
				activeBlock = sBlock;
				break;
			case 5:
				TBlock tBlock = new TBlock();
				activeBlock = tBlock;
				break;
			case 6:
				ZBlock zBlock = new ZBlock();
				activeBlock = zBlock;
				break;
		}
		if(canMove()) {
			return true;
		}
		activeBlock = null;
		return false;
	}
	public int removeFullRows() {
		int singleRemovedFullRows = 0 ;
		for(int i=BOARD_HEIGHT-1; i>=0; i--) {
			boolean lineIsFull = true;
			for(int j=0; j<BOARD_WIDTH; j++) {
				if(blockAt(j, i)==BlockColor.NO_COLOR) {
					lineIsFull = false;
					break;
				}
			}
			if (lineIsFull) {
				singleRemovedFullRows++;
				numFullLinesRemovedvar++;
				for(int j=0; j<BOARD_WIDTH; j++) {
                	cells[i][j] = BlockColor.NO_COLOR;
                }
                for (int k = i; k > 0; k--) {
                    for (int j = 0; j < BOARD_WIDTH; ++j)
                         cells[k][j] = cells[k-1][j];
                }
                i++;
                
            }
		}
		return singleRemovedFullRows;
	}
	public int numFullLinesRemoved() {
		return numFullLinesRemovedvar;
	}
}