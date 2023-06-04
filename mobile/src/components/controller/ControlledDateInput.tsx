import React from "react";
import { Controller, UseControllerProps, FieldValues } from "react-hook-form";
import { DateInput, DateInputProps } from "@components/DateInput";

const ControlledDateInput = <T extends FieldValues>({
  control,
  name,
  rules,
  ...dateInputProps
}: UseControllerProps<T> & DateInputProps) => {
  return (
    <Controller
      control={control}
      name={name}
      rules={rules}
      render={({ field, fieldState }) => (
        <DateInput
          {...dateInputProps}
          date={field.value}
          handleDate={field.onChange}
          errorMessage={fieldState.error?.message}
        />
      )}
    />
  );
};

export { ControlledDateInput };
