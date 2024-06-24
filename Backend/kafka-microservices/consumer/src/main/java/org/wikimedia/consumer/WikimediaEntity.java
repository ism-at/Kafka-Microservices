package org.wikimedia.consumer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wikimedia_table")
@Getter
@Setter
public class WikimediaEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob // This column will be CLOB (Character Large Object)
    @Column(name = "wikimedia_event_data", columnDefinition = "LONGTEXT")
    private String wikimedia_event_data;
}
