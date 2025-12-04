package CoreEngine.DataBase;


import java.util.*;

import CoreEngine.Instance.*;

/**
 * BookLogDB 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */
public class BookLogDB {
    ArrayList<String> bookLogDB = null;
    
    public BookLogDB(){
        this.bookLogDB = new ArrayList<String>();
    }
    
    public void addBookRegisterLog(Book book){
        bookLogDB.add("등록/" + book);
    }
    public void addBookLoanLog(Book book, Borrower borrower){
        bookLogDB.add("대출/" + book + "/" + borrower);
    }
    public void addBookReturnLog(Book book, Borrower borrower){
        bookLogDB.add("반납/" + book + "/" + borrower);
    }
    
    public String toString(){
        String bookLogs = "";
        
        for(String bookLog : this.bookLogDB){
            bookLogs += bookLog + "\n";
        }
        
        return bookLogs;
    }
}
