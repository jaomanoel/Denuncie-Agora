import styled from "styled-components/native";

export const container = styled.Pressable<{ isSelected: boolean }>`
  border: 1px solid ${({ theme }) => theme.colors.onBackground};
  display: flex;
  align-items: center;
  justify-content: center;
  padding: ${({ theme }) =>
    theme.spaces.default + "px " + theme.spaces.base + "px"};
  border-radius: 999px;
  background-color: ${({ theme }) => theme.colors.primary};
  margin-right: ${({ theme }) => theme.spaces.default}px;

  ${(props) =>
    props.isSelected === true
      ? `background-color: ${props.theme.colors.primary}`
      : `background-color: ${props.theme.colors.background}`}
`;

export const label = styled.Text<{ isSelected: boolean }>`
  font-size: ${({ theme }) => theme.fontSizes.base}px;
  font-weight: 700;
  text-align: center;

  ${(props) =>
    props.isSelected === true
      ? `color: white;`
      : `color: ${props.theme.colors.text}`}
`;
