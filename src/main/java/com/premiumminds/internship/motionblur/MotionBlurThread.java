package com.premiumminds.internship.motionblur;

import java.util.Arrays;

public class MotionBlurThread implements Runnable{
    private Matrix _data;
    private Matrix _result;
    private int[] _coord;

    public MotionBlurThread(Matrix data, Matrix result, int[] coord){
        _data = data;
        _result = result;
        _coord = coord;
    }

    public int calcPixelBlur(int[] coord, Matrix matrix){
        int[] adjacent = matrix.getAdjacentElements(coord);
        int sum = Arrays.stream(adjacent).sum() + matrix.getElement(coord);
        
        double aux = (double) sum / (double) (adjacent.length+1);
        int Blur = (int) Math.round(aux);
        return Blur;
    }

    public void run(){
        _result.addElement(calcPixelBlur(_coord, _data), _coord);
    }

}
