package com.meli.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Setter @Getter
@Table("adn")
public class Adn {

    @PrimaryKey
    private UUID id;
    private List<String> dna;
    private boolean response;
    private Date date;
}
