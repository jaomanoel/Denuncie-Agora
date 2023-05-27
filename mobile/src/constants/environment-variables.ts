import packageJson from "../../package.json";

const { version } = packageJson;

const uri: { [key: string]: string } = {
  development: "http://192.168.16.139:8080",
  production: "https://",
  test: "https://",
};

const NODE_ENV = process.env.NODE_ENV || "development";

export { uri, version, NODE_ENV };
