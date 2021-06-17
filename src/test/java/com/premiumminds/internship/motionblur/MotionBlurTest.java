package com.premiumminds.internship.motionblur;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2021.
 */
@RunWith(JUnit4.class)
public class MotionBlurTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public MotionBlurTest() {
  };

  private int M1result[][] = { { 3, 3, 4 }, { 4, 5, 6 }, { 6, 7, 8 } };

  @Test
  public void MotionBlurSingleThreadM1With5WorkersTest()
      throws InterruptedException, ExecutionException, TimeoutException {
    Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M1, 5);
    int[][] result = step1.get(10, TimeUnit.SECONDS);
    assertTrue(Arrays.deepEquals(result, M1result));
  }

  @Test
  public void MotionBlurMultiThreadM1With5WorkersTest()
      throws InterruptedException, ExecutionException, TimeoutException {
    Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M1, 5);
    int[][] result = step1.get(10, TimeUnit.SECONDS);
    assertTrue(Arrays.deepEquals(result, M1result));
  }

  /**
   * Implement more tests...
   */

  @Test
  public void MotionBlurSingleThreadM1With1WorkerTest()
      throws InterruptedException, ExecutionException, TimeoutException {
    Future<int[][]> step1 = new MotionBlurSingleThread().run(MatrixData.M1, 1);
    int[][] result = step1.get(10, TimeUnit.SECONDS);
    assertTrue(Arrays.deepEquals(result, M1result));
  }



   
  private void printHelper(int[][] matrix) {
    for (int l = 0; l < matrix.length; l++) {
      for (int c = 0; c < matrix[0].length; c++) {
        System.out.print(matrix[l][c] + " ");
      }
      System.out.println(" ");
    }
  }

}
