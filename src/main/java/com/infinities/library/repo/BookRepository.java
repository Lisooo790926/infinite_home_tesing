package com.infinities.library.repo;

import com.infinities.library.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {

    BookModel findByName(String name);
}
