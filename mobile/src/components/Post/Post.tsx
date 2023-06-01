import React from "react";
import { View, useColorScheme } from "react-native";
import { usePost } from "./usePost";
import { Modal } from "@components/Modal";
import IconMenu from "@assets/images/icon-menu.svg";
import * as S from "./styles";

const Post: React.FC<PostProps> = ({
  date,
  description,
  id,
  about,
  state,
  city,
  identity,
}) => {
  const isDarkMode = useColorScheme() === "dark";
  const { modal, handleModal } = usePost();

  return (
    <S.container>
      <View>
        <S.containerTitle>
          <View>
            <S.title>{about}</S.title>

            <S.text>
              {city}, {state}
            </S.text>
          </View>

          <S.text>{date.replace(/-/g, "/")}</S.text>
        </S.containerTitle>
      </View>

      <S.containerDescription>
        <S.description>{description}</S.description>

        <S.line></S.line>

        <S.button onPress={handleModal}>
          <IconMenu
            width={30}
            height={30}
            fill={isDarkMode ? "#fff" : "#000"}
          />
        </S.button>
      </S.containerDescription>

      <Modal
        id={id}
        identity={identity}
        open={modal}
        handleOpen={handleModal}
      />
    </S.container>
  );
};

export { Post };
