import api from "@services/api";
import { useCallback } from "react";

export const getReports = async () => {
  const response = await api.get<ReportProps[]>("/reports");

  return response.data;
};
