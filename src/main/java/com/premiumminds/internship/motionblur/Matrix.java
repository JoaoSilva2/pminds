package com.premiumminds.internship.motionblur;

/**
 * Class that represents the structure of a matrix
 */
public class Matrix{

    private int[][] _data;
    private int _heigth;
    private int _width;

    public Matrix(int heigth, int width) {
        _data = new int[heigth][width];
        _heigth = heigth;
        _width = width;
    }
    public Matrix(int[][] data){
        _data = data;
        _heigth = data.length;
        _width = data[0].length;
    }

    public int getHeigth(){
        return _heigth;
    }

    public int getWidth(){
        return _width;
    }

    public int[][] getData(){
        return _data;
    }

    /**
     * Method check if the coordinates given correspond to the last element of
     * the matrix
     * @param y
     * @param x
     * @return boolean
     */
    public boolean lastElement(int y, int x){
        return y == _heigth - 1 && x == _width - 1;
    }

    /**
     * Method check if the coordinates given correspond to the last element of
     * the matrix
     * @param y
     * @param x
     * @return boolean
     */
    public boolean lastElement(int[] coord){
        return lastElement(coord[0], coord[1]);
    }

    /**
     * Method to get an element from the matrix in the corresponding coordinate
     * @param y
     * @param x
     * @return integer
     */
    public int getElement(int y, int x){
        if(outOfBounds(y, x)){
            System.out.println("Out of Bounds");
            return -1;
        }

        return _data[y][x];
    }

    /**
     * Method to get an element from the matrix in the corresponding coordinate
     * @param y
     * @param x
     * @return integer
     */
    public int getElement(int[] coord){
        return getElement(coord[0], coord[1]);
    }

    /**
     * Method that adds an element to the matrix on the given coordinates
     * @param value
     * @param y
     * @param x
     */
    public void addElement(int value, int y, int x){
        _data[y][x] = value;
    }

    /**
     * Method that adds an element to the matrix on the given coordinates
     * @param value
     * @param y
     * @param x
     */
    public void addElement(int value, int[] coord){
        addElement(value, coord[0], coord[1]);
    }

    /**
     * Method that checks if coordinates corresponds to an out of bounds locati-
     * on
     * @param y
     * @param x
     * @return boolean
     */
    public boolean outOfBounds(int y, int x){
        return y >= _heigth || x >= _width ||
               y < 0 || x < 0;
    }

    /**
     * Method that checks if coordinates corresponds to an out of bounds locati-
     * on
     * @param y
     * @param x
     * @return boolean
     */
    public boolean outOfBounds(int[] coord){
        return outOfBounds(coord[0], coord[1]);
    }

    /**
     * Method that calculates the next position in the matrix left to right
     * @param y
     * @param x
     * @return array of integers
     */
    public int[] nextCoord(int y, int x){
        int[] new_coord = new int[2];

        int new_x = (x+1)%_width;
        int new_y = new_x < x ? (y+1)%_heigth : y;

        new_coord[0] = new_y;
        new_coord[1] = new_x;

        return new_coord;
    }

    /**
     * Method that calculates the next position in the matrix left to right
     * @param y
     * @param x
     * @return array of integers
     */
    public int[] nextCoord(int[] coord){
        return nextCoord(coord[0], coord[1]);
    }

    /**
     * Method that gets the adjacent elements of a coordinate.
     * left, above, below
     * @param y
     * @param x
     * @return array of integers
     */
    public int[] getAdjacentElements(int y, int x){
        int[] adjacentElements = new int[3];
        
        adjacentElements[0] = outOfBounds(y, x-1) ? 0 : _data[y][x-1];
        adjacentElements[1] = outOfBounds(y-1, x) ? 0 : _data[y-1][x];
        adjacentElements[2] = outOfBounds(y+1, x) ? 0 : _data[y+1][x];

        return adjacentElements;
    }
}
