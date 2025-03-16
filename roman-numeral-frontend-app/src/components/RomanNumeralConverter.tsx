import React, { useState } from "react";
import {
  Provider,
  defaultTheme,
  Button,
  TextField,
  Heading,
  View,
  Text,
  Flex,
} from "@adobe/react-spectrum";
import { getRomanNumeral } from "../services/roman-apis";
import { Logger } from "../services/logging";

const romanNumeralConverterLocale = {
  romanNumeralConverterTitle: "Roman numeral converter",
  enterNumberLabel: "Enter a number",
  convertButtonText: "Convert to roman numeral",
  romanNumeralLabel: "Roman numeral:",
  invalidNumberMessage: "Please enter a number between 1 and 3999",
};

const MAX_NUMBER = 4000;
const MIN_NUMBER = 0;

export const RomanNumeralConverter: React.FC = () => {
  const [inputNumber, setInputNumber] = useState("");
  const [romanNumeral, setRomanNumeral] = useState("");

  const handleConvert = async () => {
    if (Number(inputNumber) < MIN_NUMBER || Number(inputNumber) > MAX_NUMBER) {
      return;
    }
    try {
      const romanNumber = await getRomanNumeral(inputNumber);
      setRomanNumeral(romanNumber);
    } catch (err) {
      Logger.error(err);
    }
  };

  const onInputChange = (value: string) => {
    setRomanNumeral("");
    if (
      value == "" ||
      (Number(value) > MIN_NUMBER && Number(value) < MAX_NUMBER)
    ) {
      setInputNumber(value);
    }
  };

  return (
    <Provider theme={defaultTheme}>
      <View paddingX="size-1000" paddingY="size-400">
        <View padding="size-400" maxWidth="size-4000" marginX="auto">
          <Flex direction="column" alignItems="start">
            <Heading level={1}>
              {romanNumeralConverterLocale.romanNumeralConverterTitle}
            </Heading>

            <View marginY="size-200" width="100%">
              <TextField
                label={romanNumeralConverterLocale.enterNumberLabel}
                type="number"
                value={inputNumber}
                onChange={(value) => {
                  onInputChange(value);
                }}
                width="100%"
              />
            </View>

            <View marginY="size-200">
              <Button
                variant="primary"
                onPress={handleConvert}
                marginBottom="size-200"
                isDisabled={!inputNumber}
              >
                {romanNumeralConverterLocale.convertButtonText}
              </Button>
            </View>

            <Flex direction="row" alignItems="center" gap="size-100">
              <Heading level={4}>
                {romanNumeralConverterLocale.romanNumeralLabel}
              </Heading>
              <Text>{romanNumeral}</Text>
            </Flex>
          </Flex>
        </View>
      </View>
    </Provider>
  );
};
