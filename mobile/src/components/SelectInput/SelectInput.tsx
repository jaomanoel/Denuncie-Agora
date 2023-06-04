import React from "react";
import SelectDropdown from "react-native-select-dropdown";

import * as S from "./styles";
import { SelectInputProps } from "./type";

const SelectInput: React.FC<SelectInputProps> = ({
  label,
  data,
  onSelect,
  errorMessage,
  isIndex,
  defaultValue
}) => {
  return (
    <S.container>
      <S.label>{label}</S.label>
      <SelectDropdown
        defaultButtonText={defaultValue}
        buttonStyle={{ width: "100%", height: 48, borderRadius: 10 }}
        data={data}
        onSelect={(selectedItem, index) => {
          onSelect(isIndex ? index + 1 : selectedItem);
        }}
        buttonTextAfterSelection={(selectedItem) => {
          return selectedItem;
        }}
        rowTextForSelection={(item) => {
          return item;
        }}
      />
      {errorMessage && <S.error>{errorMessage}</S.error>}
    </S.container>
  );
};

export { SelectInput };
