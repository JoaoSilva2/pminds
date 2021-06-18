package com.premiumminds.internship.motionblur;

import java.util.Arrays;

/**
 * Class that represents the structure of a matrix
 */
public class Matrix{

    private int[][] _data;
    private int[] _map;
    private int _height;
    private int _width;

    public Matrix(int height, int width) {
        _data = new int[height][width];
        _map = new int[height*width];
        _height = height;
        _width = width;
    }
    public Matrix(int[][] data){
        _data = data;
        _height = data.length;
        _width = data[0].length;
    }

    public int getHeight(){
        return _height;
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
        return y == _height - 1 && x == _width - 1;
    }

    /**
     * Method check if the coordinates given correspond to the last element of
     * the matrix
     * @param coord
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
     * @param coord
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
        if(!outOfBounds(y, x)){
            _data[y][x] = value;

            int map_index = x + y*_width;
            _map[map_index] = -1;

        }
    }

    /**
     * Method that adds an element to the matrix on the given coordinates
     * @param value
     * @param coord
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
        return y >= _height || x >= _width ||
               y < 0 || x < 0;
    }

    /**
     * Method that checks if coordinates corresponds to an out of bounds locati-
     * on
     * @param coord
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
        int new_y = new_x < x ? (y+1)%_height : y;

        new_coord[0] = new_y;
        new_coord[1] = new_x;

        return new_coord;
    }

    /**
     * Method that calculates the next position in the matrix left to right
     * @param coord
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
        
        adjacentElements[0] = outOfBounds(y, x-1) ? -1 : _data[y][x-1];
        adjacentElements[1] = outOfBounds(y-1, x) ? -1 : _data[y-1][x];
        adjacentElements[2] = outOfBounds(y+1, x) ? -1 : _data[y+1][x];

        return Arrays.stream(adjacentElements).filter(i -> i != -1).toArray();
    }

    /**
     * Method that gets the adjacent elements of a coordinate.
     * left, above, below
     * @param coord
     * @return array of integers
     */
    public int[] getAdjacentElements(int[] coord){
        return getAdjacentElements(coord[0], coord[1]);
    }

    /**
     * Method that returns the number of elements of the matrix
     * @return integer
     */
    public int getNumberOfElements(){
        return _width*_height;
    }

    /**
     * Method that checks if all positions
     * @return
     */
    public boolean isComplete(){
        int[] aux = Arrays.stream(_map).filter(i -> i == -1).toArray();

        return aux.length == _width*_height;
    }
}
