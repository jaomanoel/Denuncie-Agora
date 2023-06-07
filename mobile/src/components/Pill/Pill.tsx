import React from "react";

import * as S from "./styles";

const Pill: React.FC<PillProps> = ({ label, isSelected, onClick }) => {
  function capitalizeFirstLetter(str: string) {
    return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
  }

  return (
    <S.container isSelected={isSelected} onPress={() => onClick(label)}>
      <S.label isSelected={isSelected}>{capitalizeFirstLetter(label)}</S.label>
    </S.container>
  );
};

export { Pill };
