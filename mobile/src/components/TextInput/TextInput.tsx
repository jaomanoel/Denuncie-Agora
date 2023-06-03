import React from "react";

import * as S from "./styles";
import { TextInputProps } from "./type";

const TextInput: React.FC<TextInputProps> = ({
  label,
  value,
  onBlur,
  onChangeText,
  placeholder,
  errorMessage,
  isDescription = false,
}) => {
  return (
    <S.container>
      <S.label>{label}</S.label>
      {isDescription ? (
        <S.description
          multiline
          onChangeText={onChangeText}
          onBlur={onBlur}
          value={value}
          placeholder={placeholder}
        />
      ) : (
        <S.input
          onChangeText={onChangeText}
          onBlur={onBlur}
          value={value}
          placeholder={placeholder}
        />
      )}
      {errorMessage && <S.error>{errorMessage}</S.error>}
    </S.container>
  );
};

export { TextInput };
