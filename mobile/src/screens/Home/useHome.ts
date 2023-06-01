import { useRouter } from "expo-router";
import { useCallback } from "react";

export const useHome = (): (() => void) => {
  const router = useRouter();

  const handleEnter = useCallback(() => {
    router.push("(tabs)");
  }, []);

  return handleEnter;
};
