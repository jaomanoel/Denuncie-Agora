import React from "react";
import { Controller, UseControllerProps, FieldValues } from "react-hook-form";
import { SelectInput, SelectInputProps } from "@components/SelectInput";

const ControlledSelectInput = <T extends FieldValues>({
  control,
  name,
  rules,
  ...selectInputProps
}: UseControllerProps<T> & SelectInputProps) => {
  return (
    <Controller
      control={control}
      name={name}
      rules={rules}
      render={({ field, fieldState }) => (
        <SelectInput
          {...selectInputProps}
          onSelect={field.onChange}
          errorMessage={fieldState.error?.message}
        />
      )}
    />
  );
};

export { ControlledSelectInput };
