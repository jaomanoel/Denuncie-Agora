import styled from "styled-components/native";

export const container = styled.SafeAreaView`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100%;
  background-color: ${({theme}) => theme.colors.background};
`;

export const button = styled.Pressable`
  border-radius: 999px;
  background-color: ${({ theme }) => theme.colors.primary};
  padding: 12px 34px;
`;

export const buttonLabel = styled.Text`
  color: white;
  font-weight: bold;
  font-size: ${({ theme }) => theme.fontSizes.xl}px;
  text-transform: uppercase;
`;
