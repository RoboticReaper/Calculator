package com.hoversfw.calculator;

import android.util.Log;

import java.util.ArrayList;

//Idea:
//Count how many operators are there
//Base on that, prioritize calculations, create ArrayList and mark operator's chatAt()
//Use recursive function to calculate

public class Calculate {
    private ArrayList<Integer> addition=new ArrayList<>();
    private ArrayList<Integer> subtraction=new ArrayList<>();
    private ArrayList<Integer> division=new ArrayList<>();
    private ArrayList<Integer> multiplication=new ArrayList<>();
    private ArrayList<Integer> all=new ArrayList<>();
    private int left;
    private int right;

    public Calculate(String i){
        setOperatorCount(i);
    }

    public String calculate(String equation){
        Log.d("Received equation",equation);
        try{
            //If successful, then it means the equation is int
            Integer.parseInt(equation);
            return equation;
        } catch (NumberFormatException e){
            if(multiplication.size()!=0) {
                Log.d("Inside mult part","asdfasdfasdf");
                //If there is multiplication sign
                if (division.size() != 0) {
                    Log.d("Also inside div part","asdfasdf");
                    //If there is also division sign
                    if (multiplication.get(0) < division.get(0)) {
                        Log.d("Mult first","");
                        //If multiplication sign comes in front of division sign
                        //Get left and right side numbers of multiplication sign
                        int start = getLeftInt(equation, multiplication.get(0));
                        int end = getRightInt(equation, multiplication.get(0));
                        //Calculate result
                        int result = left * right;
                        //Make a new equation with calculated part replaced with result
                        String replacement1=equation.substring(start,multiplication.get(0)-1);
                        String replacement2=equation.substring(multiplication.get(0)+1,end);
                        //Remove that from ArrayList
                        multiplication.remove(0);
                        //Recursive
                        return calculate(replacement1+result+replacement2);
                    }
                    else{
                        Log.d("Div first","");
                        //If division sign comes in front of mult sign
                        int start = getLeftInt(equation, division.get(0));
                        int end = getRightInt(equation, division.get(0));
                        //Calculate result
                        int result = left/right;
                        //Make a new equation with calculated part replaced with result
                        String replacement1=equation.substring(start,division.get(0)-1);
                        String replacement2=equation.substring(division.get(0)+1,end);
                        //Remove that from ArrayList
                        division.remove(0);
                        //Recursive
                        return calculate(replacement1+result+replacement2);
                    }
                } else {
                    Log.d("Only mult","");
                    //If there is only multiplication sign
                    int start = getLeftInt(equation, multiplication.get(0));
                    int end = getRightInt(equation, multiplication.get(0));
                    int result = left * right;
                    String replacement1=equation.substring(start,multiplication.get(0)-1);
                    String replacement2=equation.substring(multiplication.get(0)+1,end);
                    multiplication.remove(0);
                    return calculate(replacement1+result+replacement2);
                }
            }
            if(division.size()!=0){
                Log.d("Inside div part only","");
                //If there's only division sign
                int start = getLeftInt(equation, division.get(0));
                int end = getRightInt(equation, division.get(0));
                //Calculate result
                int result = left/right;
                //Make a new equation with calculated part replaced with result
                String replacement1=equation.substring(start,division.get(0)-1);
                String replacement2=equation.substring(division.get(0)+1,end);
                //Remove that from ArrayList
                division.remove(0);
                //Recursive
                return calculate(replacement1+result+replacement2);
            }
            if(addition.size()!=0){
                Log.d("Reached addition part","asdfasdfasfdasdf");
                //If there's addition sign
                if(subtraction.size()!=0){
                    Log.d("There is subtraction","");
                    //If there's also subtraction sign
                    if(addition.get(0)<subtraction.get(0)){
                        Log.d("add in front of sub","");
                        //If add comes in front of sub
                        int start = getLeftInt(equation, addition.get(0));
                        int end = getRightInt(equation, addition.get(0));
                        //Calculate result
                        int result = left + right;
                        //Make a new equation with calculated part replaced with result
                        String replacement1=equation.substring(start,addition.get(0));
                        String replacement2=equation.substring(addition.get(0),end);
                        //Remove that from ArrayList
                        addition.remove(0);
                        //Recursive
                        return calculate(replacement1+result+replacement2);
                    }
                    else{
                        Log.d("Sub in front","");
                        //If sub comes in front of add
                        int start = getLeftInt(equation, subtraction.get(0));
                        int end = getRightInt(equation, subtraction.get(0));
                        //Calculate result
                        int result = left * right;
                        //Make a new equation with calculated part replaced with result
                        String replacement1=equation.substring(start,subtraction.get(0)-1);
                        String replacement2=equation.substring(subtraction.get(0)+1,end);
                        //Remove that from ArrayList
                        subtraction.remove(0);
                        //Recursive
                        return calculate(replacement1+result+replacement2);
                    }
                }
                else{
                    Log.d("Only add sign","");
                    //If there's only add sign
                    int start = getLeftInt(equation, addition.get(0));
                    int end = getRightInt(equation, addition.get(0));
                    //Calculate result
                    int result = left + right;
                    //Make a new equation with calculated part replaced with result
                    String replacement1=equation.substring(start,start);
                    String replacement2=equation.substring(end-1);
                    //Remove that from ArrayList
                    addition.remove(0);
                    //Recursive
                    return replacement1+result+replacement2;
                }
            }
            if(subtraction.size()!=0){
                int start = getLeftInt(equation, subtraction.get(0));
                int end = getRightInt(equation, subtraction.get(0));
                //Calculate result
                int result = left + right;
                //Make a new equation with calculated part replaced with result
                String replacement1=equation.substring(start,start);
                String replacement2=equation.substring(end-1);
                //Remove that from ArrayList
                subtraction.remove(0);
                //Recursive
                return replacement1+result+replacement2;
            }
        }
        return equation;
    }

    public int getLeftInt(String equation, int operatorIndex){
        int numStartIndex = operatorIndex;
        int result = 0;
        boolean exit=false;
        while(!exit){
            //Try going left every time
            numStartIndex-=1;
            try{
                //If it doesn't throw exception, means it works. Run again.
                result=Integer.parseInt(equation.substring(numStartIndex,operatorIndex));
            } catch(final IndexOutOfBoundsException | NumberFormatException e){
                //If there's an exception, means that's the beginning of left number. Exit loop and return it.
                numStartIndex+=1;
                Log.d("getLeftInt",""+numStartIndex);
                Log.d("getLeftInt",""+operatorIndex);
                result=Integer.parseInt(equation.substring(numStartIndex,operatorIndex));
                Log.d("GetLeftInt Result",""+result);
                exit=true;
            }
        }
        left=result;
        return numStartIndex;
    }

    public int getRightInt(String equation, int operatorIndex){
        int numEndIndex=operatorIndex+1;
        int result=1;
        boolean exit=false;
        while(!exit){

            //Try going right every time
            numEndIndex+=1;
            try{
                result=Integer.parseInt(equation.substring(operatorIndex,numEndIndex));
            } catch (final IndexOutOfBoundsException | NumberFormatException e){
                Log.d("getRightInt",""+operatorIndex);
                Log.d("getRightInt",""+numEndIndex);
                result=Integer.parseInt(equation.substring(operatorIndex,numEndIndex-1));
                Log.d("getRightInt Result",""+result);
                exit=true;
            }

        }
        right=result;
        return numEndIndex;
    }

    public void setOperatorCount(String equation){
        for(int i=0;i<equation.length();i++){
            switch(equation.charAt(i)){
                case '+':
                    addition.add(i);
                    all.add(i);
                    continue;
                case '-':
                    subtraction.add(i);
                    all.add(i);
                    continue;
                case '*':
                    multiplication.add(i);
                    all.add(i);
                    continue;
                case '/':
                    division.add(i);
                    all.add(i);
            }
        }
    }
}
