package com.denuncieagora.denuncie.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HateCrimeTypeEnum {
    RACISM (1),
    XENOPHOBIA (2),
    HOMOPHOBIA (3),
    RAPE (4),
    KIDNAPPING (5);

    private Integer id;
}
