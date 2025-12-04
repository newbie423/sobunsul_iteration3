package CoreEngine.Instance;


import java.time.*;

/**
 * Loan 클래스는 Borrower와 Book간의 대출 관계를 형성하는데 사용하는 객체를 생성하는데 사용되는 클래스 이다.
 *
 * @author (정윤성)
 * @version (2025.11.20)
 */
public class Loan
{
    // 인스턴스 변수 - 다음의 예제를 사용자에 맞게 변경하세요.
    private Borrower borrower;
    private Book book;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LocalDate returnedDate = null;

    /**
     * Loan 클래스의 객체 생성자
     */
    public Loan(Borrower borrower, Book book)
    {
        this.borrower = borrower;
        this.book = book;
        this.loanDate = LocalDate.now();
        this.returnDate = loanDate.plusDays(14);
    }

    public Borrower findConnectBorrower() {
        return this.borrower;
    }

    public String toString(){
        String string = "";
        string += "대출일 : " + this.loanDate + "\n";
        string += "반납예정일 : " + this.returnDate + "\n";

        if(this.returnedDate == null){
            string += "반납일 : " + null + "\n";
        }else{
            string += "반납일 : " + this.returnedDate + "\n";
        }

        string += "책의 정보 : " + this.book + "\n";
        string += "이용자의 정보 : " + this.borrower + "\n";

        return string;
    }

    public void setReturnedDate(LocalDate returnedDate){
        this.returnedDate = returnedDate;
    }
}