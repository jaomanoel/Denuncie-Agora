import packageJson from "../../package.json";

const { version } = packageJson;

const uri: { [key: string]: string } = {
  development: "http://localhost:8080",
  production: "https://",
  test: "https://",
};

const NODE_ENV = process.env.NODE_ENV || "development";

export { uri, version, NODE_ENV };
