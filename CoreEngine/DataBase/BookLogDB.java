package CoreEngine.DataBase;


import java.util.*;

import CoreEngine.Instance.*;

/**
 * BookLogDB는 책과 관련하여 수행된 모든 작업을 저장하기 위한 클래스이다
 *
 * @author (니시 야스히로)
 * @version (20025.12.06)
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
    
    public boolean emptyCheck(){
        return this.bookLogDB.isEmpty();
    }
    
    private Iterator<String> it = null;
    // 책 작업 기록 DB의 iterator를 생성한뒤 매번 호출될 때마다 다음요소를 전달하며,
    // 모든 책 작업 기록에 대한 순회가 끝난 경우 iterator를 다시 초기화 하는 메소드이다
    public String getOneBookLog(){
        if(this.it == null){ // iterator가 초기화 되어 있으면 새롭게 생성
            this.it = this.bookLogDB.iterator();
        }
        
        String bookLog = null;
        
        if(it.hasNext()){ // 다음 책이 있을때만, 다음책을 받아오기
            bookLog = this.it.next();
        }else{ // 
            this.it = null; // 다음 책이 없으면 현재 iterator에 대한 모든 책을 다 순회한 것이므로 다음번 사용을 위해 초기화
        }
        
        return bookLog;
    }
}
