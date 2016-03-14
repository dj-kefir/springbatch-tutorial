package ru.oz.mytutors.springbatch.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ozol on 15.03.2016.
 */
@Data
@XmlRootElement(name = "student")
public class Student {
    private String emailAddress;
    private String name;
    private String purchasedPackage;
}
