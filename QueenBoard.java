//PREVIOUS COMMITS IN MKS22X-RECUSION
import java.util.ArrayList;

public class QueenBoard{

  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
    for (int i = 0; i < size; i++){
      for (int j = 0; j < size; j++){
        board[i][j] = 0;
      }
    }
  }

  private boolean addQueen(int r, int c){
    if (r >= board.length || c >= board.length || board[r][c] != 0){
      return false;
    } else {
      //horizontal Xs
      for (int i = c; i < board.length; i++){
        board[r][i]++;
      }
      //diagonal up
      for (int i = 0; r-i >= 0 && c+i < board.length; i++){
        board[r-i][c+i]++;
      }
      //diagonal down
      for (int i = 0; r+i < board.length && c+i < board.length; i++){
        board[r+i][c+i]++;
      }
      board[r][c] = -1;
      return true;
    }
  }

  private boolean removeQueen(int r, int c){
    if (r >= board.length || c >= board.length || board[r][c] != -1){
      return false;
    } else {
      //horizontal Xs
      for (int i = c; i < board.length; i++){
        board[r][i]--;
      }
      //diagonal up
      for (int i = 0; r-i >= 0 && c+i < board.length; i++){
        board[r-i][c+i]--;
      }
      //diagonal down
      for (int i = 0; r+i < board.length && c+i < board.length; i++){
        board[r+i][c+i]--;
      }
      board[r][c] = 0;
      return true;
    }
  }

  //prints out numbers
  public String debugString(){
    String s = "";
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board.length; j++){
        if (board[i][j] == -1){
          s += board[i][j] + " ";
        } else {
          s += " " + board[i][j] + " ";
        }
      }
      s += "\n";
    }
    return s;
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String s = "";
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board.length; j++){
        if (board[i][j] == -1){
          s += "Q ";
        } else {
          s += "_ ";
        }
      }
      s += "\n";
    }
    return s;
  }

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board.length; j++){
        if (board[i][j] != 0){
          throw new IllegalStateException("Board is not empty");
        }
      }
    }
    ArrayList<Integer> queens = new ArrayList<>();
    return solveHelp(0,0,queens);

  }

  private boolean solveHelp(int r, int c, ArrayList<Integer> queens){
    if (c >= board.length){
      return true;
    }
    if (c == 0 && r >= board.length){
      return false;
    }
    if (addQueen(r,c)){
      queens.add(r);
      for (int i = 0; i < board.length; i++){
        return solveHelp(i,c+1,queens);
      }
      int last = queens.get(queens.size()-1);
      removeQueen(last,c-1);
      queens.remove(queens.size()-1);
      return solveHelp(last+1,c,queens);
    } else if (r >= board.length){
      int last = queens.get(queens.size()-1);
      removeQueen(last,c-1);
      queens.remove(queens.size()-1);
      return solveHelp(last+1,c-1,queens);
    } else {
      return solveHelp(r+1,c,queens);
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    ArrayList<Integer> queens = new ArrayList<>();
    return countHelp(0,0,0,queens);
  }

  private int countHelp(int r, int c, int count, ArrayList<Integer> queens){
    if (c >= board.length){
      count++;
    }
    if (c == 0 && r >= board.length){
      return count;
    }
    //System.out.println(debugString());
    //System.out.print("("+r+", "+c+")");
    //System.out.println();
    if (addQueen(r,c)){
      queens.add(r);
      //System.out.println("EmptySpot");
      for (int i = 0; i < board.length; i++){
        //System.out.println("GoingDown1");
        return countHelp(i,c+1,count,queens);
      }
      int last = queens.get(queens.size()-1);
      removeQueen(last,c-1);
      queens.remove(queens.size()-1);
      //System.out.println("GoingBack1");
      return countHelp(last+1,c,count,queens);
    } else if (r >= board.length){
      int last = queens.get(queens.size()-1);
      removeQueen(last,c-1);
      queens.remove(queens.size()-1);
      //System.out.println("GoingBack2");
      return countHelp(last+1,c-1,count,queens);
    } else {
      //System.out.println("GoingDown2");
      return countHelp(r+1,c,count,queens);
    }
  }

  public static void main(String[] args){

    QueenBoard b = new QueenBoard(4);
    System.out.println(b.debugString());
    System.out.println(b);

    b.addQueen(0,0);
    System.out.println(b.debugString());
    System.out.println(b);

    b.addQueen(2,1);
    System.out.println(b.debugString());
    System.out.println(b);

    b.removeQueen(0,0);
    System.out.println(b.debugString());
    System.out.println(b);

  }

}
