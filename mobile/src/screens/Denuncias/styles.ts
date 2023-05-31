import styled from "styled-components/native";

export const container = styled.SafeAreaView`
  align-items: center;
  justify-content: center;
  padding-left: 20px;
  padding-right: 20px;
  background-color: ${({ theme }) => theme.colors.background};
  width: 100%;
  height: 100%;
`;
