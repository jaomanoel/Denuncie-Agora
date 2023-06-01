import React from "react";
import * as S from "./styles";
import { ThemeProvider } from "styled-components/native";
import { lightTheme } from "theme/lightTheme";
import { Image } from "react-native";
import { useHome } from "./useHome";

const Home: React.FC = () => {
  const handleEnter = useHome();

  return (
    <ThemeProvider theme={lightTheme}>
      <S.container>
        <Image
          source={require("@assets/images/bg-home.jpg")}
          style={{ width: 400, height: 400 }}
        />
        <S.button onPress={handleEnter}>
          <S.buttonLabel>entrar</S.buttonLabel>
        </S.button>
      </S.container>
    </ThemeProvider>
  );
};

export { Home };
