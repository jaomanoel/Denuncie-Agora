package com.denuncieagora.denuncie.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HateCrimeTypeEnum {
    RACISMO (1),
    XENOFOBIA (2),
    HOMOFOBIA (3),
    TRANSFOBIA (4),
    INSLAMOFOBIA (5),
    ANTISEMITA (6),
    IDADISMO (7);

    private Integer id;
}
