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

export const input = styled.TextInput`
  border-radius: 10px;
  background-color: #ebebeb;
  height: 48px;
  width: 100%;
  padding-left: ${({ theme }) => theme.spaces.md}px;
  color: black;
`;

export const description = styled.TextInput`
  border-radius: 10px;
  background-color: #ebebeb;
  height: 140px;
  width: 100%;
  padding-left: ${({ theme }) => theme.spaces.md}px;
  color: black;
`;

export const error = styled.Text`
  color: red;
  font-size: ${({ theme }) => theme.fontSizes.sm}px;
`;
