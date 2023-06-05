import * as Application from "expo-application";
import { useCallback } from "react";
import { Platform } from "react-native";

export const getDeviceId = useCallback(async () => {
  let mobileId: string | null = null;

  if (Platform.OS === "android") mobileId = Application.androidId;
  if (Platform.OS === "ios") {
    await Application.getIosIdForVendorAsync()
      .then((response) => (mobileId = response))
      .catch((err) => console.log(err));
  }

  return mobileId;
}, []);
