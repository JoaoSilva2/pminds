package com.premiumminds.internship.motionblur;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2021.
 */
class MotionBlurMultiThread implements MotionBlurFactory {

  private Matrix _result;
  private int[] _coord = {0, 0};
  private ExecutorService executor = Executors.newSingleThreadExecutor();

  public void calcMotionBlur(Matrix matrix, ExecutorService executor, int[] coord, int computed_pos){
    if(computed_pos == matrix.getNumberOfElements()){
      return;
    }
    Runnable worker = new MotionBlurThread(matrix, _result, coord);
    executor.execute(worker);

    calcMotionBlur(matrix, executor, matrix.nextCoord(coord), computed_pos+1);
  }

  public void executeThreads(Matrix matrix, int numberOfWorkers){
    ExecutorService executor = Executors.newFixedThreadPool(numberOfWorkers);
    calcMotionBlur(matrix, executor, _coord, 0);
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

    executeThreads(mat, numberOfWorkers);

    return executor.submit(() -> {
      return _result.getData();
    });
  }
}
