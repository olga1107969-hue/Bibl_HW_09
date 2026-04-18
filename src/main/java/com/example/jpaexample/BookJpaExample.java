package com.example.jpaexample;

import com.example.jpaexample.entity.Book;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class BookJpaExample {

    // Создаем фабрику один раз для всего приложения
    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("library-persistence-unit");

    // СОХРАНЕНИЕ (Уже было у препода)
    public static void saveBook(Book book) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(book);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // ПОИСК ПО ID
    public static Book findBookById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    // --- ДЗ: ОБНОВЛЕНИЕ (Update) ---
    public static void updateBookPrice(Long id, BigDecimal newPrice) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Book book = em.find(Book.class, id); // Ищем объект в базе
            if (book != null) {
                book.setPrice(newPrice); // Меняем данные
                et.commit(); // Hibernate сам сделает UPDATE
                System.out.println("Цена обновлена для ID: " + id);
            } else {
                System.out.println("Книга с ID " + id + " не найдена.");
            }
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // --- ДЗ: УДАЛЕНИЕ (Delete) ---
    public static void deleteBook(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Book book = em.find(Book.class, id);
            if (book != null) {
                em.remove(book); // Удаляем объект
                et.commit(); // Hibernate сделает DELETE
                System.out.println("Книга с ID " + id + " удалена.");
            } else {
                System.out.println("Нечего удалять, ID " + id + " не найден.");
            }
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // ПОЛУЧЕНИЕ ВСЕХ КНИГ (JPQL)
    public static void findAllBooks() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
            List<Book> books = query.getResultList();
            for (Book book : books) {
                System.out.println(book);
            }
        } finally {
            em.close();
        }
    }
}