import React from "react";
import DateTimePickerModal from "react-native-modal-datetime-picker";

import * as S from "./styles";
import { DateInputProps } from "./type";

const DateInput: React.FC<DateInputProps> = ({
  errorMessage,
  isDatePickerVisible,
  handleDate,
  handleDatePicker,
  date,
  mode,
}) => {
  return (
    <>
      <S.container>
        <S.dateContainer>
          <S.label>Data do ocorrido:</S.label>

          <S.datePicker onPress={handleDatePicker}>
            <S.dateLabel>
              {date ? (
                <S.dateLabel>
                  {date.toISOString().slice(0, 10).replace(/-/g, "/")}
                </S.dateLabel>
              ) : (
                <S.dateLabel>Selecionar data</S.dateLabel>
              )}
            </S.dateLabel>
          </S.datePicker>
        </S.dateContainer>

        {errorMessage && <S.error>{errorMessage}</S.error>}
      </S.container>

      <DateTimePickerModal
        isVisible={isDatePickerVisible}
        mode={mode}
        onConfirm={(date: Date) => {
          handleDate(date);
          handleDatePicker();
        }}
        onCancel={handleDatePicker}
      />
    </>
  );
};

export { DateInput };
