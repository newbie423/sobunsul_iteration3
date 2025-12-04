package CoreEngine.DataBase;

import CoreEngine.Instance.*;
import java.util.*;

/**
 * LoanDB 클래스는 Loan 클래스로 생성한 객체들을 저장하기 위한 클래스 이다
 *
 * @author (정윤성)
 * @version (2025.11.20)
 */
public class LoanDB
{
    private ArrayList<Loan> loanDB;

    public LoanDB()
    {
        this.loanDB = new ArrayList<Loan>();
    }
    
    public void addLoan(Loan loan) {
        this.loanDB.add(loan);
    }
    
    public void deleteLoan(Loan loan) {
        this.loanDB.remove(loan);
    }
}