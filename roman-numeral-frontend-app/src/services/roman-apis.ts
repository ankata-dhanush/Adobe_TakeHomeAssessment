import { Logger } from "./logging";

type RomanNumeralResponse = {
  output: string;
};

const apiUrl = import.meta.env.VITE_API_URL; // where is this coming from

/**
 *
 * @param inputNum
 * @returns promise string
 */
export const getRomanNumeral = async (inputNum: string): Promise<string> => {
  try {
    const response = await fetch(`${apiUrl}/romannumeral?query=${inputNum}`);
    const data: RomanNumeralResponse = await response.json();
    Logger.log("Converted to roman numeral successfully", data);
    return data.output;
  } catch (error) {
    Logger.error("Error fetching Roman numeral:", error);
    return "";
  }
};
