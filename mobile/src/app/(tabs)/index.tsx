import { FlatList, StyleSheet } from "react-native";
import { Post } from "../../src/components/Post";
import { useCallback, useEffect, useState } from "react";
import axios from "axios";
import { View } from "../../src/components/Themed";

export default function TabOneScreen() {
  const [reports, setReports] = useState<ReportProps[]>([]);

  const getReports = useCallback(async () => {
    await axios
      .get<ReportProps[]>("http://192.168.16.139:8080/reports")
      .then((response) => setReports(response.data))
      .catch((error) => {
        console.log(error);
      });
  }, []);

  useEffect(() => {
    getReports();
  }, [getReports]);

  return (
    <View style={styles.container}>
      <FlatList
        keyExtractor={(item: ReportProps) => item.id}
        data={reports}
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
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    paddingRight: 20,
    paddingLeft: 20,
  },
  title: {
    fontSize: 20,
    fontWeight: "bold",
  },
  separator: {
    marginVertical: 30,
    height: 1,
    width: "80%",
  },
});
