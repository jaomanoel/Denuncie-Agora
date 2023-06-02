import colors from "@constants/colors";
import { ActivityIndicator } from "react-native";

const Loading: React.FC<LoadingProps> = ({ isLoading }) => {
  if (isLoading) {
    return <ActivityIndicator size={30} color={colors.dark.primary} />;
  }

  return null;
};

export { Loading };
