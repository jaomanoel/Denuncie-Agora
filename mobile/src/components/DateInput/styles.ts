import styled from "styled-components/native";

export const container = styled.View`
  display: flex;
  align-items: flex-start;
  flex-direction: column;
  gap: ${({ theme }) => theme.spaces.default}px;
  width: 100%;
`;

export const dateContainer = styled.View`
  width: 100%;
  display: flex;
  align-items: flex-start;
  gap: ${({ theme }) => theme.spaces.default}px;
`;

export const dateLabel = styled.Text`
  color: black;
  font-size: ${({ theme }) => theme.fontSizes.lg}px;
`;

export const datePicker = styled.Pressable`
  width: 100%;
  background-color: #ebebeb;
  height: 48px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
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
