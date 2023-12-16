/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    borderRadius: { DEFAULT: "16px", full: "9999px" },
    boxShadow: {
      DEFAULT: "0px 10px 60px rgba(0, 0, 0, 0.1)",
      sm: " 0px 4px 30px rgba(0, 0, 0, 0.1)",
    },
    colors: {
      systemGreen: { light: "#00b300", dark: "#32d74b" },
      systemRed: { light: "#ff3b30", dark: "#ff453a" },
      systemBlue: { light: "#007aff", dark: "#0a84ff" },
      systemGrey: { light: "#8e8e93", dark: "#8e8e93" },
      systemWhite: { light: "#FFFFFF", dark: "#FFFFFF" },
      textBlack: { light: "#0b0a0a", dark: "#f5f5f5" },
      borderGrey: { light: "#b9b9bb", dark: "#b9b9bb" },
      systemBackground: {
        light: "#f9f9f9f0",
        lightSecondary: "#F2F2F7",
        dark: "#f9f9f9f0",
      },
      labelColor: {
        light: {
          tertiary: "#bebec0",
          secondary: "#86868a",
        },
      },
      rateColor: {
        light: {
          rate: "#4A4A4D",
          label: "#8A8A8D",
        },
      },
      fillColor: {
        light: {
          teritary: "#e5e5e6",
          secondary: "#e1e1e2",
        },
      },
      separatorColor: {
        light: {
          withTrans: "#3C3C435C",
        },
      },
    },
    fillColor: {
      light: {
        teritary: "#e5e5e6",
        secondary: "#e1e1e2",
      },
    },
    fontFamily: {
      DEFAULT: ["SF-Pro-text", "sans-serif"],
    },
  },
  plugins: [],
  corePlugins: {
    preflight: false,
  },
};
