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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(  message = "{BranchCode.customer.branch}", regexp = "^\\(?([2-9][0-8][0-9])\\)?[-. ]?([2-9][0-9]{2})[-. ]?([0-9]{4})$")
    @Phone(id="foo")
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private List<Address> address;
}
