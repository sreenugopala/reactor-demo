package com.sreenivaasamu.demoz.sf5.reactordemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Soldier {

    private String firstName;
    private String lastName;

    public String salute(){
        return "Salute Officer, Mr. " + firstName + " " + lastName + ".";
    }

}