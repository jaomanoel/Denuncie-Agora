export interface TextInputProps {
  value: string;
  label: string;
  placeholder?: string;
  errorMessage?: string;
  onChangeText: () => void;
  onBlur: () => void;
  isDescription?: boolean;
}
