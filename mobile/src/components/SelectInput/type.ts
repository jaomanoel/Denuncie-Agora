export interface SelectInputProps {
  onSelect: (index: any) => void;
  label: string;
  data: string[];
  errorMessage?: string;
  isIndex: boolean;
}
