package CoreEngine.DataBase;


import java.util.*;

import CoreEngine.Instance.*;

/**
 * BorrowerDB 클래스는, Borrower 클래스로 생성한 객체들을 저장하기 위한 클래스 이다.
 *
 * @author (니시 야스히로)
 * @version (2025.11.20)
 */
public class BorrowerDB{
    private TreeSet<Borrower> borrowerDB = null;

    // 생성자에서는 초기화를 진행한다
    public BorrowerDB(){
        this.borrowerDB = new TreeSet<Borrower>(Comparator.comparingInt(Borrower::getBorrowerID));
    }

    // 이용자를 추가하는데 사용되는 메소드 이다
    public void addBorrower(Borrower borrower){ this.borrowerDB.add(borrower); }

    // 이용자의 아이디가 존재하는지 확인하는 메소드이다
    public boolean borrowerIDCheck(int borrowerID){
        Borrower borrower =  this.borrowerDB.ceiling(new Borrower(borrowerID, ""));

        if(borrower == null){
            return false;
        }

        if(borrower.getBorrowerID() == borrowerID){
            return true;
        }

        return false;
    }

    // 아이디에 해당되는 이용자 객체를 반환하는 메소드이다
    public Borrower findBorrower(int borrowerID){
        Borrower borrower = this.borrowerDB.ceiling(new Borrower(borrowerID, ""));

        if(borrower == null){
            return null;
        }

        if(borrower.getBorrowerID() == borrowerID){
            return borrower;
        }

        return null;
    }
}
