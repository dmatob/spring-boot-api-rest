package com.dmatob.sandbox.springboot.infrastructure.api.soap.adapter;
 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
 
public class LocalDateAdapter extends XmlAdapter<String, LocalDate>
{
 
    public LocalDate unmarshal(String inputDate) {
        return inputDate != null ? DateTimeFormatter.ISO_DATE.parse(inputDate, LocalDate::from) : null ;
    }
 
    public String marshal(LocalDate inputDate) {
        return inputDate != null ? DateTimeFormatter.ISO_DATE.format(inputDate) : null;
    }
}
