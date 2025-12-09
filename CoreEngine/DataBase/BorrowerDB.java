package CoreEngine.DataBase;


import java.util.*;

import CoreEngine.Instance.*;

/**
 * BorrowerDB 클래스는, Borrower 클래스로 생성한 객체들을 저장하기 위한 클래스 이다.
 *
 * @author (니시 야스히로)
 * @version (2025.12.06)
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
    
    // 책이 없는지 확인하는 메소드이다
    public boolean emptyCheck(){
        return this.borrowerDB.isEmpty();
    }
    
    private Iterator<Borrower> it = null;
    // 책DB의 iterator를 생성한뒤 매번 호출될 때마다 다음요소를 전달하며,
    // 모든 책에 대한 순회가 끝난 경우 iterator를 다시 초기화 하는 메소드이다
    public Borrower getOneBorrower(){
        if(this.it == null){ // iterator가 초기화 되어 있으면 새롭게 생성
            this.it = this.borrowerDB.iterator();
        }
        
        Borrower borrower = null;
        
        if(it.hasNext()){ // 다음 책이 있을때만, 다음책을 받아오기
            borrower = this.it.next();
        }else{ // 
            this.it = null; // 다음 책이 없으면 현재 iterator에 대한 모든 책을 다 순회한 것이므로 다음번 사용을 위해 초기화
        }
        
        return borrower;
    }
}
