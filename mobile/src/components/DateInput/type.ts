export interface DateInputProps {
  date: Date | null;
  mode: "time" | "date" | "datetime";
  errorMessage?: string;
  isDatePickerVisible: boolean;
  handleDate: (date: Date) => void;
  handleDatePicker: () => void;
}
