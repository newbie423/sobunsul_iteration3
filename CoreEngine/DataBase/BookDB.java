package CoreEngine.DataBase;


import java.util.*;

import CoreEngine.Instance.*;

/**
 * BookDB 클래스는 Book 클래스로 생성한 객체들을 저장하기 위한 클래스 이다.
 *
 * @author (작성자 이름)
 * @version (2025.11.20)
 */
public class BookDB{
    private TreeSet<Book> bookDB = null;
    public BookDB(){
        this.bookDB = new TreeSet<Book>(Comparator.comparingInt(Book::getBookID));
    }

    public void addBook(Book book){ this.bookDB.add(book); }

    // 책의 아이디가 존재하는지 확인하는 메소드이다
    public boolean bookIDCheck(int bookID){
        Book book =  this.bookDB.ceiling(new Book(bookID, "", ""));

        if(book == null){
            return false;
        }

        if(book.getBookID() == bookID){
            return true;
        }

        return false;
    }

    // 아이디에 해당되는 책 객체를 반환하는 메소드이다
    public Book findBook(int bookID){
        Book book = this.bookDB.ceiling(new Book(bookID, "", ""));

        if(book == null){
            return null;
        }

        if(book.getBookID() == bookID){
            return book;
        }

        return null;
    }

    // 책이 없는지 확인하는 메소드이다
    public boolean emptyCheck(){
        return this.bookDB.isEmpty();
    }

    private Iterator<Book> it = null;
    // 책DB의 iterator를 생성한뒤 매번 호출될 때마다 다음요소를 전달하며,
    // 모든 책에 대한 순회가 끝난 경우 iterator를 다시 초기화 하는 메소드이다
    public Book getOneBook(){
        if(this.it == null){ // iterator가 초기화 되어 있으면 새롭게 생성
            this.it = this.bookDB.iterator();
        }
        
        Book book = null;
        
        if(it.hasNext()){ // 다음 책이 있을때만, 다음책을 받아오기
            book = this.it.next();
        }else{ // 
            this.it = null; // 다음 책이 없으면 현재 iterator에 대한 모든 책을 다 순회한 것이므로 다음번 사용을 위해 초기화
        }
        
        return book;
    }
}