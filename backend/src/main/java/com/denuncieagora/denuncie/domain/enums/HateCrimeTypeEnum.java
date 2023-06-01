package com.denuncieagora.denuncie.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HateCrimeTypeEnum {
    RACISMO (1),
    XENOFOBIA (2),
    HOMOFOBIA (3),
    ABUSO (4),
    SEQUESTRO (5);

    private Integer id;
}
