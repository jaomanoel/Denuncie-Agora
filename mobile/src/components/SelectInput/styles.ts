import styled from "styled-components/native";

export const container = styled.View`
  display: flex;
  align-items: flex-start;
  flex-direction: column;
  gap: ${({ theme }) => theme.spaces.default}px;
  width: 100%;
`;

export const label = styled.Text`
  color: ${({ theme }) => theme.colors.text};
  font-size: ${({ theme }) => theme.fontSizes.lg}px;
  font-weight: bold;
`;

export const error = styled.Text`
  color: red;
  font-size: ${({ theme }) => theme.fontSizes.sm}px;
`;
