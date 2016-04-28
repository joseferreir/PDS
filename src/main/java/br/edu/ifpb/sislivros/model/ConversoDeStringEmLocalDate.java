/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sislivros.model;

import java.time.LocalDate;

/**
 *
 * @author José
 */
public class ConversoDeStringEmLocalDate {

    public static LocalDate converteStringEmLocalDate(String data) {
        String[] DataSeparada = data.split("/");
        int year, Month, dayOfMonth;
        year = Integer.parseInt(DataSeparada[2]);
        Month = Integer.parseInt(DataSeparada[1]);
        dayOfMonth = Integer.parseInt(DataSeparada[0]);
        LocalDate d = LocalDate.of(year, Month, dayOfMonth);

        return LocalDate.of(year, Month, dayOfMonth);

    }

}
