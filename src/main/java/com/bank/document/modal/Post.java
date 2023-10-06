package com.bank.document.modal;


import com.bank.document.entity.Comments;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
public class Post {

    Integer Id;

    String userId;

    String title;

    String body;

}
