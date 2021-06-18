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
  private ExecutorService _submitter = Executors.newSingleThreadExecutor();
  private ExecutorService _executor;
  private Runnable _matrixBlurCalculator;

  /**
   * Method that executes the requested number of threads to calculate the
   * Motion Blur
   * 
   * @param matrix
   * @param remaining_workers
   */
  public void executeThreads(Matrix matrix, int remaining_workers){
    if(remaining_workers == 0){
      return;
    }

    _executor.execute(_matrixBlurCalculator);
    executeThreads(matrix, remaining_workers-1);
  }

  /**
   * Method that executes the threads and waits for them to finish before retur-
   * ning
   * 
   * @param matrix
   * @param numberOfWorkers
   */
  public void createThreads(Matrix matrix, int numberOfWorkers){
    executeThreads(matrix, numberOfWorkers);
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
    _executor = Executors.newFixedThreadPool(numberOfWorkers);
    _matrixBlurCalculator = new MatrixMotionBlur(matrix, _result);

    createThreads(matrix, numberOfWorkers);

    return _submitter.submit(() -> {
      return _result.getData();
    });
  }
}
