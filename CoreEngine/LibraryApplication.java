package CoreEngine;

import java.util.*;
import java.time.*;

import CoreEngine.DataBase.*;
import CoreEngine.Instance.*;

/**
 * LibraryApplication를 구현한 시스템 클래스
 * 도서관직원의 요청을 받아 책의 관한 처리를 실행한다.
 *
 * @author (정윤성, 윈파파한, 오번가 수영)
 * @version (2025.11.26)
 */
public class LibraryApplication
{
    private BorrowerDB borrowerDB = null;
    private BookDB bookDB = null;
    private LoanDB loanDB = null;
    private BookLogDB bookLogDB = null;

    /*
     * 생성자는 사용할 각종 데이터 베이스들에 대한 초기화를 진행한다
     * 
     * @param 없음
     */
    public LibraryApplication(){
        this.borrowerDB = new BorrowerDB();
        this.bookDB = new BookDB();
        this.loanDB = new LoanDB();
        this.bookLogDB = new BookLogDB();
    }

    /*
     * 이 메소드는 이용자 1명을 등록하는 메소드이다
     * 
     * @param int borrowerID -> 등록할 이용자의 아이디
     * @param String name -> 등록할 이용자의 이름
     * 
     * @return 없음
     */
    public String registerOneBorrower(int borrowerID, String name) {
        if(this.borrowerDB.borrowerIDCheck(borrowerID)){
            return "이용자 등록 실패 : " + "아이디 " + "\"" + borrowerID + "\"" + "이(가) 이미 존재하여 이용자를 등록에 사용할 수 없습니다";
        }

        Borrower borrower = new Borrower(borrowerID, name);

        this.borrowerDB.addBorrower(borrower);

        return "이용자 등록 성공 : " + "\"" + borrower + "\"" + "이(가) 성공적으로 이용자 등록 되었습니다";
    }

    /*
     * 이 메소드는 책 1권을 등록하는 메소드이다
     * 
     * @param int bookID -> 등록할 책의 아이디
     * @param String title -> 등록할 책의 제목
     * @param String author -> 등록할 책의 저자
     * 
     * @return 없음
     */
    public String registerOneBook(int bookID, String title, String author) {
        if(this.bookDB.bookIDCheck(bookID)){
            return "책 등록 실패 : " + "아이디 " + "\"" + bookID + "\"" + "이(가) 이미 존재하여 이용자를 등록에 사용할 수 없습니다";
        }

        Book book = new Book(bookID, title, author);

        this.bookDB.addBook(book);

        this.bookLogDB.addBookRegisterLog(book);

        return "책 등록 성공 : " + "\"" + book + "\"" + "이(가) 성공적으로 책 등록 되었습니다";
    }

    /*
     * 이 메소드는 이용자가 책 1권을 대출 하는 메소드이다
     * 
     * @param int bookID -> 대출할 책 아이디
     * @param int borrowerID -> 대출하는 이용자
     * 
     * @return 없음
     */
    public String loanOneBook(int bookID, int borrowerID) {
        Book loanBook = bookDB.findBook(bookID);
        Borrower loanBorrower = borrowerDB.findBorrower(borrowerID);

        if(loanBook == null){
            return "책이 존재하지 않음";
        }else if(loanBorrower == null){
            return "이용자가 존재하지 않음";
        }

        System.out.println(loanBook + "찾기 성공");
        System.out.println(loanBorrower + "찾기 성공");

        boolean bookCheck = loanBook.loanAbleCheck();
        boolean borrowerCheck = loanBorrower.loanAbleCheck();

        if(bookCheck == false && borrowerCheck == false){
            return loanBook +"와 " + loanBorrower + "은 대출불가";
        }else if(!bookCheck){
            return loanBook + "는 대출불가";
        }else if(borrowerCheck == false) {
            return loanBorrower + "는 대출불가";
        }

        System.out.println("책 대출 가능");

        this.bookLogDB.addBookLoanLog(loanBook, loanBorrower);
        Loan loan = new Loan(loanBorrower, loanBook);
        loanDB.addLoan(loan);

        loanBook.connectLoan(loan);
        loanBorrower.connectLoan(loan);

        return loan + "";
    }

    /*
     * 이 메소드는 이용자가 책 1권을 반납 하는 메소드이다
     * 
     * @param int bookID -> 반납할 책 아이디
     * 
     * @return 없음
     */
    public String returnOneBook(int bookID) {
        Book returnBook = bookDB.findBook(bookID);

        if(returnBook == null){
            return "도서관에 수장된 책이 아님";
        }

        System.out.println("반납할 책 : " + returnBook);

        Loan loan = returnBook.findConnectLoan();

        if(loan == null) {
            return "대출 중인 책이 아님";
        }

        Borrower returnBorrower = loan.findConnectBorrower();
        System.out.println("이용자 검색 완료");

        this.loanDB.deleteLoan(loan);

        returnBook.disconnectLoan();
        returnBorrower.disconnectLoan(loan);

        loan.setReturnedDate(LocalDate.now());

        returnBorrower.connectLoaned(loan);
        returnBook.connectLoaned(loan);

        this.bookLogDB.addBookReturnLog(returnBook, returnBorrower);

        String message = "";

        message += "---반납 성공---" + "\n";
        message += "대출정보 : " + loan + "\n";
        message += "반납된 책 : " + returnBook + "\n";
        message += "반납한 이용자 : " + returnBorrower + "\n";

        return message;
    }

    /*
     * 이 메소드는 대출 가능한 모든 책을 표시하는 메소드이다
     * 
     * @param 없음
     * 
     * @return 없음
     */
    public String displayBookAbleLoan() {
        if(this.bookDB.emptyCheck()){
            return "책DB에 등록된 책이 없습니다";
        }

        String message = "";

        while(true){
            Book book = this.bookDB.getOneBook();

            if(book == null){
                break;
            }

            // 대출이 되어 있지 않다고 한다면 대출 가능 여부가 true가 됨
            if(book.loanAbleCheck()){
                message += book + "\n";
            }
        }

        if(message.equals("")){
            return "대출 가능한 책이 한권도 없습니다";
        }

        return message;
    }

    /*
     * 이 메소드는 대출 중인 모든 책을 표시하는 메소드이다
     * 
     * @param 없음
     * 
     * @return 없음
     */
    public String displayBookUnableLoan() {
        if(this.bookDB.emptyCheck()){
            return "책DB에 등록된 책이 없습니다";
        }

        String message = "";

        while(true){
            Book book = this.bookDB.getOneBook();

            if(book == null){
                break;
            }

            // 이미 대출이 되어 있다고 한다면 대출 가능 여부가 false가 됨
            if(book.loanAbleCheck() == false){
                message += book + "\n";
            }
        }

        if(message.equals("")){
            return "대출 중인 책이 한권도 없습니다";
        }

        return message;
    }

    /*
     * 이 메소드는 책DB속 모든 책을 표시하는 메소드이다
     * 
     * @param 없음
     * 
     * @return 없음
     */
    public String displayAllBook() {
        if(this.bookDB.emptyCheck()){
            return "책DB에 등록된 책이 없습니다";
        }

        String message = "";

        while(true){
            Book book = this.bookDB.getOneBook();

            if(book == null){
                break;
            }

            message += book + "\n";
        }

        return message;
    }

    /*
     * 이 메소드는 이용자의 책 대출 기록을 표시하는 메소드이다
     * 
     * @param borrowerID - 책 대출 기록을 표시할 이용자의 아이디
     * 
     * @return 없음
     */
    public String displayBorrowerLoanLog(int borrowerID){
        Borrower borrower = this.borrowerDB.findBorrower(borrowerID);

        if(borrower == null){
            return "대출 기록 표시 실패 : " + "\"" + borrowerID + "\"" + "에 해당되는 이용자가 존재하지 않아 대출 기록을 출력할 수 없습니다\n";
        }

        ArrayList<Loan> loaned = borrower.getLoaned();

        if(loaned.size() <= 0){
            return "대출 기록 없음";
        }

        String loanedString = "";

        for(Loan loan : borrower.getLoaned()){
            loanedString += loan + "\n";
        }

        if(loanedString.equals("")){
            return "";
        }

        return "\"" + borrower + "\"가 대출했던 책들\n" + loanedString;
    }

    /*
     * 이 메소드는 책과 관련하여 수행된 모든 작업 내용을 표시하는 메소드이다
     * 
     * @param 없음
     * 
     * @return 없음
     */
    public String displayAllBookLogs(){
        if(this.bookLogDB.emptyCheck()){
            return "책 관련 작업 기록 없음";
        }

        String message = "";

        while(true){
            String bookLog = this.bookLogDB.getOneBookLog();

            if(bookLog == null){
                break;
            }

            message += bookLog + "\n";
        }

        return message;
    }

    /*
     * 이 메소드는 임이의 책을 대출한 이용자들의 기록을 표시하는 메소드이다
     * 
     * @param bookID - 책을 대출한 이용자의 기록을 표시할 책의 아이디
     * 
     * @return 없음
     */
    public String displayBookLoanLog(int bookID){
        Book book = this.bookDB.findBook(bookID);

        if(book == null){
            return "대출 기록 표시 실패 : " + "\"" + bookID + "\"" + "에 해당되는 책이 존재하지 않아 대출 기록을 출력할 수 없습니다\n";
        }

        ArrayList<Loan> loaned = book.getLoaned();

        if(loaned.size() <= 0){
            return "대출 기록 없음";
        }

        String loanedString = "";

        for(Loan loan : book.getLoaned()){
            loanedString += loan + "\n";
        }

        if(loanedString.equals("")){
            return "";
        }

        return "\"" + book + "\"를 대출했었던 이용자들\n" + loanedString;
    }

    /*
     * 이 메소드는 이용자DB속 모든 이용자를 표시하는 메소드이다
     * 
     * @param 없음
     * 
     * @return 없음
     */
    public String displayAllBorrower() {
        if(this.borrowerDB.emptyCheck()){
            return "이용자 DB에 등록된 책이 없습니다";
        }

        String message = "";

        while(true){
            Borrower borrower = this.borrowerDB.getOneBorrower();

            if(borrower == null){
                break;
            }

            message += borrower + "\n";
        }

        return message;
    }
}