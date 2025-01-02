public class Cell {
    private String value;

    public Cell(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //a method that validates if value of cell is a number
    boolean isNumber(){
    int numOfDots=0;


    //return false if number is build like (-.5)
    if (((int)getValue().charAt(0)=='-'&&(int)getValue().charAt(1)=='.')){
            return false;
    }

    //check if first char of text isnt between 0-9 and not minus sign, if its not then return false
    if (((int)getValue().charAt(0)>57||(int)getValue().charAt(0)<48)&&(int)getValue().charAt(0)!='-'){
        return false;
    }
    //check if first char of text isnt between 0-9 and not dot sign if its not then return false and also count the number of dots
    for (int i=1;i<getValue().length();i++){
        if ((int)getValue().charAt(i)!='.'&&((int)getValue().charAt(i)>57||(int)getValue().charAt(i)<48)){
            return false;
        }
        if ((int)getValue().charAt(i)=='.'){
            numOfDots++;
        }
    }
    if (numOfDots>1){
        return false;
    }
    //if last char is dot, if it is then return false
    if ((int)getValue().charAt(getValue().length()-1)=='.'){
        return false;
    }


    return true;
    }




    public String toString() {
        return getValue();
    }
}
