package com.bank.document.modal;


import lombok.Data;

@Data
public class Document {

    private Integer id;

    private String name;

    private String type;

    private byte[] data;

    private Post post;

}