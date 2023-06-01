import { getReports } from "utils/getReports";
import { useQuery } from "@tanstack/react-query";

export const useDenuncias = () => {
  const { data, isLoading } = useQuery<ReportProps[]>({
    queryKey: ["reports"],
    queryFn: getReports,
  });

  return {
    data,
    isLoading,
  };
};
