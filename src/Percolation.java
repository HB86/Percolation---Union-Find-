import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class Percolation {
	private static boolean Grid[][];
	private int size; 
	private WeightedQuickUnionUF unionFind;
	private int GridSize;
	private int top = 0; 
	private int bottom; 
	static int randx;
	static int randy;
	
	

	public Percolation(int n){
		System.out.println("Percolation constructor");
		int i = 0;
		int j = 1;
		size = n; 
		bottom = size * size + 1;
		unionFind = new WeightedQuickUnionUF(size * size + 2); 
		Grid = new boolean[n][n]; 
		GridSize = n; 
		for(i=0; i<n; i++){
				
			for(j=0; j<n; j++){
			 Grid[i][j]=false;
		
	  }	
		
		
    }
	
}
	
	public void open(int row, int col){
		
		Grid[row][col]=true;
		if(row == 1
				){
			unionFind.union(getIndex(row, col),top);
		}
		
		if(row == size){
			
			unionFind.union(getIndex(row,col),bottom); 
		}
		
		if(row > 1 && isOpen(row-1, col)){
			
			unionFind.union(getIndex(row,col), getIndex(row-1,col));
			
			
		}
		
		if(row < (size-1) && isOpen(row+1, col)){
			
			unionFind.union(getIndex(row,col), getIndex(row+1,col));
			
		}
		
		
		if(col > 1 && isOpen(row, col-1)){
			
			
			unionFind.union(getIndex(row,col), getIndex(row,col-1));
			
			
		}
	
		if(col < (size-1) && isOpen(row, col+1)){
			
			
			unionFind.union(getIndex(row,col), getIndex(row,col+1));
			
			
		}
		
		
		
		
	}
	

	public boolean isOpen(int row, int col){
		
		return Grid[row-1][col-1];
		
	}
	
	
	public boolean isClosed(int row, int col){
		
	if(0 < row && row <= size && 0 < col && col <= size){
		
		return unionFind.connected(top, getIndex(row,col));
		
		
	}
	else{
		
		throw new IndexOutOfBoundsException(); 
	}
		
		//if(!isOpen(row,col)){
		
		//	return false;
		
		//}
		
		//return unionFind.connected(getIndex(row,col), top); 
		//return !Grid[row][col];
	}
	
	public int numberofOpenSites(){
		System.out.println("numberofopensites");
		int k = 0;
		int i = 0;
		int j = 1;
		for(i=0; i<GridSize; i++){
				
			for(j=0; j<GridSize; j++){
			if(Grid[i][j]){
				k++;
				}
		
			}	
		
		
		}
		
		return k;
	}
	
	public boolean percolates(){
		
		return unionFind.connected(top, bottom);
			
			
		
	}
	
	private int getIndex(int row, int column){
		
		return size * (row - 1) + column;
		
	}
	
//	public void show() {
		
	//	for (int i=0; i<GridSize; i++) {
		//	for (int j=0; j<GridSize; j++) {
			//	if (Grid[i][j])  
				
				//{
					
					
	           // }
				
				//}
					
		
				
				//StdDraw.filledSquare(i+0.5, j+0.5, 0.45);
		
	//  }
	
		
	// }
			
	
	public static void draw(Percolation perc, int N) {
      
		StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        StdDraw.filledSquare(N/2.0, N/2.0, N/2.0);

        // draw N-by-N grid
        int opened = 0;
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (perc.isClosed(row, col)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    opened++;
                }
                else if (perc.isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    opened++;
                }
                else
                    StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledSquare(col - 0.5, N - row + 0.5, 0.45);
            }
        }

       
	}
	
	public static void main(String[]args){
		int gd = 20;
		int count = 0; 
		Percolation test = new Percolation(gd);
		while(!test.percolates()){
			    int randx = (int)(1+(Math.random() * (gd-1)));
				System.out.println(randx);
				int randy = (int)(1+(Math.random() * (gd-1)));
				System.out.println(randy); 
			    test.open(randx, randy);		
			   count++;
			    draw(test, gd);
			 }
				//count++;
			    
				System.out.println(test.percolates());
				System.out.println(count);
		

	 }
		
	  
	
   }
	


	
	

