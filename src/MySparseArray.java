
// Author Tim Siwula
public class MySparseArray implements SparseArray
{
	
	/*----------------------------------------------------- */
	/* Private Data Members -- LinkedList */
	/*----------------------------------------------------- */
	private MyMatrixElem	TLcorner;
	
	private Object			deafaultValue;
	boolean					findCol, findRow, findElement, busyRow, busyCol, busyElement;
	
	public Object defaultValue()
	{
		return deafaultValue;
	}
	
	public void setDeafaultValue(Object deafaultValue)
	{
		this.deafaultValue = deafaultValue;
	}
	
	public void setDeafaultValue()
	{
		deafaultValue = 0;
	}
	
	/*----------------------------------------------------- */
	/* Constructor -- LinkedList */
	/*----------------------------------------------------- */
	MySparseArray(Object defaultValue)
	{
		setDeafaultValue(defaultValue);
		TLcorner = new MyMatrixElem(-1, -1, deafaultValue, null, null);
		System.out.println("<--   returning from constructor(defaultValue)   <--   <--   <--  ");
		
	}
	
	MySparseArray()
	{
		setDeafaultValue();
		TLcorner = new MyMatrixElem(-1, -1, deafaultValue, null, null);
		System.out.println("<--   returning from constructor()   <--   <--   <--  ");
		
	}
	
	class MyMatrixElem implements MatrixElem
	{
		public int		row, col;
		public Object	value;
		public MyMatrixElem	nextInRow, nextInCol;
		public boolean		alive;
		
		public MyMatrixElem(int r, int c, Object newValue, MyMatrixElem rowNext, MyMatrixElem colNext)
		{
			row = r;
			col = c;
			value = newValue;
			nextInRow = rowNext;
			nextInCol = colNext;
		}
		
		public int rowIndex()
		{
			return row;
		}
		
		public int columnIndex()
		{
			return col;
		}
		
		public Object value()
		{
			return value;
		}
	}
	
	public Object getValue(int r, int c)
	{
		// System.out.println("   -->   called getValue()   -->   -->   -->  ");
		Object tmp = getEle(r, c);
		
		// System.out.println("  tmp = null ??? " + tmp == null);
		
		if (tmp == null)
		{
			// System.out.println("   <--   Returning from getValue()   <--   <--   <--  ");
			Object t = defaultValue();
			return t;
		} else
		{
			// System.out.println("   <--   Returning from getValue()   <--   <--   <--  ");
			return tmp;
		}
	}
	
	public Object elementAt(int row, int col)
	{
		Object tmp = getValue(row, col);
		
		return tmp;
	}
	
	private boolean findRow(int r)
	{
		// System.out.println("   -->   called findRow()   -->   -->   -->  ");
		MyMatrixElem prev = TLcorner;
		MyMatrixElem curr = TLcorner.nextInRow;
		
		if (curr == null) // empty row
		{
			// System.out.println("   <--   Returning from findRow()   <--   <--   <--  ");
			
			return false;
		} else
		{
			
			while (curr != null)
			{
				
				if (curr.row == r) // found correct rowList index
				{
					return true;
				}
				
				curr = curr.nextInRow;
				prev = prev.nextInRow;
				
			}
			// System.out.println("   <--   Returning from findRow()   <--   <--   <--  ");
			
			return false;
		}
	}
	
	private boolean findCol(int c)
	{
		// System.out.println("   -->   called findCol()   -->   -->   -->  ");
		
		MyMatrixElem prev = TLcorner;
		MyMatrixElem curr = TLcorner.nextInCol;
		if (curr == null) // empty row
		{
			// System.out.println("   <--   Returning from findCol()   <--   <--   <--  ");
			
			return false;
		} else
		{
			
			while (curr != null)
			{
				
				if (curr.col == c) // found correct rowList index
				{
					// System.out.println("   <--   Returning from findCol()   <--   <--   <--  ");
					// System.out.println(" col  =  " + c);
					// System.out.println(" c.nextInCol  =  " + curr.nextInCol);
					// System.out.println(" c.nextInRow  =  " + curr.nextInRow);
					return true;
				}
				
				curr = curr.nextInCol;
				prev = prev.nextInCol;
				
			}
			
		}
		// System.out.println("   <--   Returning from findCol()   <--   <--   <--  ");
		
		return false;
	}
	
	private boolean findEle(int r, int c)
	{
		// System.out.println("   -->   called findEle()   -->   -->   -->  ");
		MyMatrixElem currR = TLcorner;
		MyMatrixElem currC = null;
		
		if (currR == null) // empty row
		{
			// System.out.println("   <--   Returning from findEle()   <--   <--   <--  ");
			return false;
		} else
		{
			
			while (currR != null) // scan down row list ( prevR,0 ) to ( currR, 0 )
			{
				
				if (currR.row == r) // found correct rowList index
				{
					
					// System.out.println(" r  =  " + r);
					// System.out.println(" currR.row   =  " + currR.row );
					// // System.out.println(" c.nextInRow  =  " + curr.nextInRow);
					
					currC = currR; // copy row index
					
					break;
				}
				currR = currR.nextInRow;
			}
			
			if (currR == null) // row out of bounds, DNE
			{
				// System.out.println("   <--   Returning from findEle()   <--   <--   <--  ");
				return false;
			}
			if (currC == null) // Empty col list. col DNE
			{
				// System.out.println("   <--   Returning from findEle()   <--   <--   <--  ");
				return false;
			}
			
			while (currC != null) // scan across col list ( currR, currC ) to ( currR, currC )
			{
				
				if (currC.col == c) // found
				{
					return true;
				}
				currC = currC.nextInCol;
			}
			
			// System.out.println("   <--   Returning from findEle()   <--   <--   <--  ");
			return false;
			
		}
	}
	
	private Object getEle(int r, int c)
	{
		// System.out.println("   -->   called getEle()    -->   -->   -->  ");
		MyMatrixElem currR = TLcorner;
		MyMatrixElem currC = null;
		
		if (currR == null) // empty row
		{
			// System.out.println("   <--   Returning from getEle()  #1  <--   <--   <--  ");
			return null;
		} else
		{
			
			while (currR != null) // scan down row list ( prevR,0 ) to ( currR, 0 )
			{
				
				if (currR.row == r) // found correct rowList index
				{
					currC = currR; // copy row index
					break;
				}
				currR = currR.nextInRow;
			}
			
			if (currR == null) // row out of bounds, DNE
			{
				// System.out.println("   <--   Returning from getEle()  #2 <--   <--   <--  ");
				return null;
			}
			if (currC == null) // Empty col list. col DNE
			{
				// System.out.println("   <--   Returning from getEle()  #3 <--   <--   <--  ");
				return null;
			}
			
			while (currC != null) // scan across col list ( currR, currC ) to ( currR, currC )
			{
				
				if (currC.col == c) // found
				{
					Object val = currC.value();
					
					// System.out.println("   <--   Returning from getEle()   #4    <--   <--   <--  ");
					return val;
				}
				currC = currC.nextInCol;
			}
			
			// System.out.println("   <--   Returning from getEle()   #5 <--   <--   <--  ");
			return null;
			
		}
	}
	
	public void setValue(int r, int c, Object v)
	{
		// System.out.println("   -->   called setValue()   -->   -->   -->  ");
		
		boolean row = findRow(r);
		
		boolean col = findCol(c);
		
		boolean ele = findEle(r, c);
		
		if (!row)
		{
			makeRow(r);
		}
		if (!col)
		{
			makeCol(c);
		}
		if (!ele)
		{
			makeEle(r, c, v);
		}
		
		// System.out.println("setting value " + v + " at (" + r + "," + c + ")");
		
		// System.out.println("   <--   Returning from setValue()   <--   <--   <--  ");
	}
	
	private void makeRow(int r)
	{
		// System.out.println("   -->   called makeRow()   -->   -->   -->  ");
		
		MyMatrixElem prev = TLcorner;
		MyMatrixElem curr = TLcorner.nextInRow;
		MyMatrixElem newRow;
		// System.out.println("   <--   returned makeRow()   <--   <--   <--  ");
		
		newRow = new MyMatrixElem(r, 0, deafaultValue, null, null);
		
		if (curr == null) // add to head
		{
			
			prev.nextInRow = newRow;
			curr = newRow;
		} else
		// add to non empty row list
		{
			while (curr != null)
			{
				if (curr.row == r) // add before curr
				{
					prev.nextInRow = newRow; // link prev to new
					newRow.nextInRow = curr; // link new to next
					break;
				}
				curr = curr.nextInRow;
				prev = prev.nextInRow;
			}
			if (curr == null) // add to end
			{
				prev.nextInRow = newRow;
			}
		}
		// System.out.println("   <--   returned makeRow()   <--   <--   <--  ");
	}
	
	public void makeCol(int c)
	{
		// System.out.println("   -->   called makeCol()   -->   -->   -->  ");
		MyMatrixElem prev = TLcorner;
		MyMatrixElem curr = TLcorner.nextInCol;
		MyMatrixElem newCol;
		newCol = new MyMatrixElem(0, c, deafaultValue, null, null);
		
		if (curr == null) // add to head
		{
			prev.nextInCol = newCol;
			curr = newCol;
		} else
		{
			while (curr != null)
			{
				if (curr.col == c) // add in middle
				{
					curr.nextInCol = newCol; // link prev.next to new
					newCol.nextInCol = curr; // link new to next
					// this are bad pointers and loop
					// newCol = prev.nextInCol; // link prev to new
				}
				curr = curr.nextInCol;
				prev = prev.nextInCol;
			}
			
			if (curr == null) // add to end
			{
				prev.nextInCol = newCol;
			}
			
		}
		// System.out.println("   <--   returned MakeCol()   <--   <--   <--  ");
	}
	
	public void makeEle(int r, int c, Object v)
	{
		// System.out.println("   -->   called makeEle()   -->   -->   -->  ");
		MyMatrixElem prevR = TLcorner;
		MyMatrixElem currR = TLcorner.nextInRow;
		MyMatrixElem prevC = null, currC = null;
		MyMatrixElem newEle = new MyMatrixElem(r, c, v, null, null);
		
		if (currR == null)
		{
		} else
		{
			while (currR != null)
			{
				if (currR.row == r) // found row index
				{
					prevC = currR; // copy row head index
					currC = prevC.nextInCol;
					
				}
				currR = currR.nextInRow;
				prevR = prevR.nextInRow;
			}
			
		}
		
		if (currC == null) // empty row list
		{
			prevC.nextInCol = newEle;
			prevR.nextInCol = newEle;
			
		} else
		{
			while (currC != null)
			{
				if (currC.col == c) // add in middle
				{
					prevC.nextInCol = newEle; // prev points to new
					newEle.nextInCol = currC; // new points to curr
				}
				currC = currC.nextInCol;
				prevC = prevC.nextInCol;
			}
			
			if (currC == null) // add to end
			{
				prevC.nextInCol = newEle;
			}
		}
		// System.out.println("   <--   Returning from makeEle()   <--   <--   <--  ");
	}
	
	public RowIterator iterateRows()
	{
		
		// System.out.println("<--   returning from constructor()   <--   <--   <--  ");
		
		return new MyRowIterator();
		
	}
	
	public ColumnIterator iterateColumns()
	{
		return new MyColumnIterator();
	}
	
	// -----------------------------------------------------------------------------------------
	// Nested inner class: MyRowIterator
	// -----------------------------------------------------------------------------------------
	private class MyRowIterator extends RowIterator
	{
		MyElemIterator	elemIterator;
		MyMatrixElem	curr;
		
		// -----------------------------------------------------------------------------------------------------------------------------------------
		// Constructor -- MyRowIterator
		// -----------------------------------------------------------------------------------------------------------------------------------------
		public MyRowIterator()
		{
			curr = TLcorner;
		}
		
		public ElemIterator next() // arg for where to start
		{
			elemIterator = new MyElemIterator(curr, true);
			curr = curr.nextInCol;
			return elemIterator;
		}
		
		public boolean hasNext()
		{
			return curr != null;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
		
		// -----------------------------------------------------------------------------------------
		// Nested inner class: MyElemIterator
		// -----------------------------------------------------------------------------------------
		private class MyElemIterator extends ElemIterator
		{
			MyMatrixElem	curr, next;
			boolean			isRow;
			int count = 0;
			
			/*----------------------------------------------------- */
			/* Constructor -- MyElemIterator */
			/*----------------------------------------------------- */
			public MyElemIterator(MyMatrixElem curr, boolean isRow)
			{
				
				this.curr = curr;
				this.isRow = isRow;
			}
			
			public MatrixElem next() // arg for where to start
			{
				
				System.out.println("busyRow == true");
				System.out.println("returning: " + busyRow);
				if (!isRow)
				{
					next = curr.nextInRow;
					count = curr.col;
					curr = curr.nextInRow;
				} else
				{
					next = curr.nextInCol;
					count = curr.row;
					curr = curr.nextInCol;
				}
				return curr;
			}
			
			public boolean interatingRow()
			{
				if (busyRow == true)
				{
					System.out.println("busyRow == true");
					System.out.println("returning: " + busyRow);
					return false;
				} else
				{
					System.out.println("busyRow == false");
					System.out.println("returning: " + busyRow);
					return false;
				}
			}
			
			public boolean interatingCol()
			{
				if (busyCol == true)
				{
					System.out.println("busyCol == true");
					System.out.println("returning: " + busyCol);
					return true;
				} else
				{
					System.out.println("busyCol == false");
					System.out.println("returning: " + busyCol);
					return false;
				}
			}
			
			public int nonIteratingIndex(boolean iteratingThroughRow)
			{
				if (busyRow == true)
				{
					System.out.println("busyRow == true");
					System.out.println("Index of row we are transversing = " + curr.row);
					System.out.println("returning current index: " + curr.row);
					return curr.row;
				} else
				{
					System.out.println("busyCol == true");
					System.out.println("Index of col we are transversing = " + curr.col);
					System.out.println("returning current index: " + curr.col);
					return curr.col;
				}
			}
			
			public boolean hasNext()
			{
				return curr.nextInCol != null;
			}
			
			public void remove()
			{
				throw new UnsupportedOperationException();
			}
			
			public boolean iteratingColumn()
			{
				return curr.nextInCol != null;
			}
			
			@Override
			public int nonIteratingIndex()
			{
				// TODO Auto-generated method stub
				return curr.col;
			}
		}
	}
	
	// -----------------------------------------------------------------------------------------
	// Nested inner class: MyColumnIterator
	// -----------------------------------------------------------------------------------------
	private class MyColumnIterator extends ColumnIterator
	{
		/*----------------------------------------------------- */
		/* Private Data Members -- MyColumnIterator */
		/*----------------------------------------------------- */
		MyMatrixElem	curr;
		MyElemIterator	colIterator;
		
		/*----------------------------------------------------- */
		/* Constructor -- MyColumnIterator */
		/*----------------------------------------------------- */
		// Instance variable for where I am now
		public MyColumnIterator()
		{
			System.out.println("   -->   called MyColIt constructor   -->   -->   -->  ");
			curr = TLcorner;
		}
		
		public ElemIterator next() // arg for where to start
		{
			System.out.println("   -->   called MyColIt next()   -->   -->   -->  ");
			colIterator = new MyElemIterator(false, curr);
			System.out.println("   <--   Returning MyColIt next()  <--   <--   <--  ");
			curr = curr.nextInCol;
			return colIterator;
		}
		
		public boolean hasNext()
		{
			return curr != null;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
		
		// -----------------------------------------------------------------------------------------
		// Nested inner class: MyElemIterator
		// -----------------------------------------------------------------------------------------
		private class MyElemIterator extends ElemIterator
		{
			MyMatrixElem	curr, next;
			boolean			isRow;
			int				count	= 0;
			
			/*----------------------------------------------------- */
			/* Constructor -- MyElemIterator */
			/*----------------------------------------------------- */
			public MyElemIterator(boolean isRow, MyMatrixElem curr)
			{
				this.isRow = isRow;
				this.curr = curr;
			}
			
			public MatrixElem next()
			{
				if (isRow)
				{
					next = curr.nextInRow;
					count = curr.col;
					curr = curr.nextInRow;
				} else
				{
					next = curr.nextInCol;
					count = curr.row;
					curr = curr.nextInCol;
				}
				return curr;
			}
			
			public boolean hasNext()
			{
				if (isRow)
				{
					if (curr.nextInRow != null)
					{
						return true;
					} else
					{
						return false;
					}
				} else
				{
					if (curr.nextInCol != null)
					{
						return true;
					} else
					{
						return false;
					}
				}
			}
			
			public boolean interatingRow()
			{if(isRow == true){
				return true;
			}
			return false;
			}
			
			public boolean interatingCol()
			{
				if(isRow == false){
					return true;
				}
				return false;
			}
			
			public int nonIteratingIndex()
			{
				if (isRow)
					return curr.col;
				return curr.row;
			}
			
			public void remove()
			{
				throw new UnsupportedOperationException();
			}
			
			public boolean iteratingColumn()
			{
				return false;
			}
		}
	}
	
}
