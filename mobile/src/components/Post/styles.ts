import styled from "styled-components/native";

export const container = styled.View`
  flex: 1;
  flex-direction: column;
  gap: 8px;
  background-color: ${({ theme }) => theme.colors.background};
  width: 100%;
  height: 100%;
  padding-top: 20px;
`;

export const containerTitle = styled.View`
  justify-content: space-between;
  display: flex;
  align-items: center;
  flex-direction: row;
`;

export const title = styled.Text`
  font-size: ${(props) => props.theme.fontSizes["2xl"]}px;
  font-weight: bold;
  color: ${(props) => props.theme.colors.onBackground};
`;

export const containerDescription = styled.View`
  width: 100%;
  border: 1px solid ${({ theme }) => theme.colors.onBackground};
  align-items: flex-end;
  position: relative;
`;

export const text = styled.Text`
  font-size: ${({ theme }) => theme.fontSizes.base}px;
  font-weight: 700;
  color: ${({ theme }) => theme.colors.onBackground};
`;

export const description = styled.Text`
  font-size: ${({ theme }) => theme.fontSizes.xl}px;
  color: ${({ theme }) => theme.colors.onBackground};
  padding: ${(props) => props.theme.spaces.default}px;
  width: 100%;
  text-align: left;
`;

export const line = styled.View`
  height: 1px;
  width: 100%;
  background-color: ${({ theme }) => theme.colors.onBackground};
`;

export const button = styled.TouchableOpacity`
  padding: ${(props) => props.theme.spaces.default}px;
`;

