import { getReports } from "utils/getReports";
import { useQuery } from "@tanstack/react-query";
import { useColorScheme } from "react-native";

export const useDenuncias = () => {
  const { data, isLoading } = useQuery<ReportProps[]>({
    queryKey: ["reports"],
    queryFn: getReports,
  });

  const isDarkMode = useColorScheme() == "dark";

  return {
    data,
    isLoading,
    isDarkMode,
  };
};
