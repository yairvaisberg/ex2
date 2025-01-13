public class CellMain {
    public static void main(String []args){

        Part1Cell cell1 = new Part1Cell("=15.03+2");


     //   System.out.println(cell1.isNumber(cell1.texts()));
//
       //System.out.println(cell1.isValidnumbercatch(cell1.getValue()));
//
//        System.out.println(cell1.isText(cell1.getValue()));
//
//        System.out.println(cell1);


     //System.out.println(cell1.isForm(cell1.texts()));

        System.out.println(cell1.computeForm(cell1.text()));


    }
}
