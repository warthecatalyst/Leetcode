package JavaLearning;

public class Test1 {
    public static void main(String[] args) throws MyExceptionOne, MyExceptionTwo {
        Test1 test1 = new Test1();
        test1.tryCatchOne();
    }

    public void tryCatchOne() throws MyExceptionOne,MyExceptionTwo{
        try {
            tryCatchTwo();
        }catch (MyExceptionTwo exceptionTwo){
            throw exceptionTwo;
        }finally {
            int n = 1;
            if(n%2==1){
                throw new MyExceptionOne();
            }
        }
    }

    public void tryCatchTwo() throws MyExceptionTwo{
        throw new MyExceptionTwo();
    }
}
