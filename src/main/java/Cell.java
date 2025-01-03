public class Cell {
    private String value;

    public Cell(String value) {
        this.value = value;
    }

    public String texts() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //a method that validates if value of cell is a number
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

    public boolean isValidnumbercatch(String texts) {
        try {
            double d = Double.parseDouble(texts());// returns double primitive
        } catch (NumberFormatException | NullPointerException | StringIndexOutOfBoundsException d) {
            return false;
        }
        String text = texts();
        char firstChar = text.charAt(0);
        if (text.charAt(text.length() - 1) == '.' || firstChar == '.' || firstChar == '+') {
            return false;
        }
        if (((int) texts().charAt(0) == '-' && (int) texts().charAt(1) == '.')) {
            return false;
        }

        return true;
    }


    public boolean isText(String text){

        if (isNumber(texts())) {
            return false;
        }
        if (texts().charAt(0)=='='){
            return false;
        }
        return true;
    }

    public boolean isForm(String text){
        if (texts().charAt(0)!='='){
            return false;
        }
        int numOfOpen=0;
        int numOfClose=0;
        int base=1;
        int scanner=0;
        String helper;
        int indOfOp=2;

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
        }
        if (text.contains("(")){


        }


        else {
            for (int i = 2; i <text.length(); i++) {
               if (text.charAt(i)=='+'||text.charAt(i)=='-'||text.charAt(i)=='*'||text.charAt(i)=='/'){

                   helper=text.substring(base,indOfOp);
                   if (!isNumber(helper)){
                       return false;
                   }
                   base=indOfOp+1;
                }
               indOfOp++;
            }
            if (indOfOp==text.length()){
                helper=text.substring(base,indOfOp);
                if (!isNumber(helper)){
                    return false;
                }
            }
        }

        if (numOfClose!=numOfOpen){
            return false;
        }



        return true;
    }

//נחשב את המחשבון הסופי לסוגריים בעזרת סקאנר ובסיס. הסקאנר עובר עד שהוא מוצא פתח סוגריים ואז שם שם את הסבסיס ואחרי סורק לעוד פתח או סגור סוגריים
    //אם מוצא פתח סוגריים הוא מעביר לשם את הבסיס
    //אם הוא מוצא סגור סוגריים הוא נשאר שם ואז יש את האינדקס של הסוגריים הכי פניניים
    //אחרי זה מעבירים את הביטוי בתוך הסוגריים לסטרינג חדש פותרים ואז מחליפים את התוצאה בסטרינג המקורי
    //עושים זאת עד שלא נשאר סוגריים ואחרי זה פשוט פותרים רגיל



    public String toString() {
        return texts();
    }
}
