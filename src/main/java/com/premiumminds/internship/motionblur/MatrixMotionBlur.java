package com.premiumminds.internship.motionblur;

import java.util.Arrays;

/**
 * Created by aamado on 05-05-2021.
 */
class MatrixMotionBlur implements Runnable {
  
  private Matrix _data;  
  private Matrix _result;
  private int[] _coord = {0, 0};
  private int[] _map;

  public MatrixMotionBlur(Matrix data, Matrix result){
      _data = data;
      _result = result;
      _map = new int[_data.getWidth()*_data.getHeight()];
  }
  
  /**
   * Method that calculates the blur of a single pixel, rounding up to the near-
   * est integer
   * @param coord
   * @return integer
   */
  public int calcPixelBlur(int[] coord){
    int[] adjacent = _data.getAdjacentElements(coord);

    int sum = Arrays.stream(adjacent).sum() + _data.getElement(coord);
    double aux = (double) sum / (double) (adjacent.length+1);
    int Blur = (int) Math.round(aux);
    return Blur;
  }

  /**
   * Method that checks if the given coordinate has already been processed to
   * avoid useless calculating the same value twice.
   * @param coord
   * @return boolean
   */
  public boolean checkAndUpdateCoordinate(int[] coord){
    int map_index = coord[0] + coord[1]*_data.getWidth();
    if(_map[map_index] == -1){
        return true;
    }
    _map[map_index] = -1;
    _coord = _data.nextCoord(_coord);
    return false;
  }

  /**
   * Method that recursively calculates motion blur.
   * Returns when all position have been processed.
   */
  public void recursiveMatrixMotionBlur(){
    if(_result.isComplete()){
        return;
    }

    int[] current_coord;
    synchronized(this){
      current_coord = _coord;
      boolean checked = checkAndUpdateCoordinate(current_coord);
      if(checked){
        recursiveMatrixMotionBlur();
        return;
      }
    }

    _result.addElement(calcPixelBlur(current_coord), current_coord);
    recursiveMatrixMotionBlur();  
  }

  public void run() {
    recursiveMatrixMotionBlur();
  }
}
