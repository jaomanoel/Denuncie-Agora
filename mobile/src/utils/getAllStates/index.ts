import axios from "axios";

export const getAllState = async () => {
  const response = await axios.get<StateProps[]>(
    "https://servicodados.ibge.gov.br/api/v1/localidades/estados"
  );

  return response.data;
};
