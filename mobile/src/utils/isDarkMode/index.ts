import { useColorScheme } from "react-native";

export const isDarkMode = (): boolean => {
  const isDarkMode = useColorScheme() == "dark";

  return isDarkMode;
};
