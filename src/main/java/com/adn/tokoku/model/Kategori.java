package com.adn.tokoku.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("kategori")
@NoArgsConstructor
public class Kategori {
    @Id
    private int id;
    private String nama;
}
