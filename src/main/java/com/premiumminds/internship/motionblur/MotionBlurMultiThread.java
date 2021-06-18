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
  private ExecutorService executor = Executors.newSingleThreadExecutor();
  private Runnable worker;

  public void calcMotionBlur(Matrix matrix, ExecutorService executor, int remaining_workers){
    if(remaining_workers == 0){
      return;
    }
    //Runnable worker = new MatrixMotionBlur(matrix, _result);
    executor.execute(worker);
    calcMotionBlur(matrix, executor, remaining_workers-1);
  }

  public void executeThreads(Matrix matrix, int numberOfWorkers){
    ExecutorService executor = Executors.newFixedThreadPool(numberOfWorkers);
    calcMotionBlur(matrix, executor, numberOfWorkers);
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
    worker = new MatrixMotionBlur(mat, _result);
    executeThreads(mat, numberOfWorkers);

    return executor.submit(() -> {
      return _result.getData();
    });
  }
}
