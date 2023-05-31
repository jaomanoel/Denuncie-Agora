import "styled-components/native";

declare module "styled-components/native" {
  export interface DefaultTheme {
    colors: {
      text: string;
      tint: string;
      primary: string;
      secundary: string;
      background: string;
      onBackground: string;
      tabIconDefault: string;
      tabIconSelected: string;
    };

    space: {
      default: number;
      sm: number;
      base: number;
      lg: number;
      xl: number;
      "2xl": number;
      "3xl": number;
    };
  }
}
