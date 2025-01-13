import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
// Add your documentation below:

public class Ex2Sheet implements Sheet {
    private Cell[][] table;
    // Add your code here

    // ///////////////////
    public Ex2Sheet(int x, int y) {
        table = new SCell[x][y];
        for(int i=0;i<x;i=i+1) {
            for(int j=0;j<y;j=j+1) {
                table[i][j] = new SCell(Ex2Utils.EMPTY_CELL);
            }
        }
        eval();
    }
    public Ex2Sheet() {
        this(Ex2Utils.WIDTH, Ex2Utils.HEIGHT);
    }
    public static  final String[] ABC= {"A","B","C","D","E","F","G","H","I","J","K","L","O","M","N","P","Q","R","S","T","U","V","W","X","Y","Z"};


    @Override
    public String value(int x, int y) {
        String ans = Ex2Utils.EMPTY_CELL;
        // Add your code here



        Cell c = get(x,y);

        if (isText(c.toString())){
            ans=c.toString();
//            Text = 2;
        }

        try {


            if (!c.toString().isEmpty() && c.toString() != null && isForm(c.toString())) {
                String s;
                int[] ABCCOUNT = new int[ABC.length];
                int indx = 0;
                int indxL = 0;
                // s = computeForm("="+ "10" + "+" + "2").toString();
                s = (c.toString());

                // s = computeForm("=" + value(0,0) + "+" + c).toString();
                boolean complex = false;
                for (int i = 0; i < ABC.length; i++) {
                    if (s.contains(ABC[i])) {
                        complex = true;
                        ABCCOUNT[i]++;
                        indx = i;
                    }
                }
                //get indx of Letter
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ABC[indx].charAt(0)) {
                        indxL = i;
                    }
                }


// Manual tracking of visited cells using an array
                String[] visitedCells = new String[100]; // Adjust size as needed
                int visitedIndex = 0;

                while (complex) {
                    System.out.println(s + " initial string");

                    for ( indxL = 0; indxL < s.length(); indxL++) {
                        if (indxL + 1 < s.length() && isDigit(s.charAt(indxL + 1))) {
                            // Handle case where the cell reference ends at the next digit
                            int endIndex = indxL + 2; // Initially assume the reference is two characters long

                            // Check if the next character is also a digit (for multi-digit row numbers)
                            while (endIndex < s.length() && isDigit(s.charAt(endIndex))) {
                                endIndex++;
                            }

                            // Extract the cell reference
                            String cell = s.substring(indxL, endIndex);
                            System.out.println(cell + " cell reference found");

                            // Check for circular reference by searching in visitedCells
                            for (int i = 0; i < visitedIndex; i++) {
                                if (visitedCells[i].equals(cell)) {
                                    System.out.println("Circular reference detected: " + cell);
                                    s = "ERR_CYCLE_FORM"; // Return the error result
                                    complex = false;     // Exit the loop
                                    break;
                                }
                            }

                            if ("ERR_CYCLE_FORM".equals(s)) {
                                break; // Break outer loop if error was set
                            }

                            // Add the cell to visitedCells
                            visitedCells[visitedIndex++] = cell;

                            // Simulate recursive cell resolution
                            String cellValue = getCellValue(cell); // Resolve the cell value
                            if ("ERR_CYCLE_FORM".equals(cellValue)) {
                                s = "ERR_CYCLE_FORM"; // Propagate the error if detected in recursion
                                complex = false;     // Exit the loop
                                break;
                            }

                            // Replace the cell reference with its value
                            s = replaceSubstring(s, indxL, endIndex, cellValue);
                            System.out.println(s + " after replacement");

                            // Remove the cell from visitedCells after processing
                            visitedIndex--;

                            // Update the loop index to skip over the replaced part
                            indxL = indxL + cellValue.length() - 1; // Adjust based on replacement
                        }
                    }

                    if ("ERR_CYCLE_FORM".equals(s)) {
                        break; // Exit the outer loop if error was set
                    }

                    // Check if the string still contains any unresolved cell references
                    complex = false; // Assume no more unresolved references
                    for (int i = 0; i < ABC.length; i++) {
                        if (s.contains(ABC[i])) {
                            complex = true;
                            break;
                        }
                    }

                    System.out.println("Processed string: " + s);
                }

                s = computeForm(s).toString();

                ans = s;
            }
        }
        catch (StackOverflowError d){
            set(x,y,"ERR_CYCLE_FORM");
            ans=c.toString();
        }

        catch (ArrayIndexOutOfBoundsException d){
            set(x,y,"ERR_CYCLE_FORM");
            ans=c.toString();
        }


        if (isNumber(c.toString())){
            ans=c.toString();
        }
        else if (!isText(c.toString())&&!isText(c.toString())&&!isForm(c.toString())){
            ans = "ERR_FORM_FORMAT";
        }
        if(c.toString().isEmpty()) {ans = c.toString();}

        /////////////////////
//        System.out.println(ans + " this");
        return ans;
    }
    public  String replaceSubstring(String original, int start, int end, String replacement) {
        // Validate indices
        // Perform the replacement
        return original.substring(0, start) + replacement + original.substring(end);
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

    @Override
    public Cell get(int x, int y) {
        return table[x][y];
    }

    @Override
    public Cell get(String cords) {
        Cell ans = null;
        // Add your code here

        /////////////////////
        return ans;
    }

    @Override
    public int width() {
        return table.length;
    }
    @Override
    public int height() {
        return table[0].length;
    }
    @Override
    public void set(int x, int y, String s) {
        Cell c = new SCell(s);
        table[x][y] = c;
        // Add your code here

        /////////////////////
    }
    @Override
    public void eval() {
        int[][] dd = depth();
        // Add your code here

        // ///////////////////
    }

    @Override
    public boolean isIn(int x, int y) {
        boolean ans = x>=0 && y>=0;
        // Add your code here

        /////////////////////
        return ans;
    }

    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        // Add your code here

        // ///////////////////
        return ans;
    }

    @Override
    public void load(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }

    @Override
    public void save(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }

    @Override
    public String eval(int x, int y) {
        String ans = null;
        if(get(x,y)!=null) {
            ans = get(x,y).toString();
        }


        // Add your code here

        /////////////////////
        return ans;
    }




    public String getCellValue(String CellValue){
        int indxL=0;
        int indx=0;
        int place=0;

        int[] ABCCOUNT=new int[ABC.length];

        for (int i=0;i<ABC.length;i++) {
            if (CellValue.contains(ABC[i])){
                ABCCOUNT[i]++;
                indx=i;
            }
        }
        //get indx of Letter
        for (int i = 0; i < CellValue.length(); i++) {
            if (CellValue.charAt(i)==ABC[indx].charAt(0)){
                indxL=i;
            }
        }
        int yOfCell=0;

        boolean complex=false;
        for (int i=0;i<ABC.length;i++) {
            if (CellValue.contains(ABC[i])){
                complex=true;
                ABCCOUNT[i]++;
                indx=i;
            }
        }

        if(complex) {
            if (isDigit(CellValue.charAt(indxL+1))) {
                System.out.println(indxL);
                if (CellValue.length()==3) {


                    yOfCell = combineCharsToInt(CellValue.charAt(indxL + 1), CellValue.charAt(indxL + 2));

                    System.out.println(CellValue.charAt(indxL + 1));

                    //here
                }
                else if (CellValue.length()==2) {

                    yOfCell = CellValue.charAt(indxL + 1) - 48;

                }
                else if (isDigit(CellValue.charAt(indxL + 2))) {
                    System.exit(55);
                }
            }
            else {

                //System.exit(333);
            }


            for (int i=0;i<ABC.length;i++) {
                if (CellValue.contains(ABC[i])){
                    indx=i;
                }
            }
        }





        return value(letterToNumber(ABC[indx].charAt(0)),yOfCell);
    }
    public static int combineCharsToInt(char char1, char char2) {
        // Combine the two characters into a string and parse it as an integer
        String combined = "" + char1 + char2;
        return Integer.parseInt(combined);
    }

    public static int letterToNumber(char letter) {
        // Ensure the letter is uppercase and convert to its corresponding number
        return letter - 'A';
    }
    public  boolean isDigit(int number) {
        if (Integer.toString(number).isEmpty()||Integer.toString(number)==null){
            return false;
        }
        // Check if the number is a single digit (0-9)
        return Character.isDigit(number);
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




}
