package com.example.jpaexample;

import com.example.jpaexample.entity.Book;
import java.math.BigDecimal;

public class BookExample {
    public static void main(String[] args) {
        // 1. СОЗДАЕМ ОБЪЕКТ КНИГИ
        Book book = new Book();
        book.setTitle("Introduction to Java");
        book.setAuthor("JetBrains");
        book.setPublisher("E-Docs");
        book.setPublishYear(2026);
        book.setPrice(new BigDecimal("25.50"));

        // 2. СОХРАНЯЕМ В БАЗУ
        BookJpaExample.saveBook(book);
        System.out.println("Книга сохранена. Присвоен ID: " + book.getId());

        // 3. ПРОВЕРЯЕМ ОБНОВЛЕНИЕ (ДЗ)
        // Берем ID только что созданной книги и меняем ей цену
        if (book.getId() != null) {
            System.out.println("--- Тестируем обновление ---");
            BookJpaExample.updateBookPrice(book.getId(), new BigDecimal("99.99"));
        }

        // 4. ПРОВЕРЯЕМ ПОИСК ПО ID
        Book foundBook = BookJpaExample.findBookById(book.getId());
        System.out.println("Результат поиска после обновления: " + foundBook);

        // 5. ПРОВЕРЯЕМ УДАЛЕНИЕ (ДЗ)
        System.out.println("--- Тестируем удаление ---");
        BookJpaExample.deleteBook(book.getId());

        // 6. ФИНАЛЬНАЯ ПРОВЕРКА (Спискок всех книг)
        System.out.println("--- Итоговый список книг в базе ---");
        BookJpaExample.findAllBooks();
    }
}
