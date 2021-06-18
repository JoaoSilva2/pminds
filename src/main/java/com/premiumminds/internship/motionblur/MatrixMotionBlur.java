package com.premiumminds.internship.motionblur;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
  

  public int calcPixelBlur(int[] coord){
    int[] adjacent = _data.getAdjacentElements(coord);
    int sum = Arrays.stream(adjacent).sum() + _data.getElement(coord);
    
    double aux = (double) sum / (double) (adjacent.length+1);
    int Blur = (int) Math.round(aux);
    return Blur;
}

  public void recursiveMatrixMotionBlur(){
    if(_result.isComplete()){
        return;
    }

    int[] current_coord;
    synchronized(this){
        current_coord = _coord;
        int map_index = current_coord[0] + current_coord[1]*_data.getWidth();
        if(_map[map_index] == -1){
            recursiveMatrixMotionBlur();
            return;
        }
        _map[map_index] = -1;
        _coord = _data.nextCoord(_coord);
    }

    _result.addElement(calcPixelBlur(current_coord), current_coord);
    recursiveMatrixMotionBlur();  
  }

  public void run() {
    recursiveMatrixMotionBlur();
  }
}
