import React from "react";
import { Post } from "@components/Post";
import { FlatList, View } from "react-native";
import { darkTheme } from "theme/darkTheme";
import { lightTheme } from "theme/lightTheme";
import { ThemeProvider } from "styled-components/native";
import { useDenuncias } from "./useDenuncias";
import LottieView from "lottie-react-native";

import * as S from "./styles";

const Denuncias: React.FC = () => {
  const { data, isDarkMode, isLoading } = useDenuncias();

  return (
    <ThemeProvider theme={isDarkMode ? darkTheme : lightTheme}>
      {isLoading ? (
        <S.container>
          <LottieView
            source={require("@assets/animations/animate-loading.json")}
            autoPlay
            loop
            style={{ width: 80, height: 80 }}
          />
        </S.container>
      ) : (
        <S.container>
          <FlatList
            style={{ width: "100%", height: "100%" }}
            keyExtractor={(item: ReportProps) => item.id}
            data={data}
            horizontal={false}
            renderItem={({ item }) => (
              <Post
                date={item.date}
                description={item.description}
                about={item.about}
                id={item.id}
                identity={item.identity}
                state={item.state}
                city={item.city}
              />
            )}
          />
        </S.container>
      )}
    </ThemeProvider>
  );
};

export { Denuncias };
