package CoreEngine;


/**
 * 유스케이스 다이어그램에서 사서 역할을 겸하는 클래스
 * LibraryApplication 클래스를 사용해 책의 관한 처리를 진행한다.
 *
 * @author (정윤성, 니시야스히로, 윈파파한, 오번가수영)
 * @version (2025.11.26)
 */
public class Librarian_테스트용_추후_삭제
{
    public static void registerOneBorrowerTest(){
        LibraryApplication LA = new LibraryApplication();
        
        System.out.println(LA.registerOneBorrower(1, "b1"));
        System.out.println(LA.registerOneBorrower(2, "b2"));
        System.out.println(LA.registerOneBorrower(3, "b3"));
        
        System.out.println(LA.registerOneBorrower(1, "b4"));
    }
    
    public static void registerOneBookTest(){
        LibraryApplication LA = new LibraryApplication();
        
        System.out.println(LA.registerOneBook(1, "t1", "a1"));
        System.out.println(LA.registerOneBook(2, "t2", "a2"));
        System.out.println(LA.registerOneBook(3, "t3", "a3"));
        
        System.out.println(LA.registerOneBook(1, "t4", "a4"));
    }
    
    public static void loanOneBookTest(){
        LibraryApplication LA = new LibraryApplication();
        
        LA.registerOneBorrower(100, "b1");
        LA.registerOneBorrower(101, "b2");
        
        LA.registerOneBook(1, "t1", "a1");
        LA.registerOneBook(2, "t2", "a2");
        LA.registerOneBook(3, "t3", "a3");
        LA.registerOneBook(4, "t4", "a4");
        LA.registerOneBook(5, "t5", "a5");
        LA.registerOneBook(6, "t6", "a6");
        LA.registerOneBook(7, "t7", "a7");
        LA.registerOneBook(8, "t8", "a8");
        LA.registerOneBook(9, "t9", "a9");
        LA.registerOneBook(10, "t10", "a10");
        LA.registerOneBook(11, "t11", "a11");
        
        for(int num = 1; num <= 11; num++){
            System.out.println(LA.loanOneBook(num, 100));
        }
        
        System.out.println(LA.loanOneBook(1, 101));
    }
    
    public static void returnOneBookTest(){
        LibraryApplication LA = new LibraryApplication();
        
        LA.registerOneBorrower(100, "b1");
        LA.registerOneBorrower(101, "b2");
        
        LA.registerOneBook(1, "t1", "a1");
        LA.registerOneBook(2, "t2", "a2");
        
        System.out.println(LA.returnOneBook(0));
        System.out.println(LA.returnOneBook(1));
        
        LA.loanOneBook(1, 100);
        System.out.println(LA.returnOneBook(1));
    }
    
    public static void displayBookAbleLoanTest(){
        LibraryApplication LA = new LibraryApplication();
        
        System.out.println("===== 등록된책 없을때");
        System.out.println(LA.displayBookAbleLoan());
        
        LA.registerOneBook(1, "t1", "a1");
        LA.registerOneBook(2, "t2", "a2");
        LA.registerOneBook(3, "t3", "a3");
        
        System.out.println("===== 등록된책 있을때");
        System.out.println(LA.displayBookAbleLoan());
        
        LA.registerOneBorrower(100, "b1");
        
        LA.loanOneBook(1, 100);
        LA.loanOneBook(2, 100);
        
        System.out.println("===== 대출된 책 있을때");
        System.out.println(LA.displayBookAbleLoan());
        
        LA.loanOneBook(3, 100);
        
        System.out.println("===== 모든 책이 대출되었을때");
        System.out.println(LA.displayBookAbleLoan());
    }
    
    public static void displayBookUnableLoanTest(){
        LibraryApplication LA = new LibraryApplication();
        
        System.out.println("===== 등록된책 없을때");
        System.out.println(LA.displayBookUnableLoan());
        
        LA.registerOneBook(1, "t1", "a1");
        LA.registerOneBook(2, "t2", "a2");
        LA.registerOneBook(3, "t3", "a3");
        
        System.out.println("===== 대출된 책이 1권도 없을때(등록된 책은 있음)");
        System.out.println(LA.displayBookUnableLoan());
        
        LA.registerOneBorrower(100, "b1");
        
        LA.loanOneBook(1, 100);
        LA.loanOneBook(2, 100);
        LA.loanOneBook(3, 100);
        
        System.out.println("===== 대출된 책 있을때");
        System.out.println(LA.displayBookUnableLoan());
    }
    
    public static void displayAllBookTest(){
        LibraryApplication LA = new LibraryApplication();
        
        LA.registerOneBorrower(100, "b1");
        
        LA.registerOneBook(1, "t1", "a1");
        LA.registerOneBook(2, "t2", "a2");
        LA.registerOneBook(3, "t3", "a3");
        
        LA.loanOneBook(1, 100);
        
        System.out.println(LA.displayAllBook());
    }
    
    public static void displayBorrowerLoanLogTest(){
        LibraryApplication LA = new LibraryApplication();
        
        LA.registerOneBorrower(100, "b1");
        
        LA.registerOneBook(1, "t1", "a1");
        LA.registerOneBook(2, "t2", "a2");
        LA.registerOneBook(3, "t3", "a3");
        
        System.out.println("===== 빌렸었던 책이 없을때");
        System.out.println(LA.displayBorrowerLoanLog(100));
        
        LA.loanOneBook(1, 100); LA.returnOneBook(1);
        LA.loanOneBook(2, 100); LA.returnOneBook(2);
        
        System.out.println("===== 빌렸었던 책이 있을때");
        System.out.println(LA.displayBorrowerLoanLog(100));
    }
    
    public static void displayAllBookLogsTest(){
        LibraryApplication LA = new LibraryApplication();
        
        System.out.println("===== 책과 관련된 아무런 작업도 하지 않았을때");
        System.out.println(LA.displayAllBookLogs());
        
        LA.registerOneBorrower(100, "b1");
        
        LA.registerOneBook(1, "t1", "a1");
        LA.registerOneBook(2, "t2", "a2");
        LA.registerOneBook(3, "t3", "a3");
        
        LA.loanOneBook(1, 100); LA.returnOneBook(1);
        LA.loanOneBook(2, 100); LA.returnOneBook(2);
        
        System.out.println("===== 책과 관련된 여러 작업을 했을때");
        System.out.println(LA.displayAllBookLogs());
    }
    
    public static void displayAllBorrower(){
        LibraryApplication LA = new LibraryApplication();
        
        LA.registerOneBorrower(1, "b1");
        LA.registerOneBorrower(2, "b2");
        LA.registerOneBorrower(3, "b3");
        
        System.out.println(LA.displayAllBorrower());
    }
    
    public static void displayBookLoanLog(){
        LibraryApplication LA = new LibraryApplication();
        
        LA.registerOneBorrower(100, "b1");
        LA.registerOneBorrower(101, "b2");
        LA.registerOneBorrower(102, "b3");
        
        LA.registerOneBook(1, "t1", "a1");
        
        System.out.println("===== 빌렸었던 이용자가 없을때");
        System.out.println(LA.displayBookLoanLog(1));
        
        LA.loanOneBook(1, 100); LA.returnOneBook(1);
        LA.loanOneBook(1, 101); LA.returnOneBook(1);
        
        System.out.println("===== 빌렸었던 책이 있을때");
        System.out.println(LA.displayBorrowerLoanLog(1));
    }
}