package com.premiumminds.internship.motionblur;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2021.
 */
class MotionBlurSingleThread implements MotionBlurFactory {
  private Matrix _result;
  private ExecutorService executor = Executors.newSingleThreadExecutor();
  
  public int calcPixelBlur(int[] coord, Matrix matrix){
    int[] adjacent = matrix.getAdjacentElements(coord);
    int sum = Arrays.stream(adjacent).sum() + matrix.getElement(coord);
    
    double aux = (double) sum / (double) (adjacent.length+1);
    int Blur = (int) Math.round(aux);
    return Blur;
  }

  public int[][] calcMotionBlur(int[] coord, Matrix matrix){
    if(matrix.lastElement(coord)){
      _result.addElement(calcPixelBlur(coord, matrix), coord);
      return _result.getData();
    }

    _result.addElement(calcPixelBlur(coord, matrix), coord);
    return calcMotionBlur(matrix.nextCoord(coord), matrix);
  }

  /**
   * Method to start processing the data
   * 
   * @param data            matrix of integers
   * @param numberOfWorkers number of threads that should work in parallel
   * @return matrix of integers
   */
  public Future<int[][]> run(int[][] data, int numberOfWorkers) {
    int[] initial_coord = {0, 0};

    Matrix mat = new Matrix(data);
    _result = new Matrix(mat.getHeight(), mat.getWidth());

    return executor.submit(() -> {
      return calcMotionBlur(initial_coord, mat);
    });
  };
}
