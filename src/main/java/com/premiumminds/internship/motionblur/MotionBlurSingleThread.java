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
  private ExecutorService _submitter = Executors.newSingleThreadExecutor();
  private ExecutorService _executor = Executors.newSingleThreadExecutor();
  
  /**
   * Method that executes a single thread to calculate Motion Blur
   * @param matrix
   */
  public void executeThread(Matrix matrix){
    Runnable matrixBlurCalculator = new MatrixMotionBlur(matrix, _result);
    _executor.execute(matrixBlurCalculator);
    _executor.shutdown();
    while (!_executor.isTerminated()) {
    }
  }

  /**
   * Method to start processing the data
   * 
   * @param data            matrix of integers
   * @param numberOfWorkers number of threads that should work in parallel
   * @return matrix of integers
   */
  public Future<int[][]> run(int[][] data, int numberOfWorkers) {
    Matrix matrix = new Matrix(data);
    _result = new Matrix(matrix.getHeight(), matrix.getWidth());

    executeThread(matrix);

    return _submitter.submit(() -> {
      return _result.getData();
    });
  };
}
