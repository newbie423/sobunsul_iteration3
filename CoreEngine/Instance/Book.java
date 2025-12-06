package CoreEngine.Instance;


/**
 * Book 클래스는 책의 정보를 담고 있는 객체를 생성하는데 사용되는 클래스 이다.
 *
 * @author (니시 야스히로)
 * @version (2025.12.06)
 */
public class Book{
    private int bookID;
    private String title = null;
    private String author = null;

    private Loan loan = null;

    public Book(int bookID, String title, String author){
        this.bookID = bookID;
        this.title = title;
        this.author = author;
    }

    // 대출 건수와 관계를 형성하는 메소드이다
    public void connectLoan(Loan loan){
        this.loan = loan;
    }

    // 대출 건수와 관계를 제거하는 메소드이다
    public void disconnectLoan(){
        this.loan = null;
    }

    // 현재 관계를 형성하고 있는 대출 객체를 반환하는 메소드이다
    public Loan findConnectLoan(){
        return this.loan;
    }

    // 책이 대출 될 수 있는 상태인지 확인하는 메소드이다
    public boolean loanAbleCheck(){
        return this.loan == null;
    }

    public String toString(){
        return this.bookID + " " + this.title + " " + this.author;
    }

    public int getBookID(){ return this.bookID; }
}