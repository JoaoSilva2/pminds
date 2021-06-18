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
  private ExecutorService _executor = Executors.newSingleThreadExecutor();
  
  public void executeThread(Matrix matrix){
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Runnable worker = new MatrixMotionBlur(matrix, _result);
    executor.execute(worker);
    executor.shutdown();
    while (!executor.isTerminated()) {
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
    Matrix mat = new Matrix(data);
    _result = new Matrix(mat.getHeight(), mat.getWidth());

    executeThread(mat);

    return _executor.submit(() -> {
      return _result.getData();
    });
  };
}
