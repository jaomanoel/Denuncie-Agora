import { getReports } from "utils/getReports";
import { useQuery } from "@tanstack/react-query";
import { useEffect } from "react";

export const useDenuncias = () => {
  const { data, isLoading, refetch } = useQuery<ReportProps[]>({
    queryKey: ["reports"],
    queryFn: getReports,
  });

  useEffect(() => {
    refetch();
  }, [refetch]);

  return {
    data,
    isLoading,
    refetch,
  };
};
