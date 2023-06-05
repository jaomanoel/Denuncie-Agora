import api from "@services/api";

export const getReportById = async (id: string) => {
  const response = await api.get<ReportProps>(`/reports/?id=${id}`);

  return response.data;
};
