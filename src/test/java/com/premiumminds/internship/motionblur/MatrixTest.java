package com.premiumminds.internship.motionblur;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2021.
 */
@RunWith(JUnit4.class)
public class MatrixTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public MatrixTest() {
  };

  private int[] coord1 = {0, 0};
  private int[] coord2 = {5, 2};
  private int[] coord3 = {2, 6};
  private int[] coord4 = {-5, -5};
  private int[] coord5 = {0, 2};

  private int[] nextCoordResult1 = {0, 1};
  private int[] nextCoordResult2 = {1, 0};

  private int[] adjacentResult1 = {4};
  private int[] adjacentResult2 = {2, 6};
 
  @Test
  public void MatrixWidthTest(){
    Matrix m1 = new Matrix(MatrixData.M1);
    int result = m1.getWidth();
    assertTrue(result == 3);
  }

  @Test
  public void MatrixHeightTest(){
    Matrix m1 = new Matrix(MatrixData.M1);
    int result = m1.getHeight();
    assertTrue(result == 3);
  }

  @Test
  public void MatrixOutOfBoundsTest1(){
    Matrix m1 = new Matrix(MatrixData.M1);
    assertFalse(m1.outOfBounds(coord1));
  }

  @Test
  public void MatrixOutOfBoundsTest2(){
    Matrix m1 = new Matrix(MatrixData.M1);
    assertTrue(m1.outOfBounds(coord2));
  }
  
  @Test
  public void MatrixOutOfBoundsTest3(){
    Matrix m1 = new Matrix(MatrixData.M1);
    assertTrue(m1.outOfBounds(coord3));
  }

  @Test
  public void MatrixOutOfBoundsTest4(){
    Matrix m1 = new Matrix(MatrixData.M1);
    assertTrue(m1.outOfBounds(coord4));
  }

  @Test
  public void MatrixNextCoordTest1(){
    Matrix m1 = new Matrix(MatrixData.M1);
    int[] result = m1.nextCoord(coord1);
    assertTrue(Arrays.equals(result, nextCoordResult1));
  }

  @Test
  public void MatrixNextCoordTest2(){
    Matrix m1 = new Matrix(MatrixData.M1);
    int[] result = m1.nextCoord(coord5);
    assertTrue(Arrays.equals(result, nextCoordResult2));
  }

  @Test
  public void MatrixAdjacentElementsTest1(){
    Matrix m1 = new Matrix(MatrixData.M1);
    int[] result = m1.getAdjacentElements(coord1);
    assertTrue(Arrays.equals(result, adjacentResult1));
  }

  @Test
  public void MatrixAdjacentElementsTest2(){
    Matrix m1 = new Matrix(MatrixData.M1);
    int[] result = m1.getAdjacentElements(coord5);
    assertTrue(Arrays.equals(result, adjacentResult2));
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
