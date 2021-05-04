package com.example.demo.customer;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Area codes start with a number 2–9, followed by 0–8, and then any third digit.
     *
     * The second group of three digits, known as the central office or exchange code, starts with a number 2–9,
     * followed by any two digits.
     *
     * The final four digits, known as the station code, have no restrictions.
     */
    @Pattern(  message = "Phone Number Not Valid", regexp = "^\\(?([2-9][0-8][0-9])\\)?[-. ]?([2-9][0-9]{2})[-. ]?([0-9]{4})$")
    @Phone(id="foo")
    private String phoneNumber;
    @NotEmpty(message = "{ValidationError.FirstName}")
    private String firstName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private List<Address> address;
}
