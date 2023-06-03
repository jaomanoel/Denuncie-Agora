import React from "react";
import { Controller, UseControllerProps, FieldValues } from "react-hook-form";
import { TextInput, TextInputProps } from "@components/TextInput";

const ControlledTextInput = <T extends FieldValues>({
  control,
  name,
  rules,
  ...textInputProps
}: UseControllerProps<T> & TextInputProps) => {
  return (
    <Controller
      control={control}
      name={name}
      rules={rules}
      render={({ field, fieldState }) => (
        <TextInput
          {...textInputProps}
          value={field.value}
          onChangeText={field.onChange}
          onBlur={field.onBlur}
          errorMessage={fieldState.error?.message}
        />
      )}
    />
  );
};

export { ControlledTextInput };
