public class Part1Cell {
    private String value;

    public Part1Cell(String value) {
        this.value = value;
    }

    public String text() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isNumber(String texts) {
        int numOfDots = 0;

        // If value is empty or null, it's not a number
        if (texts == null || texts.isEmpty()) {
            return false;
        }

        // Return false if number is built like (-.5)
        if (texts.charAt(0) == '-' && texts.charAt(1) == '.') {
            return false;
        }

        // Check if the first char is valid
        if ((texts.charAt(0) < '0' || texts.charAt(0) > '9') && texts.charAt(0) != '-') {
            return false;
        }

        // Validate remaining characters and count dots
        for (int i = 1; i < texts.length(); i++) {
            char c = texts.charAt(i);
            if (c != '.' && (c < '0' || c > '9')) {
                return false;
            }
            if (c == '.') {
                numOfDots++;
            }
        }

        // Ensure at most one dot and the last character is not a dot
        return numOfDots <= 1 && texts.charAt(texts.length() - 1) != '.';
    }

    public boolean isValidnumbercatch(String texts) {
        try {
            double d = Double.parseDouble(text());// returns double primitive
        } catch (NumberFormatException | NullPointerException | StringIndexOutOfBoundsException d) {
            return false;
        }
        String text = text();
        char firstChar = text.charAt(0);
        if (text.charAt(text.length() - 1) == '.' || firstChar == '.' || firstChar == '+') {
            return false;
        }
        if (((int) text().charAt(0) == '-' && (int) text().charAt(1) == '.')) {
            return false;
        }
        return true;
    }


    public boolean isText(String text){


        if (isNumber(text())) {
            return false;
        }
        if (text().charAt(0)=='='){
            return false;
        }
        return true;
    }

    public boolean isForm(String text){
        if (text().charAt(0)!='='){
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






    public  Double computeForm(String form) {
        // Remove the '=' from the first character since it's already verified to exist.
        String expression = form.substring(1);

        return evaluateExpression(expression);
    }

    private  double evaluateExpression(String expression) {
        double[] numbers = new double[100]; // Stack for numbers
        char[] operators = new char[100];  // Stack for operators
        int numTop = -1, opTop = -1;
        int i = 0;

        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                // Parse number
                int start = i;
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    i++;
                }
                numbers[++numTop] = Double.parseDouble(expression.substring(start, i));
                continue;
            } else if (c == '(') {
                // Push '(' to operators stack
                operators[++opTop] = c;
            } else if (c == ')') {
                // Evaluate the expression inside parentheses
                while (opTop >= 0 && operators[opTop] != '(') {
                    numbers[numTop - 1] = applyOperator(operators[opTop--], numbers[numTop], numbers[numTop - 1]);
                    numTop--;
                }
                opTop--; // Remove '('
            } else if (isOperator(c)) {
                // Process operator
                while (opTop >= 0 && precedence(operators[opTop]) >= precedence(c)) {
                    numbers[numTop - 1] = applyOperator(operators[opTop--], numbers[numTop], numbers[numTop - 1]);
                    numTop--;
                }
                operators[++opTop] = c;
            }
            i++;
        }

        // Evaluate the remaining expression
        while (opTop >= 0) {
            numbers[numTop - 1] = applyOperator(operators[opTop--], numbers[numTop], numbers[numTop - 1]);
            numTop--;
        }

        return numbers[0];
    }

    private  boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private  int precedence(char operator) {
        if (operator == '+' || operator == '-') return 1;
        if (operator == '*' || operator == '/') return 2;
        return 0;
    }

    private  double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }




//נחשב את המחשבון הסופי לסוגריים בעזרת סקאנר ובסיס. הסקאנר עובר עד שהוא מוצא פתח סוגריים ואז שם שם את הסבסיס ואחרי סורק לעוד פתח או סגור סוגריים
    //אם מוצא פתח סוגריים הוא מעביר לשם את הבסיס
    //אם הוא מוצא סגור סוגריים הוא נשאר שם ואז יש את האינדקס של הסוגריים הכי פניניים
    //אחרי זה מעבירים את הביטוי בתוך הסוגריים לסטרינג חדש פותרים ואז מחליפים את התוצאה בסטרינג המקורי
    //עושים זאת עד שלא נשאר סוגריים ואחרי זה פשוט פותרים רגיל



    public String toString() {
        return text();
    }
}
