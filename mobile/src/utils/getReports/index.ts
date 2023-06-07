import api from "@services/api";

export interface GetReportsProps {
  hateCrime?: string;
}

export const getReports = async (hateCrime?: string) => {
  const response = await api.get<ReportProps[]>(
    `/reports?hatecrime=${hateCrime}`
  );

  return response.data;
};
