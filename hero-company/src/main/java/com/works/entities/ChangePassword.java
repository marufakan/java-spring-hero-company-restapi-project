package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;


@Data
public class ChangePassword {
    @Id
    private Long id;

    @Length(message = "the password length should be at least 5 and at most 10",min = 5, max = 10)
    private  String firstPassword;//regex kullanılacak (en az 5 karakter en fazla 10 karakter, büyük küçük harf rakam ve özel karakter ile sınırlandırılmalıdır)
    @Length(message = "the password length should be at least 5 and at most 10",min = 5, max = 10)
    private  String newPassword;//regex kullanılacak (en az 5 karakter en fazla 10 karakter, büyük küçük harf rakam ve özel karakter ile sınırlandırılmalıdır)
//
}
