package com.gym.mscommandlibrarybookstored.Aggregate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String isbn;
    private String title;
    private String editeurId;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private LibraryAggregate library;
}