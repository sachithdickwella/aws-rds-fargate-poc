package com.fargate.paf.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.StringJoiner;

/**
 * @author Sachith Dickwella
 */
@Entity(name = "paf_data")
@Data
@NoArgsConstructor
public class PAF {

    @NotNull
    @Id
    private String id;

    @NotNull
    @Column
    private String address;

    @NotNull
    @Column(name = "zipcode")
    private int zipCode;

    @Override
    public String toString() {
        return new StringJoiner(", ", "", "")
                .add(String.valueOf(id))
                .add(address)
                .add(String.valueOf(zipCode))
                .toString();
    }
}
