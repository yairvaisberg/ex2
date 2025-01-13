// Add your documentation below:

public class SCell implements Cell {
    private String line;
    private int type;
    // Add your code here

    public SCell(String s) {
        // Add your code here

        setData(s);
    }

    @Override
    public int getOrder() {
        // Add your code here


        return 0;
        // ///////////////////
    }

    //@Override
    @Override
    public String toString() {
        return getData();
    }

    @Override
    public void setData(String s) {
        // Add your code here

        System.out.println(s + " type ");

        if (isNumber(s))
            type=2;
        if (isForm(s))
            type=3;
        if (!isText(s)&&!isForm(s)&&!isNumber(s)){
            type= -2;
        }
        if(s.equals("ERR_CYCLE_FORM")){
            type= -1;
        }




        line = s;

        /////////////////////
    }
    @Override
    public String getData() {
        return line;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int t) {
        type = t;
    }

    @Override
    public void setOrder(int t) {
        // Add your code here

    }

    public boolean isNumber(String texts){
        int numOfDots=0;

        //if value is empty or null its not a number
        if (texts==null||texts.isEmpty()){
            return false;
        }

        //return false if number is build like (-.5)
        if (((int)texts.charAt(0)=='-'&&(int)texts.charAt(1)=='.')){
            return false;
        }

        //check if first char of text isnt between 0-9 and not minus sign, if its not then return false
        if (((int) texts.charAt(0)>57||(int) texts.charAt(0)<48)&&(int) texts.charAt(0)!='-'){
            return false;
        }
        //check if first char of text isnt between 0-9 and not dot sign if its not then return false and also count the number of dots
        for (int i = 1; i< texts.length(); i++){
            if ((int) texts.charAt(i)!='.'&&((int) texts.charAt(i)>57||(int) texts.charAt(i)<48)){
                return false;
            }
            if ((int) texts.charAt(i)=='.'){
                numOfDots++;
            }
        }
        if (numOfDots>1){
            return false;
        }
        //if last char is dot, if it is then return false
        if ((int) texts.charAt(texts.length()-1)=='.'){
            return false;
        }
        return true;
    }

    public boolean isForm(String text){
        if (text.isEmpty()){
            return false;
        }
        if (text.charAt(0)!='='){
            return false;
        }
//        int base=1;
//        int scanner=0;
//        String helper;
//        int indOfOp=2;

        if (!checkCloserAndOpener(text)){
            return false;
        }

        if (text.contains("(")){
            if (!simplecheck(text)){
                return false;
            }

        }

        else {
            if (!simplecheck(text)){
                return false;
            }
        }
        return true;
    }

    //check if '(' and ')' are allowed
    public boolean checkCloserAndOpener(String text){

        int numOfOpen=0;
        int numOfClose=0;

        for (int i = 1; i <text.length(); i++) {
            if (text.charAt(i)=='('){
                numOfOpen++;
            }
            if (text.charAt(i)==')'){
                numOfClose++;
            }
            if (text.charAt(1)==')'||text.charAt(text.length()-1)=='('){
                return false;
            }
            if (numOfClose>numOfOpen){
                return false;
            }
            if (text.contains("()")||text.contains(")(")){
                return false;
            }
            if (text.contains(" ")){
                return false;
            }
        }
        if (numOfClose!=numOfOpen){
            return false;
        }

        return true;
    }


    public boolean simplecheck(String text){

        int base=1;
        String helper;
        int indOfOp=2;
        int indStart=1;

        while (text.charAt(indStart)=='('){
            base++;
            indOfOp++;
            indStart++;
        }
        while (indOfOp<text.length()-1){

            if (text.charAt(indOfOp)=='('){
                base = indOfOp+2;
                indOfOp++;
            }
            if (text.charAt(indOfOp)==')'){
                indOfOp=indOfOp+2;
                base = indOfOp+1;
            }
            if (text.charAt(indOfOp)=='+'||text.charAt(indOfOp)=='-'||text.charAt(indOfOp)=='*'||text.charAt(indOfOp)=='/'){
                helper=text.substring(base,indOfOp);

                if (!isNumber(helper)){
                    return false;
                }
                base=indOfOp+1;
            }
            indOfOp++;
            if (indOfOp>=text.length()){
                indOfOp--;
            }
            if (text.charAt(indOfOp)=='+'||text.charAt(indOfOp)=='-'||text.charAt(indOfOp)=='*'||text.charAt(indOfOp)=='/'){
                indOfOp++;
            }
        }
        if (indOfOp==text.length()){
            helper=text.substring(base,indOfOp);
            if (!isNumber(helper)){

                return false;
            }
        }
        if (text.charAt(text.length()-1)!=')'&&!isNumber(Character.toString(text.charAt(text.length()-1)))){
            return false;
        }
        return true;
    }
    public boolean isText(String text){

        if (text.isEmpty())
            return false;

        if (isNumber(text)) {
            return false;
        }
        if (text.charAt(0)=='='){
            return false;
        }
        return true;
    }

}
